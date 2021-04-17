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

public class radiator extends Block
{
    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(0, 13, 15, 16, 14, 16),
            Block.makeCuboidShape(0, 0, 4, 16, 16, 15),
            Block.makeCuboidShape(0, 0, 15, 16, 1, 16),
            Block.makeCuboidShape(15, 1, 15, 16, 4, 16),
            Block.makeCuboidShape(0, 4, 15, 1, 7, 16),
            Block.makeCuboidShape(15, 7, 15, 16, 10, 16),
            Block.makeCuboidShape(0, 10, 15, 1, 13, 16),
            Block.makeCuboidShape(15, 13, 15, 16, 16, 16),
            Block.makeCuboidShape(0, 3, 15, 16, 4, 16),
            Block.makeCuboidShape(0, 6, 15, 16, 7, 16),
            Block.makeCuboidShape(0, 9, 15, 16, 10, 16),
            Block.makeCuboidShape(0, 0, 3, 2, 16, 4),
            Block.makeCuboidShape(14, 0, 3, 16, 16, 4),
            Block.makeCuboidShape(10, 0, 3, 12, 16, 4),
            Block.makeCuboidShape(4, 0, 3, 6, 16, 4)
             ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(1.0000000000000018, 13, 7.800000000000001, 17, 14, 8.8),
            Block.makeCuboidShape(1.0000000000000018, 0, 8.8, 17, 16, 19.799999999999997),
            Block.makeCuboidShape(1.0000000000000018, 0, 7.800000000000001, 17, 1, 8.8),
            Block.makeCuboidShape(1.0000000000000018, 1, 7.800000000000001, 2.0000000000000018, 4, 8.8),
            Block.makeCuboidShape(15.999999999999998, 4, 7.800000000000001, 17, 7, 8.8),
            Block.makeCuboidShape(1.0000000000000018, 7, 7.800000000000001, 2.0000000000000018, 10, 8.8),
            Block.makeCuboidShape(15.999999999999998, 10, 7.800000000000001, 17, 13, 8.8),
            Block.makeCuboidShape(1.0000000000000018, 13, 7.800000000000001, 2.0000000000000018, 16, 8.8),
            Block.makeCuboidShape(1.0000000000000018, 3, 7.800000000000001, 17, 4, 8.8),
            Block.makeCuboidShape(1.0000000000000018, 6, 7.800000000000001, 17, 7, 8.8),
            Block.makeCuboidShape(1.0000000000000018, 9, 7.800000000000001, 17, 10, 8.8),
            Block.makeCuboidShape(14.999999999999998, 0, 19.799999999999997, 17, 16, 20.799999999999997),
            Block.makeCuboidShape(1.0000000000000018, 0, 19.799999999999997, 3.0000000000000018, 16, 20.799999999999997),
            Block.makeCuboidShape(5, 0, 19.799999999999997, 7, 16, 20.799999999999997),
            Block.makeCuboidShape(11, 0, 19.799999999999997, 12.999999999999998, 16, 20.799999999999997)
             ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(4.4, 13, 3.400000000000002, 5.4, 14, 19.4),
            Block.makeCuboidShape(5.4, 0, 3.400000000000002, 16.4, 16, 19.4),
            Block.makeCuboidShape(4.4, 0, 3.400000000000002, 5.4, 1, 19.4),
            Block.makeCuboidShape(4.4, 1, 18.4, 5.4, 4, 19.4),
            Block.makeCuboidShape(4.4, 4, 3.400000000000002, 5.4, 7, 4.400000000000002),
            Block.makeCuboidShape(4.4, 7, 18.4, 5.4, 10, 19.4),
            Block.makeCuboidShape(4.4, 10, 3.400000000000002, 5.4, 13, 4.400000000000002),
            Block.makeCuboidShape(4.4, 13, 18.4, 5.4, 16, 19.4),
            Block.makeCuboidShape(4.4, 3, 3.400000000000002, 5.4, 4, 19.4),
            Block.makeCuboidShape(4.4, 6, 3.400000000000002, 5.4, 7, 19.4),
            Block.makeCuboidShape(4.4, 9, 3.400000000000002, 5.4, 10, 19.4),
            Block.makeCuboidShape(16.4, 0, 3.400000000000002, 17.4, 16, 5.400000000000002),
            Block.makeCuboidShape(16.4, 0, 17.4, 17.4, 16, 19.4),
            Block.makeCuboidShape(16.4, 0, 13.4, 17.4, 16, 15.4),
            Block.makeCuboidShape(16.4, 0, 7.400000000000002, 17.4, 16, 9.4)
             ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(11.6, 13, 4.400000000000002, 12.6, 14, 20.4),
            Block.makeCuboidShape(0.6000000000000032, 0, 4.400000000000002, 11.6, 16, 20.4),
            Block.makeCuboidShape(11.6, 0, 4.400000000000002, 12.6, 1, 20.4),
            Block.makeCuboidShape(11.6, 1, 4.400000000000002, 12.6, 4, 5.400000000000002),
            Block.makeCuboidShape(11.6, 4, 19.4, 12.6, 7, 20.4),
            Block.makeCuboidShape(11.6, 7, 4.400000000000002, 12.6, 10, 5.400000000000002),
            Block.makeCuboidShape(11.6, 10, 19.4, 12.6, 13, 20.4),
            Block.makeCuboidShape(11.6, 13, 4.400000000000002, 12.6, 16, 5.400000000000002),
            Block.makeCuboidShape(11.6, 3, 4.400000000000002, 12.6, 4, 20.4),
            Block.makeCuboidShape(11.6, 6, 4.400000000000002, 12.6, 7, 20.4),
            Block.makeCuboidShape(11.6, 9, 4.400000000000002, 12.6, 10, 20.4),
            Block.makeCuboidShape(-0.3999999999999968, 0, 18.4, 0.6000000000000032, 16, 20.4),
            Block.makeCuboidShape(-0.3999999999999968, 0, 4.400000000000002, 0.6000000000000032, 16, 6.400000000000002),
            Block.makeCuboidShape(-0.3999999999999968, 0, 8.4, 0.6000000000000032, 16, 10.4),
            Block.makeCuboidShape(-0.3999999999999968, 0, 14.4, 0.6000000000000032, 16, 16.4)
              ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();


    public radiator(Properties properties) {
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

