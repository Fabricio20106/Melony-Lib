package melonystudios.library.mixin.entity;

import melonystudios.library.tag.BackportedItemTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WolfEntity.class)
public abstract class MLWolfEntityMixin extends TameableEntity {
    public MLWolfEntityMixin(EntityType<? extends TameableEntity> type, World world) {
        super(type, world);
    }

    @Inject(method = "setTame", at = @At("HEAD"), cancellable = true)
    public void setTame(boolean tamed, CallbackInfo ci) {
        ci.cancel();
        super.setTame(tamed);
        if (tamed) {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(40);
            this.setHealth(40);
        } else {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(8);
        }

        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(4);
    }

    @Inject(method = "isFood", at = @At("HEAD"), cancellable = true)
    public void isFood(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(stack.getItem().is(BackportedItemTags.WOLF_FOOD));
    }
}
