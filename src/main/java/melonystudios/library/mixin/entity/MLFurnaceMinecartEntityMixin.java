package melonystudios.library.mixin.entity;

import melonystudios.library.tag.BackportedItemTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.entity.item.minecart.FurnaceMinecartEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FurnaceMinecartEntity.class)
public abstract class MLFurnaceMinecartEntityMixin extends AbstractMinecartEntity {
    @Shadow
    public double xPush;
    @Shadow
    public double zPush;
    @Shadow
    private int fuel;

    public MLFurnaceMinecartEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    public void interact(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResultType> cir) {
        ActionResultType resultType = super.interact(player, hand);
        if (resultType.consumesAction()) cir.setReturnValue(resultType);
        ItemStack handStack = player.getItemInHand(hand);
        if (handStack.getItem().is(BackportedItemTags.FURNACE_MINECART_FUEL) && this.fuel + 3600 <= 32000) {
            if (!player.abilities.instabuild) handStack.shrink(1);
            this.fuel += 3600;
        }

        if (this.fuel > 0) {
            this.xPush = this.getX() - player.getX();
            this.zPush = this.getZ() - player.getZ();
        }

        cir.setReturnValue(ActionResultType.sidedSuccess(this.level.isClientSide));
    }
}
