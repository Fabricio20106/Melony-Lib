package com.junethewoods.melonylib.mixin.item;

import com.junethewoods.melonylib.util.tab.MLSpawnEggsTab;
import net.minecraft.item.*;
import net.minecraft.util.NonNullList;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SpawnEggItem.class)
public class MLSpawnEggItemMixin extends Item {
    public MLSpawnEggItemMixin(Properties properties) {
        super(properties);
    }

    @Override
    public void fillItemCategory(ItemGroup tab, NonNullList<ItemStack> list) {
        if ((this.allowdedIn(tab) || tab == MLSpawnEggsTab.TAB) && tab != ItemGroup.TAB_MISC) {
            if (this == Items.BAT_SPAWN_EGG) {
                list.add(new ItemStack(Items.SPAWNER));
                list.add(new ItemStack(this));
            } else {
                list.add(new ItemStack(this));
            }
        }
    }
}
