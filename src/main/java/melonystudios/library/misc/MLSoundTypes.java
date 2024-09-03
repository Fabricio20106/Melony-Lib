package melonystudios.library.misc;

import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.util.ForgeSoundType;

public class MLSoundTypes {
    public static final ForgeSoundType SPAWNER = new ForgeSoundType(1, 1, MLSounds.SPAWNER_BREAK, MLSounds.SPAWNER_STEP, MLSounds.SPAWNER_PLACE, MLSounds.SPAWNER_HIT, MLSounds.SPAWNER_HIT);
    public static final ForgeSoundType COBWEB = new ForgeSoundType(1, 1, MLSounds.COBWEB_BREAK, MLSounds.COBWEB_STEP, MLSounds.COBWEB_PLACE, MLSounds.COBWEB_HIT, MLSounds.COBWEB_FALL);
    public static final ForgeSoundType SPONGE = new ForgeSoundType(1, 1, MLSounds.SPONGE_BREAK, MLSounds.SPONGE_STEP, MLSounds.SPONGE_PLACE, MLSounds.SPONGE_HIT, MLSounds.SPONGE_FALL);
    public static final ForgeSoundType WET_SPONGE = new ForgeSoundType(1, 1, MLSounds.WET_SPONGE_BREAK, MLSounds.WET_SPONGE_STEP, MLSounds.WET_SPONGE_PLACE, MLSounds.WET_SPONGE_HIT, MLSounds.WET_SPONGE_FALL);
    public static final ForgeSoundType VINE = new ForgeSoundType(1, 1, MLSounds.VINE_BREAK, () -> SoundEvents.VINE_STEP, MLSounds.VINE_PLACE, MLSounds.VINE_HIT, MLSounds.VINE_FALL);
}
