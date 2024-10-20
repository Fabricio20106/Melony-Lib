package melonystudios.library.util;

import melonystudios.library.MelonyLib;
import melonystudios.library.misc.HighContrastPack;
import melonystudios.library.packet.CHurtAnimationPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.play.IClientPlayNetHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.resources.IPackNameDecorator;
import net.minecraft.resources.ResourcePackInfo;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.ModList;

import javax.annotation.Nullable;

public class LibUtils {
    @Nullable
    private static final Rarity POTATO = Rarity.create("POTATO", TextFormatting.GREEN);

    public static ResourceLocation minecraft(String name) {
        return new ResourceLocation(name);
    }

    public static ResourceLocation forge(String name) {
        return new ResourceLocation("forge", name);
    }

    public static ResourceLocation melony(String name) {
        return new ResourceLocation("melony", name);
    }

    public static ResourceLocation melonyLib(String name) {
        return new ResourceLocation(MelonyLib.MOD_ID, name);
    }

    public static ResourceLocation variants(String name) {
        return new ResourceLocation("variants", name);
    }

    public static ResourceLocation theMato(String name) {
        return new ResourceLocation("themato", name);
    }

    public static ResourceLocation backMath(String name) {
        return new ResourceLocation("backmath", name);
    }

    public static Rarity potatoRarity() {
        return POTATO != null ? POTATO : Rarity.COMMON;
    }

    public static ItemStack getTermianBannerItem() {
        ItemStack lightBlueBanner = new ItemStack(Items.LIGHT_BLUE_BANNER);
        CompoundNBT blockEntityTag = lightBlueBanner.getOrCreateTagElement("BlockEntityTag");
        ListNBT patterns = new BannerPattern.Builder().addPattern(BannerPattern.GRADIENT_UP, DyeColor.PURPLE).addPattern(BannerPattern.STRIPE_CENTER, DyeColor.LIGHT_BLUE).addPattern(
                BannerPattern.RHOMBUS_MIDDLE, DyeColor.CYAN).addPattern(BannerPattern.FLOWER, DyeColor.RED).addPattern(BannerPattern.FLOWER, DyeColor.YELLOW).toListTag();
        blockEntityTag.put("Patterns", patterns);
        lightBlueBanner.hideTooltipPart(ItemStack.TooltipDisplayFlags.ADDITIONAL);
        lightBlueBanner.setHoverName(new TranslationTextComponent("block.backmath.termian_empire_banner").withStyle(Style.EMPTY.withColor(Color.fromRgb(0x1DC2D1)).withItalic(false)));
        return lightBlueBanner;
    }

    // Copied from teamtwilight/twilightforest.
    public static void addHighContrastResourcePack() {
        // noinspection ConstantConditions
        if (Minecraft.getInstance() == null)
            // Normally Minecraft Client is never null except when generating through runData
            return;

        Minecraft.getInstance().getResourcePackRepository().addPackFinder((packInfo, infoFactory) -> packInfo.accept(ResourcePackInfo.create(
                LibUtils.melonyLib("high_contrast").toString(), false, () -> new HighContrastPack(ModList.get()
                        .getModFileById(MelonyLib.MOD_ID).getFile()), infoFactory, ResourcePackInfo.Priority.TOP, IPackNameDecorator.BUILT_IN)));
    }

    public static void handleHurtAnimation(CHurtAnimationPacket packet, IClientPlayNetHandler playHandler) {
        Minecraft minecraft = Minecraft.getInstance();
        PacketThreadUtil.ensureRunningOnSameThread(packet, playHandler, minecraft);
        if (minecraft.level != null) {
            Entity entity = minecraft.level.getEntity(packet.entityID);
            if (entity != null) {
                if (entity instanceof LivingEntity) ((LivingEntity) entity).hurtDir = packet.yaw;
                entity.animateHurt();
            }
        }
    }
}
