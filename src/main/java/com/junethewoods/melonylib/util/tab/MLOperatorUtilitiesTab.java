package com.junethewoods.melonylib.util.tab;

import com.junethewoods.melonylib.MelonyLib;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;

public class MLOperatorUtilitiesTab extends ItemGroup {
    public static final MLOperatorUtilitiesTab TAB = new MLOperatorUtilitiesTab(MelonyLib.MOD_ID + ".operator_utilities");

    public MLOperatorUtilitiesTab(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Items.COMMAND_BLOCK);
    }

    @Override
    public void fillItemList(NonNullList<ItemStack> list) {
        list.add(new ItemStack(Items.COMMAND_BLOCK));
        list.add(new ItemStack(Items.CHAIN_COMMAND_BLOCK));
        list.add(new ItemStack(Items.REPEATING_COMMAND_BLOCK));
        list.add(new ItemStack(Items.COMMAND_BLOCK_MINECART));
        list.add(new ItemStack(Items.JIGSAW));
        list.add(new ItemStack(Items.STRUCTURE_BLOCK));
        list.add(new ItemStack(Items.STRUCTURE_VOID));
        list.add(new ItemStack(Items.BARRIER));
        list.add(new ItemStack(Items.DEBUG_STICK));
    }

    public static void init() {}
}
