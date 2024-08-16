package melonystudios.library.event;

import melonystudios.library.MelonyLib;
import melonystudios.library.util.LibUtils;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = MelonyLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MLClientBusEvents {
    public static Map<ResourceLocation, BannerPattern> DATA_DRIVEN_PATTERNS = new HashMap<>();

    @SubscribeEvent
    public static void stitchTextures(final TextureStitchEvent.Pre event) {
        if (event.getMap().location().getPath().contains("banner_patterns")) {
//            event.addSprite(LibUtils.minecraft("entity/banner/guster"));
            for (ResourceLocation location : DATA_DRIVEN_PATTERNS.keySet()) {
                event.addSprite(LibUtils.minecraft("entity/banner/" + location.getPath()));
                LogManager.getLogger().debug("Loaded banner pattern texture: {}", "entity/banner/" + location.getPath());
            }
        }
        if (event.getMap().location().getPath().contains("shield_patterns")) {
//            event.addSprite(LibUtils.minecraft("entity/shield/guster"));
            for (ResourceLocation location : DATA_DRIVEN_PATTERNS.keySet()) {
                event.addSprite(LibUtils.minecraft("entity/shield/" + location.getPath()));
                LogManager.getLogger().debug("Loaded shield banner pattern texture: {}", "entity/banner/" + location.getPath());
            }
        }
    }
}
