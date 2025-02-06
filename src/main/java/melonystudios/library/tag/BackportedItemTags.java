package melonystudios.library.tag;

import melonystudios.library.util.LibUtils;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

public class BackportedItemTags {
    public static final ITag.INamedTag<Item> WART_BLOCKS = minecraft("wart_blocks");
    public static final ITag.INamedTag<Item> CREEPER_IGNITERS = minecraft("creeper_igniters");
    public static final ITag.INamedTag<Item> FURNACE_MINECART_FUEL = minecraft("furnace_minecart_fuel");
    public static final ITag.INamedTag<Item> IGNORED_BY_PIGLIN_BABIES = minecraft("ignored_by_piglin_babies");
    public static final ITag.INamedTag<Item> MEAT = minecraft("meat");
    public static final ITag.INamedTag<Item> PIGLIN_FOOD = minecraft("piglin_food");
    public static final ITag.INamedTag<Item> WOLF_FOOD = minecraft("wolf_food");
    public static final ITag.INamedTag<Item> FOX_FOOD = minecraft("fox_food");

    public static ITag.INamedTag<Item> minecraft(String name) {
        return ItemTags.bind(LibUtils.minecraft(name).toString());
    }
}
