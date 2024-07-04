package melonystudios.library.mixin.item;

import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FireworkRocketItem.class)
public class MLFireworkRocketItemMixin extends Item {
    public MLFireworkRocketItemMixin(Properties properties) {
        super(properties);
    }

    @Override
    public void fillItemCategory(ItemGroup tab, NonNullList<ItemStack> list) {
        if (this.allowdedIn(tab)) {
            for (int flight = 1; flight <= 3; ++flight) {
                ItemStack rocketStack = new ItemStack(this);
                CompoundNBT flightTag = rocketStack.getOrCreateTagElement("Fireworks");
                flightTag.putInt("Flight", flight);
                list.add(rocketStack);
            }
        }
    }
}
