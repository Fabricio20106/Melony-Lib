package melonystudios.library.mixin.item;

import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class MLItemStackMixin {
    @Inject(method = "setDamageValue", at = @At("HEAD"), cancellable = true)
    public void setDamageValue(int damage, CallbackInfo ci) {
        if (damage == 0) ci.cancel();
    }
}
