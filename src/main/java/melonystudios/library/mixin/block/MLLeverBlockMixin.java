package melonystudios.library.mixin.block;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import org.spongepowered.asm.mixin.Mixin;

import javax.annotation.Nullable;

@Mixin(LeverBlock.class)
public class MLLeverBlockMixin extends Block {
    public MLLeverBlockMixin(Properties properties) {
        super(properties);
    }

    @Override
    public SoundType getSoundType(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity) {
        return state.getBlock().is(Blocks.LEVER) ? SoundType.STONE : super.getSoundType(state, world, pos, entity);
    }
}
