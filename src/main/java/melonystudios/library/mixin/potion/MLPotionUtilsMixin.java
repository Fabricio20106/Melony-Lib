package com.junethewoods.melonylib.mixin.potion;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.PotionUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;
import java.util.List;

@Mixin(PotionUtils.class)
public abstract class MLPotionUtilsMixin {
    @Shadow
    public static List<EffectInstance> getMobEffects(ItemStack stack) {
        return null;
    }

    @Shadow
    public static int getColor(Collection<EffectInstance> effects) {
        return 0;
    }

    @Inject(method = "getColor(Lnet/minecraft/item/ItemStack;)I", at = @At("HEAD"), cancellable = true)
    private static void getColorML(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        List<EffectInstance> effects = getMobEffects(stack);
        int color = getColor(effects);
        if (effects != null && effects.size() == 1) {
            if (effects.get(0).getEffect() == Effects.MOVEMENT_SPEED) color = 0x33EBFF;
            if (effects.get(0).getEffect() == Effects.MOVEMENT_SLOWDOWN) color = 0x8BAFE0;
            if (effects.get(0).getEffect() == Effects.DAMAGE_BOOST) color = 0xFFC700;
            if (effects.get(0).getEffect() == Effects.HEAL) color = 0xF82423;
            if (effects.get(0).getEffect() == Effects.HARM) color = 0xA9656A;
            if (effects.get(0).getEffect() == Effects.JUMP) color = 0xFDFF84;
            if (effects.get(0).getEffect() == Effects.REGENERATION) color = 0xCD5CAB;
            if (effects.get(0).getEffect() == Effects.DAMAGE_RESISTANCE) color = 0x9146F0;
            if (effects.get(0).getEffect() == Effects.FIRE_RESISTANCE) color = 0xFF9900;
            if (effects.get(0).getEffect() == Effects.WATER_BREATHING) color = 0x98DAC0;
            if (effects.get(0).getEffect() == Effects.INVISIBILITY) color = 0xF6F6F6;
            if (effects.get(0).getEffect() == Effects.NIGHT_VISION) color = 0xC2FF66;
            if (effects.get(0).getEffect() == Effects.WEAKNESS) color = 0x484D48;
            if (effects.get(0).getEffect() == Effects.POISON) color = 0x87A363;
            if (effects.get(0).getEffect() == Effects.WITHER) color = 0x736156;
            if (effects.get(0).getEffect() == Effects.LUCK) color = 0x59C106;
            if (effects.get(0).getEffect() == Effects.SLOW_FALLING) color = 0xF3CFB9;
        } else if (effects != null && effects.size() == 2) {
            if (effects.get(0).getEffect() == Effects.POISON && effects.get(1).getEffect() == Effects.BLINDNESS) color = 0xC9DFDF;
        }

        cir.setReturnValue(color);
    }
}
