package melonystudios.library.data.tag;

import melonystudios.library.MelonyLib;
import melonystudios.library.tag.ConventionEntityTypeTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.EntityTypeTagsProvider;
import net.minecraft.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class MLEntityTypeTagsProvider extends EntityTypeTagsProvider {
    public MLEntityTypeTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper fileHelper) {
        super(generator, MelonyLib.MOD_ID, fileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(ConventionEntityTypeTags.PIGLINS_AFRAID_OF).add(EntityType.ZOMBIFIED_PIGLIN, EntityType.ZOGLIN);
    }
}
