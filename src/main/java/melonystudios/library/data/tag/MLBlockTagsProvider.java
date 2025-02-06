package melonystudios.library.data.tag;

import melonystudios.library.MelonyLib;
import melonystudios.library.tag.BackportedBlockTags;
import melonystudios.library.tag.ConventionBlockTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

import static melonystudios.library.util.LibUtils.theMato;
import static melonystudios.library.util.LibUtils.variants;

public class MLBlockTagsProvider extends BlockTagsProvider {
    public MLBlockTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper fileHelper) {
        super(generator, MelonyLib.MOD_ID, fileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(ConventionBlockTags.MINEABLE_SHEARS).addTag(BlockTags.LEAVES).addTag(BlockTags.WOOL).add(Blocks.COBWEB, Blocks.GRASS, Blocks.FERN, Blocks.DEAD_BUSH, Blocks.VINE, Blocks.TRIPWIRE, Blocks.REDSTONE_WIRE);
        this.tag(ConventionBlockTags.COMPLETES_FIND_TREE_TUTORIAL).addTag(BlockTags.LOGS).addTag(BlockTags.LEAVES).addTag(BlockTags.WART_BLOCKS);

        // Backported Tags
        this.tag(BackportedBlockTags.INSIDE_STEP_SOUND_BLOCKS).add(Blocks.LILY_PAD).addOptional(theMato("powder_snow"));
        this.tag(BackportedBlockTags.COMBINATION_STEP_SOUND_BLOCKS).addTag(BlockTags.CARPETS).add(Blocks.SNOW, Blocks.NETHER_SPROUTS, Blocks.WARPED_ROOTS, Blocks.CRIMSON_ROOTS).addOptional(variants("ender_roots"))
                .addOptional(variants("end_sprouts"));
    }
}
