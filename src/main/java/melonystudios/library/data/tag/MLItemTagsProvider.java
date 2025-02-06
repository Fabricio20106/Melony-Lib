package melonystudios.library.data.tag;

import melonystudios.library.MelonyLib;
import melonystudios.library.tag.ConventionItemTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

import static melonystudios.library.tag.BackportedItemTags.*;
import static melonystudios.library.util.LibUtils.*;

public class MLItemTagsProvider extends ItemTagsProvider {
    public MLItemTagsProvider(DataGenerator generator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper fileHelper) {
        super(generator, blockTagsProvider, MelonyLib.MOD_ID, fileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(ConventionItemTags.PIGLIN_CURRENCY).addTag(Tags.Items.INGOTS_GOLD);
        this.tag(ConventionItemTags.CROSSBOWS).add(Items.CROSSBOW);
        this.tag(ConventionItemTags.VILLAGER_PLANTABLE_SEEDS).add(Items.WHEAT_SEEDS, Items.CARROT, Items.POTATO, Items.BEETROOT_SEEDS);
        this.tag(ConventionItemTags.VILLAGER_WANTED_ITEMS).addTag(ConventionItemTags.VILLAGER_PLANTABLE_SEEDS).add(Items.WHEAT, Items.BREAD, Items.BEETROOT);
        this.tag(ConventionItemTags.COMPLETES_FIND_TREE_TUTORIAL).addTag(ItemTags.LOGS).addTag(ItemTags.LEAVES).addTag(WART_BLOCKS);
        this.tag(ConventionItemTags.WITH_COMMON_RARITY).add(Items.END_CRYSTAL, Items.GOLDEN_APPLE);
        this.tag(ConventionItemTags.WITH_UNCOMMON_RARITY).add(Items.PIGLIN_BANNER_PATTERN, Items.CHAINMAIL_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, Items.CHAINMAIL_BOOTS, Items.NAUTILUS_SHELL,
                Items.CONDUIT, Items.MUSIC_DISC_13, Items.MUSIC_DISC_CAT, Items.MUSIC_DISC_BLOCKS, Items.MUSIC_DISC_CHIRP, Items.MUSIC_DISC_FAR, Items.MUSIC_DISC_MALL, Items.MUSIC_DISC_MELLOHI, Items.MUSIC_DISC_STAL, Items.MUSIC_DISC_STRAD,
                Items.MUSIC_DISC_WARD, Items.MUSIC_DISC_11, Items.MUSIC_DISC_WAIT);
        this.tag(ConventionItemTags.WITH_RARE_RARITY).add(Items.ENCHANTED_GOLDEN_APPLE, Items.TRIDENT, Items.NETHER_STAR, Items.WITHER_SKELETON_SKULL, Items.SKULL_BANNER_PATTERN, Items.MOJANG_BANNER_PATTERN);
        this.tag(ConventionItemTags.WITH_EPIC_RARITY).add(Items.ELYTRA, Items.DRAGON_HEAD, Items.BARRIER, Items.STRUCTURE_VOID, Items.COMMAND_BLOCK_MINECART, Items.DEBUG_STICK, Items.KNOWLEDGE_BOOK).addOptional(
                variants("debug_bow")).addOptional(variants("debug_arrow")).addOptional(variants("enchanted_knowledge_book"));
        this.tag(ConventionItemTags.WITH_POTATO_RARITY);

        // Forge Tags
        this.tag(ConventionItemTags.ELYTRA).add(Items.ELYTRA);

        // Backported Tags
        this.tag(WART_BLOCKS).add(Items.NETHER_WART_BLOCK, Items.WARPED_WART_BLOCK);
        this.tag(CREEPER_IGNITERS).add(Items.FLINT_AND_STEEL, Items.FIRE_CHARGE).addOptional(variants("soul_charge"));
        this.tag(FURNACE_MINECART_FUEL).addTag(ItemTags.COALS);
        this.tag(IGNORED_BY_PIGLIN_BABIES).addTag(Tags.Items.LEATHER);
        this.tag(MEAT).add(Items.BEEF).add(Items.PORKCHOP).add(Items.MUTTON).add(Items.CHICKEN).add(Items.RABBIT).add(Items.COOKED_BEEF).add(Items.COOKED_PORKCHOP).add(Items.COOKED_MUTTON).add(Items.COOKED_CHICKEN).add(Items.COOKED_RABBIT)
                .add(Items.ROTTEN_FLESH).addOptional(backMath("aljamic_beef")).addOptional(backMath("aljamic_porkchop")).addOptional(backMath("aljamic_mutton")).addOptional(backMath("aljamic_chicken"))
                .addOptional(backMath("cooked_aljamic_beef")).addOptional(backMath("cooked_aljamic_porkchop")).addOptional(backMath("cooked_aljamic_mutton")).addOptional(backMath("cooked_aljamic_chicken"))
                .addOptional(backMath("rotten_aljamic_flesh")).addOptional(backMath("mortadella"));
        this.tag(PIGLIN_FOOD).add(Items.PORKCHOP, Items.COOKED_PORKCHOP);
        this.tag(WOLF_FOOD).addTag(MEAT).add(Items.COD).add(Items.SALMON).add(Items.COOKED_COD).add(Items.COOKED_SALMON).add(Items.TROPICAL_FISH).add(Items.PUFFERFISH).add(Items.RABBIT_STEW).addOptional(variants("fish"))
                .addOptional(variants("cooked_fish")).addOptional(variants("exponential_rabbit_stew"));
        this.tag(FOX_FOOD).add(Items.SWEET_BERRIES).addOptional(new ResourceLocation("cavesandcliffs", "glow_berries")).addOptional(backMath("aljamic_berry"));
    }
}
