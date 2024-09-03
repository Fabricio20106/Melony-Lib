package melonystudios.library;

import melonystudios.library.config.MLConfigs;
import melonystudios.library.misc.MLSounds;
import melonystudios.library.util.LibUtils;
import melonystudios.library.util.TabUtils;
import melonystudios.library.util.tab.MLOperatorUtilitiesTab;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.eventbus.api.IEventBus;
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
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        MLOperatorUtilitiesTab.init();
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
