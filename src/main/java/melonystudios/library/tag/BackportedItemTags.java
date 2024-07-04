package melonystudios.library.tag;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class BackportedItemTags {
    public static final ITag.INamedTag<Item> WART_BLOCKS = minecraft("wart_blocks");
    public static final ITag.INamedTag<Item> CREEPER_IGNITERS = minecraft("creeper_igniters");

    public static ITag.INamedTag<Item> minecraft(String name) {
        return ItemTags.bind(new ResourceLocation("minecraft", name).toString());
    }
}
