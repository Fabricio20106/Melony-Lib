package melonystudios.library.mixin.block;

import melonystudios.library.misc.MLSoundTypes;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import org.spongepowered.asm.mixin.Mixin;

import javax.annotation.Nullable;

@Mixin(WebBlock.class)
public class MLCobwebBlockMixin extends Block {
    public MLCobwebBlockMixin(Properties properties) {
        super(properties);
    }

    @Override
    public SoundType getSoundType(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity) {
        return MLSoundTypes.COBWEB;
    }
}
