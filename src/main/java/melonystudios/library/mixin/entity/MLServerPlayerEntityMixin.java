package melonystudios.library.mixin.entity;

import melonystudios.library.packet.CHurtAnimationPacket;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.ServerPlayNetHandler;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public abstract class MLServerPlayerEntityMixin extends LivingEntity {
    @Shadow
    public ServerPlayNetHandler connection;

    public MLServerPlayerEntityMixin(EntityType<? extends LivingEntity> type, World world) {
        super(type, world);
    }

    @Inject(method = "hurt", at = @At("HEAD"))
    private void hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.getEntity() != null && !source.isExplosion()) {
            double deltaX = source.getEntity().getX() - this.getX();

            double deltaY;
            for (deltaY = source.getEntity().getZ() - this.getZ(); deltaX * deltaX + deltaY * deltaY < 1.0E-4D; deltaY = (Math.random() - Math.random()) * 0.01D) {
                deltaX = (Math.random() - Math.random()) * 0.01D;
            }

            this.indicateDamage(deltaX, deltaY);
        }
    }

    @Unique
    private void indicateDamage(double deltaX, double deltaY) {
        this.hurtDir = (float) (MathHelper.atan2(deltaY, deltaX) * (double) (180F / (float) Math.PI) - (double) this.getYHeadRot());
        this.connection.send(new CHurtAnimationPacket(this.getId(), this.yRot));
    }
}
