package melonystudios.library.tag;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;

public class ConventionBlockTags {
    public static final ITag.INamedTag<Block> COMPLETES_FIND_TREE_TUTORIAL = melony("completes_find_tree_tutorial");

    public static ITag.INamedTag<Block> melony(String name) {
        return BlockTags.bind(new ResourceLocation("melony", name).toString());
    }
}
