package melonystudios.library.mixin.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BannerPatternItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(BannerPatternItem.class)
public class MLBannerPatternItemMixin extends Item {
    public MLBannerPatternItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "appendHoverText", at = @At("HEAD"), cancellable = true)
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag, CallbackInfo ci) {
        ci.cancel();
        super.appendHoverText(stack, world, tooltip, flag);
    }
}
