package melonystudios.library.mixin.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.EndPortalBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndPortalBlock.class)
public class MLEndPortalBlockMixin extends Block {
    private static final VoxelShape SHAPE = Block.box(0, 6, 0, 16, 12, 16);

    public MLEndPortalBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "getShape", at = @At("HEAD"), cancellable = true)
    public void getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context, CallbackInfoReturnable<VoxelShape> cir) {
        cir.setReturnValue(SHAPE);
    }
}
