package melonystudios.library.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import melonystudios.library.command.argument.DamageSourceArgument;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;

import java.util.Collection;

public class DamageCommand {
    //    public static void register(CommandDispatcher<CommandSource> dispatcher) {
    //        dispatcher.register(Commands.literal("setbehavior").requires(source -> source.hasPermission(2))
    //                .then(Commands.argument("targets", EntityArgument.players()).then(Commands.argument("stew_behavior", BehaviorArgument.behavior())
    //                        .executes(dispatcher1 -> setBehaviorToItem(dispatcher1.getSource(), BehaviorArgument.getBehavior(dispatcher1, "stew_behavior"),
    //                                EntityArgument.getPlayers(dispatcher1, "targets"))))));
    //    }
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("damage").requires(source-> source.hasPermission(2))
                .then(Commands.argument("target", EntityArgument.entities()).then(Commands.argument("amount", FloatArgumentType.floatArg(0, Float.MAX_VALUE))
                        .then(Commands.argument("source", DamageSourceArgument.sources()).executes(dispatcher1 -> damageEntity(dispatcher1.getSource(), DamageSourceArgument.getSource(dispatcher1, "source"),
                                EntityArgument.getEntities(dispatcher1, "target")))))));
    }

    private static int damageEntity(CommandSource source, DamageSource damageSource, Collection<Entity> entities) {
        return 0;
    }
}
