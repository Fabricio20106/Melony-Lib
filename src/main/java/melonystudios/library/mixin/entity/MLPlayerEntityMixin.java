package melonystudios.library.mixin.entity;

import melonystudios.library.tag.BackportedBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerEntity.class)
public abstract class MLPlayerEntityMixin extends Entity {
    public MLPlayerEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Unique
    protected void waterSwimSound() {
        Entity controllingPassenger = this.getControllingPassenger() != null ? this.getControllingPassenger() : this;
        float speed = controllingPassenger == this ? 0.35F : 0.4F;
        Vector3d deltaMovement = controllingPassenger.getDeltaMovement();
        float volume = Math.min(1, (float) Math.sqrt(deltaMovement.x * deltaMovement.x * 0.2F + deltaMovement.y * deltaMovement.y + deltaMovement.z * deltaMovement.z * 0.2F) * speed);
        this.playSwimSound(volume);
    }

    @Unique
    protected BlockPos getPrimaryStepSoundBlockPos(BlockPos pos) {
        BlockPos blockpos = pos.above();
        BlockState blockstate = this.level.getBlockState(blockpos);
        return !blockstate.is(BackportedBlockTags.INSIDE_STEP_SOUND_BLOCKS) && !blockstate.is(BackportedBlockTags.COMBINATION_STEP_SOUND_BLOCKS) ? pos : blockpos;
    }

    @Unique
    protected void playCombinationStepSounds(BlockState primaryState, BlockState secondaryState, BlockPos primaryPos, BlockPos secondaryPos) {
        SoundType soundType = primaryState.getSoundType(this.level, primaryPos, this);
        this.playSound(soundType.getStepSound(), soundType.getVolume() * 0.15F, soundType.getPitch());
        this.playMuffledStepSound(secondaryState, secondaryPos);
    }

    @Unique
    protected void playMuffledStepSound(BlockState state, BlockPos pos) {
        SoundType soundType = state.getSoundType(this.level, pos, this);
        this.playSound(soundType.getStepSound(), soundType.getVolume() * 0.05F, soundType.getPitch() * 0.8F);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        if (this.isInWater()) {
            this.waterSwimSound();
            this.playMuffledStepSound(state, pos);
        } else {
            BlockPos primaryPos = this.getPrimaryStepSoundBlockPos(pos);
            if (!primaryPos.equals(pos)) {
                BlockState primaryState = this.level.getBlockState(primaryPos);
                if (primaryState.is(BackportedBlockTags.COMBINATION_STEP_SOUND_BLOCKS)) {
                    this.playCombinationStepSounds(primaryState, state, primaryPos, pos);
                } else {
                    super.playStepSound(primaryPos, primaryState);
                }
            } else {
                super.playStepSound(pos, state);
            }
        }
    }
}
