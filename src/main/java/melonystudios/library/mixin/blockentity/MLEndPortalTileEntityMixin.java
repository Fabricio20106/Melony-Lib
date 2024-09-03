package melonystudios.library.mixin.blockentity;

import net.minecraft.tileentity.EndPortalTileEntity;
import net.minecraft.util.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndPortalTileEntity.class)
public class MLEndPortalTileEntityMixin {
    @Inject(method = "shouldRenderFace", at = @At("HEAD"), cancellable = true)
    public void shouldRenderFace(Direction direction, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(direction.getAxis() == Direction.Axis.Y);
    }
}
