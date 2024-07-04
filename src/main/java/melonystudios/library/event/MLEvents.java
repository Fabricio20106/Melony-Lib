package melonystudios.library.event;

import melonystudios.library.MelonyLib;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ToolItem;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MelonyLib.MOD_ID)
public class MLEvents {
    @SubscribeEvent
    public static void onAddTooltips(ItemTooltipEvent event) {
        int componentCount = 6;
        if (event.getItemStack().getItem().isEdible()) componentCount = componentCount + 1;
        if (event.getItemStack().getItem() instanceof ToolItem) componentCount = componentCount + 1;
        if (event.getItemStack().getItem() instanceof ArmorItem) componentCount = componentCount + 1;
        if (event.getItemStack().getItem().isFireResistant()) componentCount = componentCount + 1;
        if (event.getItemStack().getItem().isFoil(event.getItemStack())) componentCount = componentCount + 1;

        if (event.getItemStack().getTag() != null) {
            event.getToolTip().remove(new TranslationTextComponent("item.nbt_tags", event.getItemStack().getTag().getAllKeys().size()).withStyle(TextFormatting.DARK_GRAY));
            event.getToolTip().add(new TranslationTextComponent("item.nbt_tags", (event.getItemStack().getTag().getAllKeys().size() + componentCount)).withStyle(TextFormatting.DARK_GRAY));
        } else {
            event.getToolTip().add(new TranslationTextComponent("item.nbt_tags", componentCount).withStyle(TextFormatting.DARK_GRAY));
        }
    }
}
