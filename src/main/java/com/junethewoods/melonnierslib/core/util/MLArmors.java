package com.junethewoods.melonnierslib.core.util;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public class MLArmors implements IArmorMaterial {
    private static final int[] HEALTH_PER_SLOT = new int[] {
            13, 15, 16, 11
    };

    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairIngredient;

    public MLArmors(String name, int durabilityMultiplier, int[] protectionPerSlot, int enchantmentValue, SoundEvent equipSound, float toughness, float knockbackRes, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = protectionPerSlot;
        this.enchantmentValue = enchantmentValue;
        this.sound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackRes;
        this.repairIngredient = new LazyValue<>(repairIngredient);
    }

    public int getDurabilityForSlot(EquipmentSlotType slot) {
        return HEALTH_PER_SLOT[slot.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlotType slot) {
        return this.slotProtections[slot.getIndex()];
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
