package melonystudios.library.mixin.block;

import melonystudios.library.util.MLKeys;
import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.*;
import net.minecraft.world.IBlockReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

// Fixes https://bugs.mojang.com/browse/MC-132201 (Shulker box tooltips do not take item rarity colours into consideration)
@Mixin(ShulkerBoxBlock.class)
public class MLShulkerBoxBlockMixin extends Block {
    public MLShulkerBoxBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "appendHoverText", at = @At("HEAD"), cancellable = true)
    public void appendHoverText(ItemStack stack, IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag, CallbackInfo ci) {
        ci.cancel();
        super.appendHoverText(stack, world, tooltip, flag);
        CompoundNBT blockEntityTag = stack.getTagElement("BlockEntityTag");
        if (blockEntityTag != null) {
            if (blockEntityTag.contains("LootTable", 8)) tooltip.add(new TranslationTextComponent("container.shulker_box.has_unknown_loot").withStyle(TextFormatting.GRAY));

            if (blockEntityTag.contains("Items", 9)) {
                NonNullList<ItemStack> shulkerItems = NonNullList.withSize(27, ItemStack.EMPTY);
                ItemStackHelper.loadAllItems(blockEntityTag, shulkerItems);
                int displayedItems = 0;
                int totalItems = 0;

                for (ItemStack shulkerStack : shulkerItems) {
                    if (!shulkerStack.isEmpty()) {
                        ++totalItems;
                        if (!MLKeys.isShiftDown()) {
                            if (displayedItems <= 4) {
                                ++displayedItems;
                                IFormattableTextComponent component = shulkerStack.getDisplayName().copy();
                                ITextComponent countComponent = new TranslationTextComponent("container.shulker_box.item_count", shulkerStack.getCount()).withStyle(TextFormatting.GRAY);
                                component.append(countComponent);
                                tooltip.add(component);
                            }
                        } else {
                            IFormattableTextComponent component = shulkerStack.getDisplayName().copy();
                            ITextComponent countComponent = new TranslationTextComponent("container.shulker_box.item_count", shulkerStack.getCount()).withStyle(TextFormatting.GRAY);
                            component.append(countComponent);
                            tooltip.add(component);
                        }
                    }
                }

                if (totalItems - displayedItems > 0 && !MLKeys.isShiftDown()) {
                    tooltip.add(new TranslationTextComponent("container.shulkerBox.more", totalItems - displayedItems).withStyle(TextFormatting.DARK_GRAY).withStyle(TextFormatting.ITALIC));
                    tooltip.add(new TranslationTextComponent("container.shulker_box.hold_shift").withStyle(TextFormatting.DARK_GRAY).withStyle(TextFormatting.ITALIC));
                }
            }
        }
    }
}
