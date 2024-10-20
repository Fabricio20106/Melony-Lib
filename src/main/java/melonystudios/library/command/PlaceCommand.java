package melonystudios.library.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import melonystudios.library.command.argument.ConfiguredFeatureArgument;
import melonystudios.library.command.argument.ConfiguredStructureFeatureArgument;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.BlockPosArgument;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;

public class PlaceCommand {
    private static final SimpleCommandExceptionType PLACE_FEATURE_ERROR = new SimpleCommandExceptionType(new TranslationTextComponent("commands.place.feature.failed"));
    private static final SimpleCommandExceptionType PLACE_STRUCTURE_ERROR = new SimpleCommandExceptionType(new TranslationTextComponent("commands.place.structure.failed"));

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("place").requires(source -> source.hasPermission(2))
                .then(Commands.literal("feature")
                        .then(Commands.argument("feature", ConfiguredFeatureArgument.configuredFeature())
                                .executes(context -> placeFeature(context.getSource(), ConfiguredFeatureArgument.getFeature(context, "feature"), floorPos(context.getSource().getPosition())))
                                .then(Commands.argument("pos", BlockPosArgument.blockPos())
                                        .executes(context -> placeFeature(context.getSource(), ConfiguredFeatureArgument.getFeature(context, "feature"), BlockPosArgument.getLoadedBlockPos(context, "pos"))))))
                .then(Commands.literal("structure")
                        .then(Commands.argument("structure", ConfiguredStructureFeatureArgument.structureFeature())
                                .executes(context -> placeStructure(context.getSource(), ConfiguredStructureFeatureArgument.getStructure(context, "structure"), floorPos(context.getSource().getPosition())))
                                .then(Commands.argument("pos", BlockPosArgument.blockPos())
                                        .executes(context -> placeStructure(context.getSource(), ConfiguredStructureFeatureArgument.getStructure(context, "structure"), BlockPosArgument.getLoadedBlockPos(context, "pos")))))));
    }

    public static int placeFeature(CommandSource source, ConfiguredFeature<?, ?> feature, BlockPos pos) throws CommandSyntaxException {
        ServerWorld world = source.getLevel();
        ChunkPos chunkPos = new ChunkPos(pos);
        checkLoaded(world, new ChunkPos(chunkPos.x - 1, chunkPos.z - 1), new ChunkPos(chunkPos.x + 1, chunkPos.z + 1));
        if (!feature.place(world, world.getChunkSource().getGenerator(), world.getRandom(), pos)) {
            throw PLACE_FEATURE_ERROR.create();
        } else {
            source.sendSuccess(new TranslationTextComponent("commands.place.feature.success", feature.feature().getRegistryName().toString(), pos.getX(), pos.getY(), pos.getZ()), true);
            return 1;
        }
    }

    public static int placeStructure(CommandSource source, StructureFeature<?, ?> structure, BlockPos pos) throws CommandSyntaxException {
        ServerWorld world = source.getLevel();
        ChunkGenerator generator = world.getChunkSource().getGenerator();
        StructureStart<?> structureStart = structure.generate(source.registryAccess(), generator, generator.getBiomeSource(),
                world.getStructureManager(), world.getSeed(), new ChunkPos(pos), world.getBiome(pos), 0,
                new StructureSeparationSettings(2, 1, 1)); // new SharedSeedRandom(world.getSeed())
        if (!structureStart.isValid()) {
            throw PLACE_STRUCTURE_ERROR.create();
        } else {
            MutableBoundingBox box = structureStart.getBoundingBox();
            ChunkPos chunkPosMin = new ChunkPos(SectionPos.blockToSectionCoord(box.x0), SectionPos.blockToSectionCoord(box.z0));
            ChunkPos chunkPosMax = new ChunkPos(SectionPos.blockToSectionCoord(box.x1), SectionPos.blockToSectionCoord(box.z1));
            checkLoaded(world, chunkPosMin, chunkPosMax);
            ChunkPos.rangeClosed(chunkPosMin, chunkPosMax).forEach(pos1 -> structureStart.placeInChunk(world,
                    world.structureFeatureManager(), world.getChunkSource().getGenerator(), world.getRandom(), box, new ChunkPos(pos)));
            source.sendSuccess(new TranslationTextComponent("commands.place.structure.success", structure.feature.getRegistryName().toString(), pos.getX(), pos.getY(), pos.getZ()), true);
            return 1;
        }
    }

    public static void checkLoaded(ServerWorld world, ChunkPos pos, ChunkPos pos1) throws CommandSyntaxException {
        if (ChunkPos.rangeClosed(pos, pos1).anyMatch(posP -> !world.isLoaded(posP.getWorldPosition()))) {
            throw BlockPosArgument.ERROR_NOT_LOADED.create();
        }
    }

    public static BlockPos floorPos(Vector3d position) {
        return new BlockPos(MathHelper.floor(position.x), MathHelper.floor(position.y), MathHelper.floor(position.z));
    }
}
