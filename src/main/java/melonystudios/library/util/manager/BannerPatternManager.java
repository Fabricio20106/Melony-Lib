package melonystudios.library.util.manager;

import com.google.common.collect.ImmutableMap;
import com.google.gson.*;
import melonystudios.library.event.MLClientBusEvents;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.lang.reflect.Type;
import java.util.*;

public class BannerPatternManager extends JsonReloadListener {
    private static final Gson GSON = createBannerPatternSerializer().create();
    public static final Logger LOGGER = LogManager.getLogger();

    public BannerPatternManager() {
        super(GSON, "banner_pattern");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> resourceList, @Nonnull IResourceManager manager, @Nonnull IProfiler profiler) {
        ImmutableMap.Builder<ResourceLocation, BannerPattern> builder = ImmutableMap.builder();

        resourceList.forEach((location, element) -> {
            try {
                if (element.isJsonObject()) {
                    BannerPattern pattern = GSON.fromJson(element, BannerPattern.class);
                    builder.put(location, pattern);
                }
            } catch (Exception exception) {
                LOGGER.error(new TranslationTextComponent("error.melonylib.banner_pattern_manager.parsing", location).getString(), exception);
            }
        });
        ImmutableMap<ResourceLocation, BannerPattern> map = builder.build();
        MLClientBusEvents.DATA_DRIVEN_PATTERNS.putAll(map);
        BannerPattern.COUNT = BannerPattern.COUNT + map.size();
        LOGGER.info(new TranslationTextComponent("console.melonylib.banner_pattern_manager.loaded", map.size()).getString());
    }

    public static GsonBuilder createBannerPatternSerializer() {
        return new GsonBuilder().registerTypeAdapter(BannerPattern.class, new Serializer());
    }

    private static class Serializer implements JsonDeserializer<BannerPattern>, JsonSerializer<BannerPattern> {
        @Override
        public BannerPattern deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
            if (element.isJsonObject()) {
                JsonObject object = element.getAsJsonObject();
                String assetID = JSONUtils.getAsString(object, "asset_id");
                String hashName = JSONUtils.getAsString(object, "hash_name");
                boolean hasPatternItem = JSONUtils.getAsBoolean(object, "has_pattern_item");
                return BannerPattern.create(assetID.toUpperCase(Locale.ROOT), assetID, hashName, hasPatternItem);
            } else throw new JsonParseException("Melony Lib: Banner pattern JSON is not an object.");
        }

        @Override
        public JsonElement serialize(BannerPattern pattern, Type type, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            object.addProperty("asset_id", pattern.getFilename());
            object.addProperty("hash_name", pattern.getHashname());
            object.addProperty("has_pattern_item", pattern.hasPatternItem);
            return object;
        }
    }
}
