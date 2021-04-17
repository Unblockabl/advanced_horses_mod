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

public class oil_pump extends Block
{
    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(4, 0, 7, 6, 2, 9),
            Block.makeCuboidShape(2, 2, 5, 8, 4, 11),
            Block.makeCuboidShape(4, 4, 7, 6, 6, 9),
            Block.makeCuboidShape(2, 6, 5, 8, 8, 11),
            Block.makeCuboidShape(4, 8, 7, 6, 10, 9)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(3, 0, 8, 5, 2, 10),
            Block.makeCuboidShape(1, 2, 6, 7, 4, 12),
            Block.makeCuboidShape(3, 4, 8, 5, 6, 10),
            Block.makeCuboidShape(1, 6, 6, 7, 8, 12),
            Block.makeCuboidShape(3, 8, 8, 5, 10, 10)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(4, 0, 8, 6, 2, 10),
            Block.makeCuboidShape(2, 2, 6, 8, 4, 12),
            Block.makeCuboidShape(4, 4, 8, 6, 6, 10),
            Block.makeCuboidShape(2, 6, 6, 8, 8, 12),
            Block.makeCuboidShape(4, 8, 8, 6, 10, 10)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(3, 0, 7, 5, 2, 9),
            Block.makeCuboidShape(1, 2, 5, 7, 4, 11),
            Block.makeCuboidShape(3, 4, 7, 5, 6, 9),
            Block.makeCuboidShape(1, 6, 5, 7, 8, 11),
            Block.makeCuboidShape(3, 8, 7, 5, 10, 9)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();




    public oil_pump(Properties properties) {
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

