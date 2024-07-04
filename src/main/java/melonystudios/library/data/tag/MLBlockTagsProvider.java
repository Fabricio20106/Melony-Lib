package melonystudios.library.data.tag;

import melonystudios.library.MelonyLib;
import melonystudios.library.tag.ConventionBlockTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class MLBlockTagsProvider extends BlockTagsProvider {
    public MLBlockTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper fileHelper) {
        super(generator, MelonyLib.MOD_ID, fileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(ConventionBlockTags.COMPLETES_FIND_TREE_TUTORIAL).addTag(BlockTags.LOGS).addTag(BlockTags.LEAVES).addTag(BlockTags.WART_BLOCKS);
    }
}
