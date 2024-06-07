package com.junethewoods.melonylib.mixin.item;

import net.minecraft.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class MLSimpleItemMixins {
    @Mixin(TridentItem.class)
    public static class TridentItemMixin extends Item {
        public TridentItemMixin(Properties properties) {
            super(properties);
        }

        @Override
        public Rarity getRarity(ItemStack stack) {
            return Rarity.EPIC;
        }
    }

    @Mixin(PotionItem.class)
    public static class PotionItemMixin extends Item {
        public PotionItemMixin(Properties properties) {
            super(properties);
        }

        @Inject(method = "isFoil", at = @At("HEAD"), cancellable = true)
        private void isFoil(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
            cir.setReturnValue(false);
        }
    }
}
