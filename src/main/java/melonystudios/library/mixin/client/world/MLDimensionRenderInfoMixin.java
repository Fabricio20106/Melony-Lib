package melonystudios.library.mixin.client.world;

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
        cir.setReturnValue(this.cloudLevel == 128 ? 192 : this.cloudLevel);
    }
}
