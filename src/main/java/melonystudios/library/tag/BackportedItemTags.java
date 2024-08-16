package melonystudios.library.tag;

import melonystudios.library.util.LibUtils;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

public class BackportedItemTags {
    public static final ITag.INamedTag<Item> WART_BLOCKS = minecraft("wart_blocks");
    public static final ITag.INamedTag<Item> CREEPER_IGNITERS = minecraft("creeper_igniters");
    public static final ITag.INamedTag<Item> FURNACE_MINECART_FUEL = minecraft("furnace_minecart_fuel");

    public static ITag.INamedTag<Item> minecraft(String name) {
        return ItemTags.bind(LibUtils.minecraft(name).toString());
    }
}
