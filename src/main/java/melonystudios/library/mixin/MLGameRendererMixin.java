package melonystudios.library.mixin;

import com.mojang.blaze3d.matrix.MatrixStack;
import melonystudios.library.config.MLConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class MLGameRendererMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    // BUG: mobs attacking you while protecting with a shield still plays animation despite dealing no damage
    // (discovered while playing in Backend Modpack)
    @Inject(method = "bobHurt", at = @At("HEAD"), cancellable = true)
    private void bobHurt(MatrixStack stack, float partialTicks, CallbackInfo ci) {
        ci.cancel();
        if (this.minecraft.getCameraEntity() instanceof LivingEntity) {
            LivingEntity livEntity = (LivingEntity) this.minecraft.getCameraEntity();
            float hurtTime = (float) livEntity.hurtTime - partialTicks;
            if (livEntity.isDeadOrDying()) {
                float f1 = Math.min((float) livEntity.deathTime + partialTicks, 20);
                stack.mulPose(Vector3f.ZP.rotationDegrees(40 - 8000 / (f1 + 200)));
            }

            if (hurtTime > 0F) {
                hurtTime /= livEntity.hurtDuration;
                hurtTime = MathHelper.sin(hurtTime * hurtTime * hurtTime * hurtTime * (float) Math.PI);
                float hurtDirection = livEntity.hurtDir;
                stack.mulPose(Vector3f.YP.rotationDegrees(-hurtDirection));
                float tiltStrength = (float) ((double) (-hurtTime) * 14 * MLConfigs.COMMON_CONFIGS.damageTilt.get());
                stack.mulPose(Vector3f.ZP.rotationDegrees(tiltStrength));
                stack.mulPose(Vector3f.YP.rotationDegrees(hurtDirection));
            }
        }
    }
}
