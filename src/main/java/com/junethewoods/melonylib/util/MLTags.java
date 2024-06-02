package com.junethewoods.melonylib.util;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class MLTags {
    public static class Items {
        public static final ITag.INamedTag<Item> BONE_MEALS = forge("bone_meals");

        public static ITag.INamedTag<Item> forge(String name) {
            return ItemTags.bind(new ResourceLocation("forge", name).toString());
        }
    }
}
