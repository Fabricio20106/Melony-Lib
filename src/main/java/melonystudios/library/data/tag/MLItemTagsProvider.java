package com.junethewoods.melonylib.data.tag;

import com.junethewoods.melonylib.MelonyLib;
import com.junethewoods.melonylib.util.ConventionItemTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class MLItemTagsProvider extends ItemTagsProvider {
    public MLItemTagsProvider(DataGenerator generator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper fileHelper) {
        super(generator, blockTagsProvider, MelonyLib.MOD_ID, fileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(ConventionItemTags.VILLAGER_PLANTABLE_SEEDS).add(Items.WHEAT_SEEDS).add(Items.CARROT).add(Items.POTATO).add(Items.BEETROOT_SEEDS);
        this.tag(ConventionItemTags.VILLAGER_WANTED_ITEMS).addTag(ConventionItemTags.VILLAGER_PLANTABLE_SEEDS).add(Items.WHEAT).add(Items.BREAD).add(Items.BEETROOT);
        this.tag(ConventionItemTags.COMPLETES_FIND_TREE_TUTORIAL).addTag(ItemTags.LOGS).addTag(ItemTags.LEAVES).addTag(ConventionItemTags.WART_BLOCKS);

        this.tag(ConventionItemTags.WART_BLOCKS).add(Items.NETHER_WART_BLOCK).add(Items.WARPED_WART_BLOCK);
    }
}
