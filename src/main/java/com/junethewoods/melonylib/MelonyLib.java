package com.junethewoods.melonylib;

import com.junethewoods.melonylib.util.TabUtils;
import com.junethewoods.melonylib.util.tab.MLOperatorUtilitiesTab;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.NonNullList;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(MelonyLib.MOD_ID)
public class MelonyLib {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "melonylib";

    public MelonyLib() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        MLOperatorUtilitiesTab.init();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ItemGroup.TAB_FOOD = new ItemGroup(7, MOD_ID + ".food_and_drinks") {
            @Override
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
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {}
}
