package com.junethewoods.melonnierslib.common.item;

import net.minecraft.item.IItemTier;

public class MLMilkedSwordItem extends MLSwordItem {
    public MLMilkedSwordItem(IItemTier tier, int attackDamage, float attackSpeed, Properties builder) {
        super(tier, attackDamage, attackSpeed, builder);
    }

    @Override
    public boolean isMilked() {
        return true;
    }
}
