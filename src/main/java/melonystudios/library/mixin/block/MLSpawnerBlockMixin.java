package com.junethewoods.melonylib.mixin.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SpawnerBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(SpawnerBlock.class)
public class MLSpawnerBlockMixin extends Block {
    public MLSpawnerBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "getCloneItemStack", at = @At("HEAD"), cancellable = true)
    private void getCloneItemStack(IBlockReader world, BlockPos pos, BlockState state, CallbackInfoReturnable<ItemStack> cir) {
        cir.setReturnValue(new ItemStack(Items.SPAWNER));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        ITextComponent entityName = getSpawnEntityDisplayName(stack);
        if (entityName != null) {
            tooltip.add(entityName);
        } else {
            tooltip.add(new StringTextComponent(" "));
            tooltip.add(new TranslationTextComponent("block.minecraft.spawner.desc1").withStyle(TextFormatting.GRAY));
            tooltip.add(new TranslationTextComponent("block.minecraft.spawner.desc2").withStyle(TextFormatting.BLUE));
        }
    }

    @Unique
    @Nullable
    private static ITextComponent getSpawnEntityDisplayName(ItemStack stack) {
        CompoundNBT tag = stack.getTagElement("BlockEntityTag");
        ResourceLocation entityKey = getEntityKey(tag);
        return entityKey != null ? new TranslationTextComponent(ForgeRegistries.ENTITIES.getValue(entityKey).getDescriptionId()).withStyle(TextFormatting.GRAY) : null;
    }

    @Unique
    @Nullable
    private static ResourceLocation getEntityKey(CompoundNBT tag) {
        if (tag != null && tag.contains("SpawnPotentials")) {
            String entityID = tag.getList("SpawnPotentials", 10).getCompound(0).getCompound("Entity").getString("id");
            return ResourceLocation.tryParse(entityID);
        }
        return null;
    }
}
