package melonystudios.library;

import com.google.common.collect.Lists;
import melonystudios.library.config.MLConfigs;
import melonystudios.library.config.MLOptions;
import melonystudios.library.misc.MLSounds;
import melonystudios.library.util.LibUtils;
import melonystudios.library.util.TabUtils;
import melonystudios.library.util.tab.MLOperatorUtilitiesTab;
import net.minecraft.client.AbstractOption;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AccessibilityScreen;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.NonNullList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.List;

@Mod(MelonyLib.MOD_ID)
public class MelonyLib {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "melonylib";

    public MelonyLib() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
        MLSounds.SOUNDS.register(eventBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MLConfigs.COMMON_SPEC, "jtw-mods/melonylib-common.toml");
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> (DistExecutor.SafeRunnable) LibUtils::addHighContrastResourcePack);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        MLOperatorUtilitiesTab.init();

        MLOptions.MONOCHROME_LOGO.set(Minecraft.getInstance().options, MLConfigs.COMMON_CONFIGS.monochromeMojangLogo.get() ? "true" : "false");
        MLOptions.HIDE_SPLASH_TEXTS.set(Minecraft.getInstance().options, MLConfigs.COMMON_CONFIGS.hideSplashTexts.get() ? "true" : "false");
        MLOptions.HIGH_CONTRAST_BLOCK_OUTLINES.set(Minecraft.getInstance().options, MLConfigs.COMMON_CONFIGS.highContrastBlockOutlines.get() ? "true" : "false");
        MLOptions.DAMAGE_TILT.set(Minecraft.getInstance().options, MLConfigs.COMMON_CONFIGS.damageTilt.get());

        try {
            List<AbstractOption> options = Lists.newArrayList(AbstractOption.NARRATOR, AbstractOption.SHOW_SUBTITLES, AbstractOption.TEXT_BACKGROUND_OPACITY,
                    AbstractOption.TEXT_BACKGROUND, AbstractOption.CHAT_OPACITY, AbstractOption.CHAT_LINE_SPACING, AbstractOption.CHAT_DELAY, AbstractOption.AUTO_JUMP,
                    AbstractOption.TOGGLE_CROUCH, AbstractOption.TOGGLE_SPRINT, AbstractOption.SCREEN_EFFECTS_SCALE, AbstractOption.FOV_EFFECTS_SCALE,
                    MLOptions.DAMAGE_TILT, MLOptions.MONOCHROME_LOGO, MLOptions.HIDE_SPLASH_TEXTS, MLOptions.HIGH_CONTRAST_BLOCK_OUTLINES);
            AccessibilityScreen.OPTIONS = options.toArray(new AbstractOption[0]);
        } catch (Exception exception) {
            LOGGER.error("Failed to add \"Monochrome Logo\" option to accessibility screen", exception);
        }
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ItemGroup.TAB_FOOD = new ItemGroup(7, MOD_ID + ".food_and_drinks") {
            @Override
            @Nonnull
            public ItemStack makeIcon() {
                return new ItemStack(Items.GOLDEN_APPLE);
            }

            @Override
            public void fillItemList(NonNullList<ItemStack> list) {
                TabUtils.compileFoodAndDrinksTab(list);
                for (Potion potion : ForgeRegistries.POTION_TYPES) {
                    if (potion != Potions.EMPTY) list.add(PotionUtils.setPotion(new ItemStack(Items.POTION), potion));
                }
                for (Potion potion : ForgeRegistries.POTION_TYPES) {
                    if (potion != Potions.EMPTY) list.add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), potion));
                }
                for (Potion potion : ForgeRegistries.POTION_TYPES) {
                    if (potion != Potions.EMPTY) list.add(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), potion));
                }
            }
        };

        ItemModelsProperties.register(Items.BEE_NEST, LibUtils.melony("honey_level"), (stack, world, livEntity) -> {
            CompoundNBT tag = stack.getTag();
            if (tag != null && tag.contains("BlockStateTag", Constants.NBT.TAG_COMPOUND)) {
                CompoundNBT blockStateTag = tag.getCompound("BlockStateTag");
                if (blockStateTag.contains("honey_level", Constants.NBT.TAG_INT)) return blockStateTag.getInt("honey_level");
            }
            return 0;
        });
        ItemModelsProperties.register(Items.BEEHIVE, LibUtils.melony("honey_level"), (stack, world, livEntity) -> {
            CompoundNBT tag = stack.getTag();
            if (tag != null && tag.contains("BlockStateTag", Constants.NBT.TAG_COMPOUND)) {
                CompoundNBT blockStateTag = tag.getCompound("BlockStateTag");
                if (blockStateTag.contains("honey_level", Constants.NBT.TAG_INT)) return blockStateTag.getInt("honey_level");
            }
            return 0;
        });
    }
}
