package com.junethewoods.melonnierslib.common.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;

public class MLSwordItem extends SwordItem {
    public MLSwordItem(IItemTier tier, int attackDamage, float attackSpeed, Properties builder) {
        super(tier, attackDamage, attackSpeed, builder);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if (this.isMilked()) {
            player.addItem(new ItemStack(Items.MILK_BUCKET));
        }
        if (this.isFiery()) {
            entity.setSecondsOnFire(5);
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    public boolean isMilked() {
        return false;
    }

    public boolean isFiery() {
        return false;
    }
}
