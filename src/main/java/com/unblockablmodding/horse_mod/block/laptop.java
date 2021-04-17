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

public class laptop extends Block
{
    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(8.000003333333334, 0, 2.999996666666666, 12.000003333333334, 2, 5.999996666666666),
            Block.makeCuboidShape(1.0000033333333338, 0, 1.9999966666666662, 4.000003333333334, 2, 5.999996666666666),
            Block.makeCuboidShape(1.0000033333333338, 2, 4.999996666666666, 2.000003333333334, 2, 5.999996666666666),
            Block.makeCuboidShape(3.000003333333334, 2, 4.999996666666666, 4.000003333333334, 2, 5.999996666666666),
            Block.makeCuboidShape(6.000003333333334, 0, 2.999996666666666, 8.000003333333334, 2, 10.999996666666666),
            Block.makeCuboidShape(12.000003333333334, 0, 2.999996666666666, 14.000003333333334, 2, 10.999996666666666),
            Block.makeCuboidShape(8.000003333333334, 1, 5.999996666666666, 12.000003333333334, 2, 10.999996666666666),
            Block.makeCuboidShape(8.000003333333334, 2, 6.999996666666666, 9.000003333333334, 3, 7.999996666666666),
            Block.makeCuboidShape(11.000003333333334, 2, 6.999996666666666, 12.000003333333334, 3, 7.999996666666666),
            Block.makeCuboidShape(11.000003333333334, 2, 8.999996666666666, 12.000003333333334, 3, 9.999996666666666),
            Block.makeCuboidShape(8.000003333333334, 2, 8.999996666666666, 9.000003333333334, 3, 9.999996666666666),
            Block.makeCuboidShape(6.000003333333334, 0, 10.999996666666666, 14.000003333333334, 10, 12.999996666666666) ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(4.250003333333334, 0, 8.666663333333334, 8.250003333333334, 2, 11.666663333333334),
            Block.makeCuboidShape(12.250003333333334, 0, 8.666663333333334, 15.250003333333334, 2, 12.666663333333334),
            Block.makeCuboidShape(14.250003333333334, 2, 8.666663333333334, 15.250003333333334, 2, 9.666663333333334),
            Block.makeCuboidShape(12.250003333333334, 2, 8.666663333333334, 13.250003333333334, 2, 9.666663333333334),
            Block.makeCuboidShape(8.250003333333334, 0, 3.666663333333334, 10.250003333333334, 2, 11.666663333333334),
            Block.makeCuboidShape(2.250003333333334, 0, 3.666663333333334, 4.250003333333334, 2, 11.666663333333334),
            Block.makeCuboidShape(4.250003333333334, 1, 3.666663333333334, 8.250003333333334, 2, 8.666663333333334),
            Block.makeCuboidShape(7.250003333333334, 2, 6.666663333333334, 8.250003333333334, 3, 7.666663333333334),
            Block.makeCuboidShape(4.250003333333334, 2, 6.666663333333334, 5.250003333333334, 3, 7.666663333333334),
            Block.makeCuboidShape(4.250003333333334, 2, 4.666663333333334, 5.250003333333334, 3, 5.666663333333334),
            Block.makeCuboidShape(7.250003333333334, 2, 4.666663333333334, 8.250003333333334, 3, 5.666663333333334),
            Block.makeCuboidShape(2.250003333333334, 0, 1.666663333333334, 10.250003333333334, 10, 3.666663333333334) ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(9.458336666666668, 0, 7.20833, 12.458336666666668, 2, 11.20833),
            Block.makeCuboidShape(9.458336666666668, 0, 0.20833000000000013, 13.458336666666668, 2, 3.20833),
            Block.makeCuboidShape(9.458336666666668, 2, 0.20833000000000013, 10.458336666666668, 2, 1.2083300000000001),
            Block.makeCuboidShape(9.458336666666668, 2, 2.20833, 10.458336666666668, 2, 3.20833),
            Block.makeCuboidShape(4.458336666666668, 0, 5.20833, 12.458336666666668, 2, 7.20833),
            Block.makeCuboidShape(4.458336666666668, 0, 11.20833, 12.458336666666668, 2, 13.20833),
            Block.makeCuboidShape(4.458336666666668, 1, 7.20833, 9.458336666666668, 2, 11.20833),
            Block.makeCuboidShape(7.458336666666668, 2, 7.20833, 8.458336666666668, 3, 8.20833),
            Block.makeCuboidShape(7.458336666666668, 2, 10.20833, 8.458336666666668, 3, 11.20833),
            Block.makeCuboidShape(5.458336666666668, 2, 10.20833, 6.458336666666668, 3, 11.20833),
            Block.makeCuboidShape(5.458336666666668, 2, 7.20833, 6.458336666666668, 3, 8.20833),
            Block.makeCuboidShape(2.4583366666666677, 0, 5.20833, 4.458336666666668, 10, 13.20833)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(3.79167, 0, 3.45833, 6.79167, 2, 7.45833),
            Block.makeCuboidShape(2.79167, 0, 11.45833, 6.79167, 2, 14.45833),
            Block.makeCuboidShape(5.79167, 2, 13.45833, 6.79167, 2, 14.45833),
            Block.makeCuboidShape(5.79167, 2, 11.45833, 6.79167, 2, 12.45833),
            Block.makeCuboidShape(3.79167, 0, 7.45833, 11.79167, 2, 9.45833),
            Block.makeCuboidShape(3.79167, 0, 1.4583300000000001, 11.79167, 2, 3.45833),
            Block.makeCuboidShape(6.79167, 1, 3.45833, 11.79167, 2, 7.45833),
            Block.makeCuboidShape(7.79167, 2, 6.45833, 8.79167, 3, 7.45833),
            Block.makeCuboidShape(7.79167, 2, 3.45833, 8.79167, 3, 4.45833),
            Block.makeCuboidShape(9.79167, 2, 3.45833, 10.79167, 3, 4.45833),
            Block.makeCuboidShape(9.79167, 2, 6.45833, 10.79167, 3, 7.45833),
            Block.makeCuboidShape(11.79167, 0, 1.4583300000000001, 13.79167, 10, 9.45833)  ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();


    public laptop(Properties properties) {
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
    @SuppressWarnings("deprecation")
    public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player)
    {


            player.sendMessage(new StringTextComponent("lol"), player.getUniqueID());


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
