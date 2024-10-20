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
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;

import java.util.concurrent.CompletableFuture;

public class SurfaceBuilderArgument implements ArgumentType<ConfiguredSurfaceBuilder<?>> {
    private static final DynamicCommandExceptionType UNKNOWN_BUILDER = new DynamicCommandExceptionType(object -> new TranslationTextComponent("commands.fillsurface.invalid", object));

    public static SurfaceBuilderArgument surfaceBuilder() {
        return new SurfaceBuilderArgument();
    }

    @Override
    public ConfiguredSurfaceBuilder<?> parse(StringReader reader) throws CommandSyntaxException {
        ResourceLocation location = ResourceLocation.read(reader);
        return WorldGenRegistries.CONFIGURED_SURFACE_BUILDER.getOptional(location).orElseThrow(() -> UNKNOWN_BUILDER.create(location));
    }

    public static ConfiguredSurfaceBuilder<?> getSurfaceBuilder(CommandContext<CommandSource> context, String argumentName) {
        return context.getArgument(argumentName, ConfiguredSurfaceBuilder.class);
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return ISuggestionProvider.suggestResource(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER.keySet(), builder);
    }
}
