package melonystudios.library.data.tag;

import melonystudios.library.MelonyLib;
import melonystudios.library.tag.ConventionItemTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

import static melonystudios.library.MelonyLib.variants;
import static melonystudios.library.tag.BackportedItemTags.*;

public class MLItemTagsProvider extends ItemTagsProvider {
    public MLItemTagsProvider(DataGenerator generator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper fileHelper) {
        super(generator, blockTagsProvider, MelonyLib.MOD_ID, fileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(ConventionItemTags.VILLAGER_PLANTABLE_SEEDS).add(Items.WHEAT_SEEDS).add(Items.CARROT).add(Items.POTATO).add(Items.BEETROOT_SEEDS);
        this.tag(ConventionItemTags.VILLAGER_WANTED_ITEMS).addTag(ConventionItemTags.VILLAGER_PLANTABLE_SEEDS).add(Items.WHEAT).add(Items.BREAD).add(Items.BEETROOT);
        this.tag(ConventionItemTags.COMPLETES_FIND_TREE_TUTORIAL).addTag(ItemTags.LOGS).addTag(ItemTags.LEAVES).addTag(WART_BLOCKS);
        this.tag(ConventionItemTags.WITH_COMMON_RARITY);
        this.tag(ConventionItemTags.WITH_UNCOMMON_RARITY).add(Items.PIGLIN_BANNER_PATTERN);
        this.tag(ConventionItemTags.WITH_RARE_RARITY);
        this.tag(ConventionItemTags.WITH_EPIC_RARITY).add(Items.BARRIER).add(Items.STRUCTURE_VOID).add(Items.COMMAND_BLOCK_MINECART).add(Items.DEBUG_STICK).add(Items.KNOWLEDGE_BOOK).add(Items.TRIDENT).addOptional(
                variants("debug_bow")).addOptional(variants("enchanted_knowledge_book"));

        // Forge tags
        this.tag(ConventionItemTags.ELYTRA).add(Items.ELYTRA);

        // Backported Tags
        this.tag(WART_BLOCKS).add(Items.NETHER_WART_BLOCK).add(Items.WARPED_WART_BLOCK);
        this.tag(CREEPER_IGNITERS).add(Items.FLINT_AND_STEEL).add(Items.FIRE_CHARGE).addOptional(variants("soul_charge"));
    }
}
