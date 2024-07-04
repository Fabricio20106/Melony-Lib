package com.junethewoods.melonylib.mixin.entity;

import com.junethewoods.melonylib.util.ConventionItemTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerData;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(VillagerEntity.class)
public abstract class MLVillagerEntityMixin extends AbstractVillagerEntity {
    public MLVillagerEntityMixin(EntityType<? extends AbstractVillagerEntity> villager, World world) {
        super(villager, world);
    }

    @Shadow
    public abstract VillagerData getVillagerData();

    @Inject(method = "wantsToPickUp", at = @At("HEAD"), cancellable = true)
    public void wantsToPickUp(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue((stack.getItem().is(ConventionItemTags.VILLAGER_WANTED_ITEMS) || this.getVillagerData().getProfession().getRequestedItems().contains(stack.getItem())) && this.getInventory().canAddItem(stack));
    }

    @Inject(method = "hasFarmSeeds", at = @At("HEAD"), cancellable = true)
    public void hasFarmSeeds(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(hasAnyMatching((stack -> stack.getItem().is(ConventionItemTags.VILLAGER_PLANTABLE_SEEDS))));
    }

    @Unique
    private boolean hasAnyMatching(Predicate<ItemStack> stack) {
        for(int i = 0; i < this.getInventory().getContainerSize(); ++i) {
            ItemStack invStack = this.getInventory().getItem(i);
            if (stack.test(invStack)) {
                return true;
            }
        }
        return false;
    }
}
