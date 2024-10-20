package melonystudios.library.event;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import melonystudios.library.MelonyLib;
import melonystudios.library.config.MLConfigs;
import melonystudios.library.util.LibUtils;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.DrawHighlightEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = MelonyLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MLClientBusEvents {
    public static Map<ResourceLocation, BannerPattern> DATA_DRIVEN_PATTERNS = new HashMap<>();

    @SubscribeEvent
    public static void stitchTextures(final TextureStitchEvent.Pre event) {
        if (event.getMap().location().getPath().contains("banner_patterns")) {
//            event.addSprite(LibUtils.minecraft("entity/banner/guster"));
            for (ResourceLocation location : DATA_DRIVEN_PATTERNS.keySet()) {
                event.addSprite(LibUtils.minecraft("entity/banner/" + location.getPath()));
                LogManager.getLogger().debug("Loaded banner pattern texture: {}", "entity/banner/" + location.getPath());
            }
        }
        if (event.getMap().location().getPath().contains("shield_patterns")) {
//            event.addSprite(LibUtils.minecraft("entity/shield/guster"));
            for (ResourceLocation location : DATA_DRIVEN_PATTERNS.keySet()) {
                event.addSprite(LibUtils.minecraft("entity/shield/" + location.getPath()));
                LogManager.getLogger().debug("Loaded shield banner pattern texture: {}", "entity/banner/" + location.getPath());
            }
        }
    }

    @SubscribeEvent
    public void renderHighContrastOutlines(final DrawHighlightEvent.HighlightBlock event) {
        if (MLConfigs.COMMON_CONFIGS.highContrastBlockOutlines.get()) {
            event.setCanceled(true);
            renderHitOutline(event.getMatrix(), event.getBuffers().getBuffer(RenderType.lines()), event.getInfo().getEntity(),
                    event.getTarget().getBlockPos().getX(), event.getTarget().getBlockPos().getY(), event.getTarget().getBlockPos().getZ(),
                    event.getTarget().getBlockPos(), event.getInfo().getEntity().level.getBlockState(event.getTarget().getBlockPos()));
        }
    }

    private void renderHitOutline(MatrixStack stack, IVertexBuilder builder, Entity entity, double x, double y, double z, BlockPos pos, BlockState state) {
        renderShape(stack, builder, state.getShape(entity.level, pos, ISelectionContext.of(entity)), (double) pos.getX() - x, (double) pos.getY() - y, (double) pos.getZ() - z, 0.337F, 0.992F, 0.874F, 1);
//        renderShape(stack, builder, state.getShape(entity.level, pos, ISelectionContext.of(entity)), (double) pos.getX() - x + 0.2, (double) pos.getY() - y + 0.2, (double) pos.getZ() - z + 0.2, 0, 0, 0, 1);
    }

    private void renderShape(MatrixStack stack, IVertexBuilder builder, VoxelShape shape, double x, double y, double z, float red, float green, float blue, float alpha) {
        stack.pushPose();
        Matrix4f lastPose = stack.last().pose();
        shape.forAllEdges((minX, minY, minZ, maxX, maxY, maxZ) -> {
            builder.vertex(lastPose, (float) (minX + x), (float) (minY + y), (float) (minZ + z)).color(red, green, blue, alpha).endVertex();
            builder.vertex(lastPose, (float) (maxX + x), (float) (maxY + y), (float) (maxZ + z)).color(red, green, blue, alpha).endVertex();
        });
        stack.popPose();
    }
}
