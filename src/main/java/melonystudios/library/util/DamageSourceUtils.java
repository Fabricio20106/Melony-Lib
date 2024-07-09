package melonystudios.library.util;

import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.util.ResourceLocation;

import java.util.List;

import static melonystudios.library.MelonyLib.*;

public class DamageSourceUtils {
    public static List<ResourceLocation> VALID_DAMAGE_SOURCES = new ImmutableList.Builder<ResourceLocation>().add(minecraft("generic"), minecraft("in_fire"), minecraft("on_fire"), minecraft("lava"), minecraft("lightning_bolt"),
            minecraft("hot_floor"), minecraft("in_wall"), minecraft("fly_into_wall"), minecraft("cramming"), minecraft("drown"), minecraft("starve"), minecraft("fall"), minecraft("magic"), minecraft("wither"),
            minecraft("falling_anvil"), minecraft("out_of_world"), minecraft("falling_block"), minecraft("dragon_breath"), minecraft("dry_out"), minecraft("sweet_berry_bush"), minecraft("bad_respawn_point"),
            variants("redstone_poisoning"), variants("bluestone_poisoning"), variants("glowstone_poisoning"), variants("gunpowder_poisoning"), variants("explosive_blend_poisoning"), backMath("hot_and_cold_meal"),
            backMath("poison_rose"), backMath("water_talc_powder"), backMath("mid_term_armor_instability"), backMath("patience_tea"), backMath("chocoglued")).build();

    public static String read(StringReader reader) throws CommandSyntaxException {
        int cursor = reader.getCursor();

        while (reader.canRead() && isValidDamageSource(reader.peek())) reader.skip();
        String messageID = reader.getString().substring(cursor, reader.getCursor());
        try {

        }
    }

    // Usable Damage Sources:
    // generic, in_fire, on_fire, lava, lightning_bolt, hot_floor, in_wall, fly_into_wall, cramming, drown, starve, cactus, fall, magic, wither, anvil, out_of_world, falling_block, dragon_breath, dry_out, sweet_berry_bush, bad_respawn_point, redstone_poisoning, bluestone_poisoning,
    // glowstone_poisoning, gunpowder_poisoning and explosive_blend_poisoning

    // Back Math:
    //    public static final DamageSource HOT_AND_COLD_MEAL = new DamageSource("hotAndColdMeal");
    //    public static final DamageSource POISON_ROSE = new DamageSource("poisonRose").bypassArmor();
    //    public static final DamageSource WATER_TALC_POWDER = new DamageSource("waterTalcPowder").bypassMagic();
    //    public static final DamageSource MID_TERM_ARMOR_INSTABILITY = new DamageSource("midTermArmorInstability").bypassArmor();
    //    public static final DamageSource PATIENCE_TEA = new DamageSource("patienceTea").bypassMagic().bypassArmor().setMagic();
    //    public static final DamageSource CHOCOGLUED = new DamageSource("chocoglued").setProjectile();
}
