package melonystudios.library.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SkullItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MLMinecraftMixin {
    @Inject(method = "addCustomNbtData", at = @At("HEAD"), cancellable = true)
    private void addCustomNbtData(ItemStack stack, TileEntity blockEntity, CallbackInfoReturnable<ItemStack> cir) {
        CompoundNBT blockEntityData = blockEntity.save(new CompoundNBT());
        if (stack.getItem() instanceof SkullItem && blockEntityData.contains("SkullOwner")) {
            CompoundNBT skullOwnerTag = blockEntityData.getCompound("SkullOwner");
            stack.getOrCreateTag().put("SkullOwner", skullOwnerTag);
            cir.setReturnValue(stack);
        } else {
            stack.addTagElement("BlockEntityTag", blockEntityData);
            cir.setReturnValue(stack);
        }
    }
}
