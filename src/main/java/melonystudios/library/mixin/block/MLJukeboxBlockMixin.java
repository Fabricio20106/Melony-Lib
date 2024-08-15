package melonystudios.library.mixin.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.JukeboxBlock;
import net.minecraft.block.SoundType;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.JukeboxTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import org.spongepowered.asm.mixin.Mixin;

import javax.annotation.Nullable;

@Mixin(JukeboxBlock.class)
@SuppressWarnings("deprecation")
public class MLJukeboxBlockMixin extends Block {
    public MLJukeboxBlockMixin(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    public int getSignal(BlockState state, IBlockReader world, BlockPos pos, Direction direction) {
        int signalStrength = 0;
        TileEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof JukeboxTileEntity) {
            JukeboxTileEntity jukebox = (JukeboxTileEntity) blockEntity;
            if (!jukebox.getRecord().isEmpty()) {
                signalStrength = 15;
                jukebox.setChanged();
                return signalStrength;
            }
        }
        return signalStrength;
    }

    @Override
    public SoundType getSoundType(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity) {
        return SoundType.WOOD;
    }
}
