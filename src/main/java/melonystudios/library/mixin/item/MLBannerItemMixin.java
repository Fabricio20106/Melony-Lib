package melonystudios.library.mixin.item;

import melonystudios.library.util.LibUtils;
import net.minecraft.item.*;
import net.minecraft.util.NonNullList;
import net.minecraft.world.raid.Raid;
import net.minecraftforge.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BannerItem.class)
public class MLBannerItemMixin extends Item {
    public MLBannerItemMixin(Properties properties) {
        super(properties);
    }

    @Override
    public void fillItemCategory(ItemGroup tab, NonNullList<ItemStack> list) {
        if (this.allowdedIn(tab)) {
            list.add(new ItemStack(this));
            ItemStack bannerStack = new ItemStack(this);
            if (bannerStack.getItem() == Items.BLACK_BANNER) {
                list.add(Raid.getLeaderBannerInstance());
                if (ModList.get().isLoaded("backmath")) list.add(LibUtils.getTermianBannerItem());
            }
        }
    }
}
