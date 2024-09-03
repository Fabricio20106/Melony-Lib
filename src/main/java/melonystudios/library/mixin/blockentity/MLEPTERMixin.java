package melonystudios.library.mixin.blockentity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.tileentity.EndPortalTileEntityRenderer;
import net.minecraft.tileentity.EndGatewayTileEntity;
import net.minecraft.tileentity.EndPortalTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(EndPortalTileEntityRenderer.class)
public abstract class MLEPTERMixin<T extends EndPortalTileEntity> {
    @Shadow
    protected abstract void renderFace(T endPortal, Matrix4f matrix4F, IVertexBuilder vertexBuilder, float p_228884_4_, float p_228884_5_, float p_228884_6_, float p_228884_7_, float p_228884_8_, float p_228884_9_, float p_228884_10_, float red, float green, float blue, float alpha, Direction direction);
    @Shadow
    @Final
    private static Random RANDOM;

    @Inject(method = "renderCube", at = @At("HEAD"), cancellable = true)
    private void renderCube(T endPortal, float topOffset, float p_228883_3_, Matrix4f matrix4F, IVertexBuilder vertexBuilder, CallbackInfo ci) {
        ci.cancel();
        float rand1 = (RANDOM.nextFloat() * 0.5F + 0.1F) * p_228883_3_;
        float rand2 = (RANDOM.nextFloat() * 0.5F + 0.4F) * p_228883_3_;
        float rand3 = (RANDOM.nextFloat() * 0.5F + 0.5F) * p_228883_3_;
        float bottomOffset = endPortal instanceof EndGatewayTileEntity ? 0 : 0.375F;

        renderFace(endPortal, matrix4F, vertexBuilder, 0, 1, 0, 1, 1, 1, 1, 1, rand1, rand2, rand3, Direction.SOUTH);
        renderFace(endPortal, matrix4F, vertexBuilder, 0, 1, 1, 0, 0, 0, 0, 0, rand1, rand2, rand3,  Direction.NORTH);
        renderFace(endPortal, matrix4F, vertexBuilder, 1, 1, 1, 0, 0, 1, 1, 0, rand1, rand2, rand3,  Direction.EAST);
        renderFace(endPortal, matrix4F, vertexBuilder, 0, 0, 0, 1, 0, 1, 1, 0, rand1, rand2, rand3,  Direction.WEST);
        renderFace(endPortal, matrix4F, vertexBuilder, 0, 1, bottomOffset, bottomOffset, 0, 0, 1, 1, rand1, rand2, rand3,  Direction.DOWN);
        renderFace(endPortal, matrix4F, vertexBuilder, 0, 1, topOffset, topOffset, 1, 1, 0, 0, rand1, rand2, rand3,  Direction.UP);
    }
}
