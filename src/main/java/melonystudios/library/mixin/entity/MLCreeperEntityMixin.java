package melonystudios.library.mixin.entity;

import melonystudios.library.tag.BackportedItemTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CreeperEntity.class)
public abstract class MLCreeperEntityMixin extends MonsterEntity {
    @Shadow
    public abstract void ignite();

    public MLCreeperEntityMixin(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world);
    }

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    protected void mobInteract(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResultType> cir) {
        cir.cancel();
        ItemStack handStack = player.getItemInHand(hand);
        if (handStack.getItem().is(BackportedItemTags.CREEPER_IGNITERS)) {
            this.level.playSound(player, this.getX(), this.getY(), this.getZ(), SoundEvents.FLINTANDSTEEL_USE, this.getSoundSource(), 1, this.random.nextFloat() * 0.4F + 0.8F);
            if (!this.level.isClientSide) {
                ignite();
                handStack.hurtAndBreak(1, player, player1 -> player1.broadcastBreakEvent(hand));
            }

            cir.setReturnValue(ActionResultType.sidedSuccess(this.level.isClientSide));
        } else {
            cir.setReturnValue(super.mobInteract(player, hand));
        }
    }
}
