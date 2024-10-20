package melonystudios.library.command;

import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import melonystudios.library.command.argument.SurfaceBuilderArgument;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.BlockPosArgument;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.server.ServerWorld;
import org.apache.logging.log4j.LogManager;

import java.util.List;

public class FillSurfaceCommand {
    private static final Dynamic2CommandExceptionType AREA_TOO_LARGE_ERROR = new Dynamic2CommandExceptionType((maximumArea, totalArea) -> new TranslationTextComponent("commands.fillsurface.tooBig", maximumArea, totalArea));
    private static final SimpleCommandExceptionType FAILED_ERROR = new SimpleCommandExceptionType(new TranslationTextComponent("commands.fillsurface.failed"));

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("fillsurface").requires(source -> source.hasPermission(2))
                .then(Commands.argument("from", BlockPosArgument.blockPos())
                        .then(Commands.argument("to", BlockPosArgument.blockPos())
                                .then(Commands.argument("surface_builder", SurfaceBuilderArgument.surfaceBuilder())
                                        .executes(context -> fillSurface(context.getSource(), new MutableBoundingBox(BlockPosArgument.getLoadedBlockPos(context, "from"), BlockPosArgument.getLoadedBlockPos(context, "to")), SurfaceBuilderArgument.getSurfaceBuilder(context, "surface_builder")))))));
    }

    private static int fillSurface(CommandSource source, MutableBoundingBox boundingBox, ConfiguredSurfaceBuilder<?> surfaceBuilder) throws CommandSyntaxException {
        int totalArea = boundingBox.getXSpan() * boundingBox.getYSpan() * boundingBox.getZSpan();
        if (totalArea > 32768) {
            throw AREA_TOO_LARGE_ERROR.create(32768, totalArea);
        } else {
            List<BlockPos> toUpdate = Lists.newArrayList();
            ServerWorld world = source.getLevel();
            int changedBlocks = 0;

            for (BlockPos pos : BlockPos.betweenClosed(boundingBox.x0, boundingBox.y0, boundingBox.z0, boundingBox.x1, boundingBox.y1, boundingBox.z1)) {
                try {
                    surfaceBuilder.initNoise(world.getSeed());
                    surfaceBuilder.apply(world.random, world.getChunk(pos), world.getBiome(pos), pos.getX(), pos.getZ(), pos.getY(), 0.3, Blocks.SANDSTONE_SLAB.defaultBlockState(), Fluids.WATER.defaultFluidState().createLegacyBlock(), world.getChunkSource().getGenerator().getSeaLevel(), world.getSeed());
                } catch (Exception exception) {
                    LogManager.getLogger().error("something went wrong!", exception);
                }
                toUpdate.add(pos.immutable());
                ++changedBlocks;
            }

            for (BlockPos pos : toUpdate) {
                Block block = world.getBlockState(pos).getBlock();
                world.blockUpdated(pos, block);
            }

            if (changedBlocks == 0) {
                throw FAILED_ERROR.create();
            } else {
                source.sendSuccess(new TranslationTextComponent("commands.fill.success", changedBlocks), true);
                return changedBlocks;
            }
        }
    }
}
