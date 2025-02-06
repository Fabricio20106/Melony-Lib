package melonystudios.library.tag;

import melonystudios.library.util.LibUtils;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;

public class ConventionBlockTags {
    public static final ITag.INamedTag<Block> COMPLETES_FIND_TREE_TUTORIAL = melony("completes_find_tree_tutorial");
    public static final ITag.INamedTag<Block> MINEABLE_SHEARS = melony("mineable/shears");

    public static ITag.INamedTag<Block> melony(String name) {
        return BlockTags.bind(LibUtils.melony(name).toString());
    }
}
