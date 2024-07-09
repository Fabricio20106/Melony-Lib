package melonystudios.library.command.argument;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class DamageSourceArgument implements ArgumentType<String> {
    private static final Collection<String> EXAMPLES = Arrays.asList("out_of_world", "thrown", "redstone_poisoning");

    @Override
    public String parse(StringReader reader) throws CommandSyntaxException {
        return null;
    }

    public static DamageSourceArgument sources() {
        return new DamageSourceArgument();
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return ArgumentType.super.listSuggestions(context, builder);
    }
}
