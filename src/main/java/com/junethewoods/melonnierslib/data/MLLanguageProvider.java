package com.junethewoods.melonnierslib.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public abstract class MLLanguageProvider extends LanguageProvider {
    public MLLanguageProvider(DataGenerator gen, String modId, String locale) {
        super(gen, modId, locale);
    }

    public void newToolSet(String modId, String registryName, String translationName, boolean hasGreatsword) {
        add("item." + modId + "." + registryName + "_sword", translationName + " Sword");
        add("item." + modId + "." + registryName + "_pickaxe", translationName + " Pickaxe");
        add("item." + modId + "." + registryName + "_shovel", translationName + " Shovel");
        add("item." + modId + "." + registryName + "_axe", translationName + " Axe");
        add("item." + modId + "." + registryName + "_hoe", translationName + " Hoe");
        add("item." + modId + "." + "milked_" + registryName + "_sword", "Milked " + translationName + " Sword");
        if (hasGreatsword) {
            add("item." + modId + "." + registryName + "_greatsword", translationName + " Greatsword");
        }
    }

    public void newArmorSet(String modId, String registryName, String translationName, boolean isBreastplate) {
        add("item." + modId + "." + registryName + "_helmet", translationName + " Helmet");
        if (isBreastplate) {
            add("item." + modId + "." + registryName + "_breastplate", translationName + " Breastplate");
        } else {
            add("item." + modId + "." + registryName + "_chestplate", translationName + " Chestplate");
        }
        add("item." + modId + "." + registryName + "_leggings", translationName + " Leggings");
        add("item." + modId + "." + registryName + "_boots", translationName + " Boots");
        add("item." + modId + "." + registryName + "_warrior_helmet", translationName + " Warrior Helmet");
    }
}
