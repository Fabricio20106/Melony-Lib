package melonystudios.library.tag;

import melonystudios.library.util.LibUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;

public class ConventionEntityTypeTags {
    public static final ITag.INamedTag<EntityType<?>> PIGLINS_AFRAID_OF = melony("piglins_afraid_of");

    public static ITag.INamedTag<EntityType<?>> melony(String name) {
        return EntityTypeTags.bind(LibUtils.melony(name).toString());
    }
}
