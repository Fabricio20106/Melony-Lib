package melonystudios.library.mixin.blockentity;

import net.minecraft.block.JukeboxBlock;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.JukeboxTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(JukeboxTileEntity.class)
public class MLJukeboxTileEntityMixin extends TileEntity implements ITickableTileEntity {
    public MLJukeboxTileEntityMixin(TileEntityType<?> type) {
        super(type);
    }

    @Override
    public void tick() {
        World world = this.level;
        if (world != null && world.isClientSide && this.getBlockState().getValue(JukeboxBlock.HAS_RECORD)) {
            if (world.getGameTime() % 20 == 0) {
                float rand1 = world.getRandom().nextInt(4) / 24F;
                world.addParticle(ParticleTypes.NOTE, this.getBlockPos().getX() + 0.5, this.getBlockPos().getY() + 1.2, this.getBlockPos().getZ() + 0.5, 0, rand1, 0);
            }
        }
    }
}
