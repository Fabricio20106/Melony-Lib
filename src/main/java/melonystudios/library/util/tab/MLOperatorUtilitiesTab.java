package melonystudios.library.util.tab;

import melonystudios.library.MelonyLib;
import melonystudios.library.item.MLModdedItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.ModList;

import javax.annotation.Nonnull;

public class MLOperatorUtilitiesTab extends ItemGroup {
    public static final MLOperatorUtilitiesTab TAB = new MLOperatorUtilitiesTab(MelonyLib.MOD_ID + ".operator_utilities");

    public MLOperatorUtilitiesTab(String label) {
        super(label);
    }

    @Override
    @Nonnull
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
        if (ModList.get().isLoaded("variants")) {
            list.add(new ItemStack(MLModdedItems.OAK_DEBUG_STICK));
            list.add(new ItemStack(MLModdedItems.SPRUCE_DEBUG_STICK));
            list.add(new ItemStack(MLModdedItems.BIRCH_DEBUG_STICK));
            list.add(new ItemStack(MLModdedItems.JUNGLE_DEBUG_STICK));
            list.add(new ItemStack(MLModdedItems.ACACIA_DEBUG_STICK));
            list.add(new ItemStack(MLModdedItems.DARK_OAK_DEBUG_STICK));
            list.add(new ItemStack(MLModdedItems.PAINTING_DEBUG_STICK));
            list.add(new ItemStack(MLModdedItems.CRIMSON_DEBUG_STICK));
            list.add(new ItemStack(MLModdedItems.WARPED_DEBUG_STICK));
            list.add(new ItemStack(MLModdedItems.ENDERWOOD_DEBUG_STICK));
        }
        list.add(new ItemStack(Items.DRAGON_EGG));
    }

    public static void init() {}
}
