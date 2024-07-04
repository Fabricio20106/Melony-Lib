package melonystudios.library.tag;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class ConventionItemTags {
    public static final ITag.INamedTag<Item> VILLAGER_WANTED_ITEMS = melony("villager_wanted_items");
    public static final ITag.INamedTag<Item> VILLAGER_PLANTABLE_SEEDS = melony("villager_plantable_seeds");
    public static final ITag.INamedTag<Item> COMPLETES_FIND_TREE_TUTORIAL = melony("completes_find_tree_tutorial");
    public static final Tags.IOptionalNamedTag<Item> WITH_COMMON_RARITY = melonyOptional("with_rarity/common");
    public static final Tags.IOptionalNamedTag<Item> WITH_UNCOMMON_RARITY = melonyOptional("with_rarity/uncommon");
    public static final Tags.IOptionalNamedTag<Item> WITH_RARE_RARITY = melonyOptional("with_rarity/rare");
    public static final Tags.IOptionalNamedTag<Item> WITH_EPIC_RARITY = melonyOptional("with_rarity/epic");

    // Forge tags
    public static final ITag.INamedTag<Item> ELYTRA = forge("elytra");

    public static ITag.INamedTag<Item> melony(String name) {
        return ItemTags.bind(new ResourceLocation("melony", name).toString());
    }

    public static Tags.IOptionalNamedTag<Item> melonyOptional(String name) {
        return ItemTags.createOptional(new ResourceLocation("melony", name));
    }

    public static ITag.INamedTag<Item> forge(String name) {
        return ItemTags.bind(new ResourceLocation("forge", name).toString());
    }
}
