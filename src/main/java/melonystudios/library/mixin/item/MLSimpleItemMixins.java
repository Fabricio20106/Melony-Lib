package melonystudios.library.mixin.item;

import melonystudios.library.tag.ConventionItemTags;
import net.minecraft.item.*;
import net.minecraft.tags.ItemTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class MLSimpleItemMixins {
    @Mixin(Item.class)
    public static class ItemMixin {
        @Inject(method = "getRarity", at = @At("HEAD"), cancellable = true)
        private void getRarity(ItemStack stack, CallbackInfoReturnable<Rarity> cir) {
            if (!ItemTags.getAllTags().getAllTags().isEmpty()) {
                if (stack.getItem().is(ConventionItemTags.WITH_COMMON_RARITY)) {
                    cir.setReturnValue(stack.isEnchanted() ? Rarity.UNCOMMON : Rarity.COMMON);
                } else if (stack.getItem().is(ConventionItemTags.WITH_UNCOMMON_RARITY)) {
                    cir.setReturnValue(stack.isEnchanted() ? Rarity.RARE : Rarity.UNCOMMON);
                } else if (stack.getItem().is(ConventionItemTags.WITH_RARE_RARITY)) {
                    cir.setReturnValue(stack.isEnchanted() ? Rarity.EPIC : Rarity.RARE);
                } else if (stack.getItem().is(ConventionItemTags.WITH_EPIC_RARITY)) {
                    cir.setReturnValue(Rarity.EPIC);
                }
            }
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
