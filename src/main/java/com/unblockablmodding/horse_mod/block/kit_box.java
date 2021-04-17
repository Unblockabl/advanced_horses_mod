package com.unblockablmodding.horse_mod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.network.play.client.CEntityActionPacket;
import net.minecraft.network.play.client.CUseEntityPacket;
import net.minecraft.network.play.server.SWorldBorderPacket;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.util.stream.Stream;

public class kit_box extends Block
{
    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(2, 0, 3, 14, 10, 13),
            Block.makeCuboidShape(14, 0, 2, 15, 10, 3),
            Block.makeCuboidShape(1, 0, 2, 2, 10, 3),
            Block.makeCuboidShape(1, 0, 13, 2, 10, 14),
            Block.makeCuboidShape(1, 9.5, 2.5, 2, 10.5, 13.5),
            Block.makeCuboidShape(14, 9.5, 2.5, 15, 10.5, 13.5),
            Block.makeCuboidShape(14, 0, 13, 15, 10, 14),
            Block.makeCuboidShape(1, 10, 7, 2, 15, 9),
            Block.makeCuboidShape(14, 10, 7, 15, 15, 9),
            Block.makeCuboidShape(2, 14, 7, 14, 15, 9) ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(2, 0, 3, 14, 10, 13),
            Block.makeCuboidShape(1, 0, 13, 2, 10, 14),
            Block.makeCuboidShape(14, 0, 13, 15, 10, 14),
            Block.makeCuboidShape(14, 0, 2, 15, 10, 3),
            Block.makeCuboidShape(14, 9.5, 2.5, 15, 10.5, 13.5),
            Block.makeCuboidShape(1, 9.5, 2.5, 2, 10.5, 13.5),
            Block.makeCuboidShape(1, 0, 2, 2, 10, 3),
            Block.makeCuboidShape(14, 10, 7, 15, 15, 9),
            Block.makeCuboidShape(1, 10, 7, 2, 15, 9),
            Block.makeCuboidShape(2, 14, 7, 14, 15, 9) ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(3, 0, 2, 13, 10, 14),
            Block.makeCuboidShape(13, 0, 14, 14, 10, 15),
            Block.makeCuboidShape(13, 0, 1, 14, 10, 2),
            Block.makeCuboidShape(2, 0, 1, 3, 10, 2),
            Block.makeCuboidShape(2.5, 9.5, 1, 13.5, 10.5, 2),
            Block.makeCuboidShape(2.5, 9.5, 14, 13.5, 10.5, 15),
            Block.makeCuboidShape(2, 0, 14, 3, 10, 15),
            Block.makeCuboidShape(7, 10, 1, 9, 15, 2),
            Block.makeCuboidShape(7, 10, 14, 9, 15, 15),
            Block.makeCuboidShape(7, 14, 2, 9, 15, 14) ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(3, 0, 2, 13, 10, 14),
            Block.makeCuboidShape(2, 0, 1, 3, 10, 2),
            Block.makeCuboidShape(2, 0, 14, 3, 10, 15),
            Block.makeCuboidShape(13, 0, 14, 14, 10, 15),
            Block.makeCuboidShape(2.5, 9.5, 14, 13.5, 10.5, 15),
            Block.makeCuboidShape(2.5, 9.5, 1, 13.5, 10.5, 2),
            Block.makeCuboidShape(13, 0, 1, 14, 10, 2),
            Block.makeCuboidShape(7, 10, 14, 9, 15, 15),
            Block.makeCuboidShape(7, 10, 1, 9, 15, 2),
            Block.makeCuboidShape(7, 14, 2, 9, 15, 14)  ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();




    public kit_box(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)){

            case NORTH:
                return SHAPE_N;
            case EAST:
                return SHAPE_E;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }








    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context){

        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState rotate(BlockState state, Rotation rot)
    {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn){
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);

    }

}
