package com.junethewoods.melonnierslib.block.custom;

import com.junethewoods.melonnierslib.item.ModdedItems;
import com.junethewoods.melonnierslib.util.MLTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;

import java.util.Random;

import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class GrapeVinePostBlock extends HorizontalBlock implements IGrowable {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    public static final VoxelShape SHAPE_NS = Block.box(0, 0, 7, 16, 16, 9);
    public static final VoxelShape SHAPE_EW = Block.box(7, 0, 0, 9, 16, 16);

    public GrapeVinePostBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        if (state.getValue(FACING) == Direction.NORTH || state.getValue(FACING) == Direction.SOUTH) {
            return SHAPE_NS;
        } else {
            return SHAPE_EW;
        }
    }

    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 3;
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        int ageState = state.getValue(AGE);
        if (ageState < 3 && world.getRawBrightness(pos.above(), 0) >= 9 && ForgeHooks.onCropsGrowPre(world, pos, state, rand.nextInt(5) == 0)) {
            world.setBlock(pos, state.setValue(AGE, ageState + 1), 2);
            ForgeHooks.onCropsGrowPost(world, pos, state);
        }
    }

    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        int ageState = state.getValue(AGE);
        boolean flag = ageState == 3;
        if (!flag && player.getItemInHand(hand).getItem().is(MLTags.Items.BONE_MEALS)) {
            return ActionResultType.PASS;
        } else if (ageState > 1) {
            int j = 1 + world.random.nextInt(2);
            popResource(world, pos, new ItemStack(ModdedItems.GRAPES, j + (flag ? 1 : 0)));
            world.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1, 0.8F + world.random.nextFloat() * 0.4F);
            world.setBlock(pos, state.setValue(AGE, 1), 2);
            return ActionResultType.sidedSuccess(world.isClientSide);
        } else {
            return super.use(state, world, pos, player, hand, hit);
        }
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(HORIZONTAL_FACING, rotation.rotate(state.getValue(HORIZONTAL_FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(HORIZONTAL_FACING)));
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE, FACING);
    }

    public boolean isValidBonemealTarget(IBlockReader world, BlockPos pos, BlockState state, boolean isClient) {
        return state.getValue(AGE) < 3;
    }

    public boolean isBonemealSuccess(World world, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
        int i = Math.min(3, state.getValue(AGE) + 1);
        world.setBlock(pos, state.setValue(AGE, i), 2);
    }
}