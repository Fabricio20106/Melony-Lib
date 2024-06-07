package com.junethewoods.melonylib.util;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class ConventionItemTags {
    public static final ITag.INamedTag<Item> VILLAGER_WANTED_ITEMS = melony("villager_wanted_items");
    public static final ITag.INamedTag<Item> VILLAGER_PLANTABLE_SEEDS = melony("villager_plantable_seeds");
    public static final ITag.INamedTag<Item> COMPLETES_FIND_TREE_TUTORIAL = melony("completes_find_tree_tutorial");

    // Minecraft Tags
    public static final ITag.INamedTag<Item> WART_BLOCKS = minecraft("wart_blocks");

    public static ITag.INamedTag<Item> melony(String name) {
        return ItemTags.bind(new ResourceLocation("melony", name).toString());
    }

    public static ITag.INamedTag<Item> minecraft(String name) {
        return ItemTags.bind(new ResourceLocation("minecraft", name).toString());
    }
}
