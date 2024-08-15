package melonystudios.library.tag;

import melonystudios.library.util.LibUtils;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;

public class BackportedBlockTags {
    public static final ITag.INamedTag<Block> INSIDE_STEP_SOUND_BLOCKS = minecraft("inside_step_sound_blocks");
    public static final ITag.INamedTag<Block> COMBINATION_STEP_SOUND_BLOCKS = minecraft("combination_step_sound_blocks");

    public static ITag.INamedTag<Block> minecraft(String name) {
        return BlockTags.bind(LibUtils.minecraft(name).toString());
    }
}
