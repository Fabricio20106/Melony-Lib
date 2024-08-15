package melonystudios.library.mixin.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.CompassItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CompassItem.class)
public abstract class MLCompassItemMixin extends Item {
    public MLCompassItemMixin(Properties properties) {
        super(properties);
    }

    @Unique
    private static boolean isLodestoneCompassAlt(ItemStack stack) {
        CompoundNBT tag = stack.getTag();
        return tag != null && (tag.contains("LodestoneDimension") || tag.contains("LodestonePos"));
    }

    @Inject(method = "inventoryTick", at = @At("HEAD"))
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean isSelected, CallbackInfo ci) {
        if (!world.isClientSide && entity instanceof PlayerEntity && isSelected) {
            PlayerEntity player = (PlayerEntity) entity;
            if (isLodestoneCompassAlt(stack)) {
                BlockPos lodestonePos = NBTUtil.readBlockPos(stack.getOrCreateTag().getCompound("LodestonePos"));
                ((ServerPlayerEntity) player).sendMessage(new TranslationTextComponent("tooltip.melonylib.lodestone_compass_coordinates", lodestonePos.getX(), lodestonePos.getY(), lodestonePos.getZ()), ChatType.GAME_INFO, Util.NIL_UUID);
            } else {
                ((ServerPlayerEntity) player).sendMessage(new TranslationTextComponent("tooltip.melonylib.compass_coordinates", player.blockPosition().getX(), player.blockPosition().getY(), player.blockPosition().getZ()), ChatType.GAME_INFO, Util.NIL_UUID);
            }
        }
    }
}
