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
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.concurrent.CompletableFuture;

public class ConfiguredStructureFeatureArgument implements ArgumentType<StructureFeature<?, ?>> {
    private static final DynamicCommandExceptionType UNKNOWN_STRUCTURE = new DynamicCommandExceptionType(object -> new TranslationTextComponent("commands.place.structure.invalid", object));

    public static ConfiguredStructureFeatureArgument structureFeature() {
        return new ConfiguredStructureFeatureArgument();
    }

    @Override
    public StructureFeature<?, ?> parse(StringReader reader) throws CommandSyntaxException {
        ResourceLocation location = ResourceLocation.read(reader);
        return WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE.getOptional(location).orElseThrow(() -> UNKNOWN_STRUCTURE.create(location));
    }

    public static StructureFeature<?, ?> getStructure(CommandContext<CommandSource> context, String argumentName) {
        return context.getArgument(argumentName, StructureFeature.class);
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return ISuggestionProvider.suggestResource(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE.keySet(), builder);
    }
}
