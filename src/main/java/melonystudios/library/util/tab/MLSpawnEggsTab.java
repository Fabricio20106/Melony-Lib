package melonystudios.library.util.tab;

import melonystudios.library.MelonyLib;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class MLSpawnEggsTab extends ItemGroup {
    public static final MLSpawnEggsTab TAB = new MLSpawnEggsTab(MelonyLib.MOD_ID + ".spawn_eggs");

    public MLSpawnEggsTab(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Items.PIG_SPAWN_EGG);
    }
}
