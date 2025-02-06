package melonystudios.library.mixin.entity;

import melonystudios.library.tag.BackportedItemTags;
import melonystudios.library.tag.ConventionEntityTypeTags;
import melonystudios.library.tag.ConventionItemTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.piglin.PiglinEntity;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinTasks.class)
public class MLPiglinTasksMixin {
    @Inject(method = "isFood", at = @At("HEAD"), cancellable = true)
    private static void isFood(Item item, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(item.is(BackportedItemTags.PIGLIN_FOOD));
    }

    @Inject(method = "isBarterCurrency", at = @At("HEAD"), cancellable = true)
    private static void isBarterCurrency(Item item, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(item.is(ConventionItemTags.PIGLIN_CURRENCY));
    }

    @Inject(method = "hasCrossbow", at = @At("HEAD"), cancellable = true)
    private static void hasCrossbow(LivingEntity livEntity, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(livEntity.isHolding(item -> item.is(ConventionItemTags.CROSSBOWS)));
    }

    @Inject(method = "wantsToPickup", at = @At("HEAD"), cancellable = true)
    private static void wantsToPickup(PiglinEntity piglin, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (piglin.isBaby() && stack.getItem().is(BackportedItemTags.IGNORED_BY_PIGLIN_BABIES)) cir.setReturnValue(false);
    }

    @Inject(method = "isZombified", at = @At("HEAD"), cancellable = true)
    private static void isZombified(EntityType<?> entity, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(entity.is(ConventionEntityTypeTags.PIGLINS_AFRAID_OF));
    }
}
