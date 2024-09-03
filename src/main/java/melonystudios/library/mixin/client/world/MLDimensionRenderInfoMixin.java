package melonystudios.library.mixin.client.world;

import melonystudios.library.config.MLConfigs;
import net.minecraft.client.world.DimensionRenderInfo;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DimensionRenderInfo.class)
public class MLDimensionRenderInfoMixin {
    @Shadow
    @Final
    private float cloudLevel;

    @Inject(method = "getCloudHeight", at = @At("HEAD"), cancellable = true)
    private void getCloudHeight(CallbackInfoReturnable<Float> cir) {
        double newCloudHeight = MLConfigs.COMMON_CONFIGS.defaultCloudHeight.get();
        cir.setReturnValue(this.cloudLevel == 128 ? (float) newCloudHeight : this.cloudLevel);
    }
}
