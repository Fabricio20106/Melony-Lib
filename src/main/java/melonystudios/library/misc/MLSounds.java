package melonystudios.library.misc;

import melonystudios.library.MelonyLib;
import melonystudios.library.util.LibUtils;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MLSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MelonyLib.MOD_ID);

    public static final RegistryObject<SoundEvent> SPAWNER_PLACE = SOUNDS.register("block.spawner.place", () -> new SoundEvent(LibUtils.melonyLib("block.spawner.place")));
    public static final RegistryObject<SoundEvent> SPAWNER_BREAK = SOUNDS.register("block.spawner.break", () -> new SoundEvent(LibUtils.melonyLib("block.spawner.break")));
    public static final RegistryObject<SoundEvent> SPAWNER_HIT = SOUNDS.register("block.spawner.hit", () -> new SoundEvent(LibUtils.melonyLib("block.spawner.hit")));
    public static final RegistryObject<SoundEvent> SPAWNER_STEP = SOUNDS.register("block.spawner.step", () -> new SoundEvent(LibUtils.melonyLib("block.spawner.step")));

    public static final RegistryObject<SoundEvent> COBWEB_PLACE = SOUNDS.register("block.cobweb.place", () -> new SoundEvent(LibUtils.melonyLib("block.cobweb.place")));
    public static final RegistryObject<SoundEvent> COBWEB_BREAK = SOUNDS.register("block.cobweb.break", () -> new SoundEvent(LibUtils.melonyLib("block.cobweb.break")));
    public static final RegistryObject<SoundEvent> COBWEB_HIT = SOUNDS.register("block.cobweb.hit", () -> new SoundEvent(LibUtils.melonyLib("block.cobweb.hit")));
    public static final RegistryObject<SoundEvent> COBWEB_FALL = SOUNDS.register("block.cobweb.fall", () -> new SoundEvent(LibUtils.melonyLib("block.cobweb.fall")));
    public static final RegistryObject<SoundEvent> COBWEB_STEP = SOUNDS.register("block.cobweb.step", () -> new SoundEvent(LibUtils.melonyLib("block.cobweb.step")));

    public static final RegistryObject<SoundEvent> SPONGE_PLACE = SOUNDS.register("block.sponge.place", () -> new SoundEvent(LibUtils.melonyLib("block.sponge.place")));
    public static final RegistryObject<SoundEvent> SPONGE_BREAK = SOUNDS.register("block.sponge.break", () -> new SoundEvent(LibUtils.melonyLib("block.sponge.break")));
    public static final RegistryObject<SoundEvent> SPONGE_HIT = SOUNDS.register("block.sponge.hit", () -> new SoundEvent(LibUtils.melonyLib("block.sponge.hit")));
    public static final RegistryObject<SoundEvent> SPONGE_FALL = SOUNDS.register("block.sponge.fall", () -> new SoundEvent(LibUtils.melonyLib("block.sponge.fall")));
    public static final RegistryObject<SoundEvent> SPONGE_STEP = SOUNDS.register("block.sponge.step", () -> new SoundEvent(LibUtils.melonyLib("block.sponge.step")));

    public static final RegistryObject<SoundEvent> WET_SPONGE_PLACE = SOUNDS.register("block.wet_sponge.place", () -> new SoundEvent(LibUtils.melonyLib("block.wet_sponge.place")));
    public static final RegistryObject<SoundEvent> WET_SPONGE_BREAK = SOUNDS.register("block.wet_sponge.break", () -> new SoundEvent(LibUtils.melonyLib("block.wet_sponge.break")));
    public static final RegistryObject<SoundEvent> WET_SPONGE_HIT = SOUNDS.register("block.wet_sponge.hit", () -> new SoundEvent(LibUtils.melonyLib("block.wet_sponge.hit")));
    public static final RegistryObject<SoundEvent> WET_SPONGE_FALL = SOUNDS.register("block.wet_sponge.fall", () -> new SoundEvent(LibUtils.melonyLib("block.wet_sponge.fall")));
    public static final RegistryObject<SoundEvent> WET_SPONGE_STEP = SOUNDS.register("block.wet_sponge.step", () -> new SoundEvent(LibUtils.melonyLib("block.wet_sponge.step")));

    public static final RegistryObject<SoundEvent> VINE_PLACE = SOUNDS.register("block.vine.place", () -> new SoundEvent(LibUtils.melonyLib("block.vine.place")));
    public static final RegistryObject<SoundEvent> VINE_BREAK = SOUNDS.register("block.vine.break", () -> new SoundEvent(LibUtils.melonyLib("block.vine.break")));
    public static final RegistryObject<SoundEvent> VINE_HIT = SOUNDS.register("block.vine.hit", () -> new SoundEvent(LibUtils.melonyLib("block.vine.hit")));
    public static final RegistryObject<SoundEvent> VINE_FALL = SOUNDS.register("block.vine.fall", () -> new SoundEvent(LibUtils.melonyLib("block.vine.fall")));
}
