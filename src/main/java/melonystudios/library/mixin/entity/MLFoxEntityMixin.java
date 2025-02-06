package melonystudios.library.mixin.entity;

import melonystudios.library.tag.BackportedItemTags;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FoxEntity.class)
public class MLFoxEntityMixin {
    @Inject(method = "isFood", at = @At("HEAD"), cancellable = true)
    public void isFood(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(stack.getItem().is(BackportedItemTags.FOX_FOOD));
    }
}
