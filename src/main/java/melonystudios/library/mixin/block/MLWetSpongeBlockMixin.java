package melonystudios.library.mixin.block;

import melonystudios.library.misc.MLSoundTypes;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import org.spongepowered.asm.mixin.Mixin;

import javax.annotation.Nullable;

@Mixin(WetSpongeBlock.class)
public class MLWetSpongeBlockMixin extends Block {
    public MLWetSpongeBlockMixin(Properties properties) {
        super(properties);
    }

    @Override
    public SoundType getSoundType(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity) {
        return MLSoundTypes.WET_SPONGE;
    }
}
