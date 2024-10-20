package melonystudios.library.command.argument;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.concurrent.CompletableFuture;

public class ConfiguredFeatureArgument implements ArgumentType<ConfiguredFeature<?, ?>> {
    private static final DynamicCommandExceptionType UNKNOWN_FEATURE = new DynamicCommandExceptionType(object -> new TranslationTextComponent("commands.place.feature.invalid", object));

    public static ConfiguredFeatureArgument configuredFeature() {
        return new ConfiguredFeatureArgument();
    }

    @Override
    public ConfiguredFeature<?, ?> parse(StringReader reader) throws CommandSyntaxException {
        ResourceLocation location = ResourceLocation.read(reader);
        return WorldGenRegistries.CONFIGURED_FEATURE.getOptional(location).orElseThrow(() -> UNKNOWN_FEATURE.create(location));
    }

    public static ConfiguredFeature<?, ?> getFeature(CommandContext<CommandSource> context, String argumentName) {
        return context.getArgument(argumentName, ConfiguredFeature.class);
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return ISuggestionProvider.suggestResource(WorldGenRegistries.CONFIGURED_FEATURE.keySet(), builder);
    }
}
