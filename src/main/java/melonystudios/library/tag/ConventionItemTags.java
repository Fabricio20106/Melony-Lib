package melonystudios.library.tag;

import melonystudios.library.util.LibUtils;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;

public class ConventionItemTags {
    // Melony Tags
    public static final ITag.INamedTag<Item> CROSSBOWS = melony("crossbows");
    public static final ITag.INamedTag<Item> PIGLIN_CURRENCY = melony("piglin_currency");
    public static final ITag.INamedTag<Item> VILLAGER_WANTED_ITEMS = melony("villager_wanted_items");
    public static final ITag.INamedTag<Item> VILLAGER_PLANTABLE_SEEDS = melony("villager_plantable_seeds");
    public static final ITag.INamedTag<Item> COMPLETES_FIND_TREE_TUTORIAL = melony("completes_find_tree_tutorial");
    public static final Tags.IOptionalNamedTag<Item> WITH_COMMON_RARITY = melonyOptional("with_rarity/common");
    public static final Tags.IOptionalNamedTag<Item> WITH_UNCOMMON_RARITY = melonyOptional("with_rarity/uncommon");
    public static final Tags.IOptionalNamedTag<Item> WITH_RARE_RARITY = melonyOptional("with_rarity/rare");
    public static final Tags.IOptionalNamedTag<Item> WITH_EPIC_RARITY = melonyOptional("with_rarity/epic");
    public static final Tags.IOptionalNamedTag<Item> WITH_POTATO_RARITY = melonyOptional("with_rarity/potato");

    // Forge Tags
    public static final ITag.INamedTag<Item> ELYTRA = forge("elytra");

    public static ITag.INamedTag<Item> melony(String name) {
        return ItemTags.bind(LibUtils.melony(name).toString());
    }

    public static Tags.IOptionalNamedTag<Item> melonyOptional(String name) {
        return ItemTags.createOptional(LibUtils.melony(name));
    }

    public static ITag.INamedTag<Item> forge(String name) {
        return ItemTags.bind(LibUtils.forge(name).toString());
    }
}
