package com.junethewoods.melonylib.mixin.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.PaintingType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(HangingEntityItem.class)
public class MLHangingEntityItemMixin extends Item {
    public MLHangingEntityItemMixin(Properties properties) {
        super(properties);
    }

    @Override
    public void fillItemCategory(ItemGroup tab, NonNullList<ItemStack> list) {
        if (this.allowdedIn(tab)) {
            list.add(new ItemStack(this));
            if (this == Items.PAINTING) {
                for (PaintingType painting : ForgeRegistries.PAINTING_TYPES) {
                    ItemStack stack1 = new ItemStack(this);
                    CompoundNBT entityTag = stack1.getOrCreateTagElement("EntityTag");
                    entityTag.putString("Motive", painting.getRegistryName().toString());
                    list.add(stack1);
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        CompoundNBT entityTag = stack.getTagElement("EntityTag");
        if (stack.getItem() == Items.PAINTING) {
            if (entityTag != null && entityTag.contains("Motive")) {
                PaintingType painting = ForgeRegistries.PAINTING_TYPES.getValue(ResourceLocation.tryParse(entityTag.getString("Motive")));
                tooltip.add(new TranslationTextComponent("painting." + painting.getRegistryName().getNamespace() + "." + painting.getRegistryName().getPath() + ".title").withStyle(TextFormatting.YELLOW));
                tooltip.add(new TranslationTextComponent("painting." + painting.getRegistryName().getNamespace() + "." + painting.getRegistryName().getPath() + ".author").withStyle(TextFormatting.GRAY));
                tooltip.add(new TranslationTextComponent("painting.dimensions", painting.getWidth() / 16, painting.getHeight() / 16).withStyle(TextFormatting.WHITE));
            } else {
                tooltip.add(new TranslationTextComponent("painting.random_variant").withStyle(TextFormatting.GRAY));
            }
        }
        super.appendHoverText(stack, world, tooltip, flag);
    }
}
