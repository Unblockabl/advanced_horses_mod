package com.unblockablmodding.horse_mod.block;

import com.unblockablmodding.horse_mod.container.saddle_stand_container;
import com.unblockablmodding.horse_mod.tileentity.ModTileEntities;
import com.unblockablmodding.horse_mod.tileentity.saddle_stand_tile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.stream.Stream;

public class saddle_stand extends Block {
    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(6.43939393939394, 10, 3.075757575757578, 8.439393939393941, 12, 5.075757575757578),
            Block.makeCuboidShape(0.43939393939394034, 10, 3.075757575757578, 2.4393939393939403, 12, 5.075757575757578),
            Block.makeCuboidShape(-0.5606060606060597, 5, 0.07575757575757436, 15.439393939393938, 14, 3.075757575757578),
            Block.makeCuboidShape(12.439393939393941, 10, 3.075757575757578, 14.439393939393941, 12, 5.075757575757578),
            Block.makeCuboidShape(12.439393939393941, 10, 5.075757575757578, 14.439393939393941, 12, 7.075757575757578),
            Block.makeCuboidShape(12.439393939393941, 10, 7.075757575757578, 14.439393939393941, 12, 9.075757575757578),
            Block.makeCuboidShape(12.439393939393941, 10.5, 9.075757575757578, 14.439393939393941, 11.5, 11.075757575757574),
            Block.makeCuboidShape(12.939393939393941, 10.5, 11.075757575757574, 13.939393939393941, 11.5, 12.075757575757574),
            Block.makeCuboidShape(12.939393939393941, 9.5, 11.075757575757574, 13.939393939393941, 10.5, 12.075757575757574),
            Block.makeCuboidShape(12.939393939393941, 9.5, 12.075757575757574, 13.939393939393941, 10.5, 13.075757575757574),
            Block.makeCuboidShape(12.939393939393941, 9.5, 13.075757575757574, 13.939393939393941, 10.5, 14.075757575757574),
            Block.makeCuboidShape(12.939393939393941, 10.5, 13.075757575757574, 13.939393939393941, 11.5, 14.075757575757574),
            Block.makeCuboidShape(12.939393939393941, 11.5, 13.075757575757574, 13.939393939393941, 12.5, 14.075757575757574),
            Block.makeCuboidShape(0.9393939393939403, 11.5, 13.075757575757574, 1.9393939393939403, 12.5, 14.075757575757574),
            Block.makeCuboidShape(0.43939393939394034, 10, 3.075757575757578, 2.4393939393939403, 12, 5.075757575757578),
            Block.makeCuboidShape(0.43939393939394034, 10, 7.075757575757578, 2.4393939393939403, 12, 9.075757575757578),
            Block.makeCuboidShape(0.43939393939394034, 10.5, 9.075757575757578, 2.4393939393939403, 11.5, 11.075757575757574),
            Block.makeCuboidShape(0.9393939393939403, 10.5, 11.075757575757574, 1.9393939393939403, 11.5, 12.075757575757574),
            Block.makeCuboidShape(0.9393939393939403, 9.5, 11.075757575757574, 1.9393939393939403, 10.5, 12.075757575757574),
            Block.makeCuboidShape(0.9393939393939403, 9.5, 12.075757575757574, 1.9393939393939403, 10.5, 13.075757575757574),
            Block.makeCuboidShape(0.9393939393939403, 9.5, 13.075757575757574, 1.9393939393939403, 10.5, 14.075757575757574),
            Block.makeCuboidShape(0.9393939393939403, 10.5, 13.075757575757574, 1.9393939393939403, 11.5, 14.075757575757574),
            Block.makeCuboidShape(6.93939393939394, 11.5, 13.075757575757574, 7.93939393939394, 12.5, 14.075757575757574),
            Block.makeCuboidShape(6.43939393939394, 10, 3.075757575757578, 8.439393939393941, 12, 5.075757575757578),
            Block.makeCuboidShape(6.43939393939394, 10, 5.075757575757578, 8.439393939393941, 12, 7.075757575757578),
            Block.makeCuboidShape(6.43939393939394, 10, 7.075757575757578, 8.439393939393941, 12, 9.075757575757578),
            Block.makeCuboidShape(6.43939393939394, 10.5, 9.075757575757578, 8.439393939393941, 11.5, 11.075757575757574),
            Block.makeCuboidShape(6.93939393939394, 10.5, 11.075757575757574, 7.93939393939394, 11.5, 12.075757575757574),
            Block.makeCuboidShape(6.93939393939394, 9.5, 11.075757575757574, 7.93939393939394, 10.5, 12.075757575757574),
            Block.makeCuboidShape(6.93939393939394, 9.5, 12.075757575757574, 7.93939393939394, 10.5, 13.075757575757574),
            Block.makeCuboidShape(6.93939393939394, 9.5, 13.075757575757574, 7.93939393939394, 10.5, 14.075757575757574),
            Block.makeCuboidShape(6.93939393939394, 10.5, 13.075757575757574, 7.93939393939394, 11.5, 14.075757575757574),
            Block.makeCuboidShape(0.43939393939394034, 10, 5.075757575757578, 2.4393939393939403, 12, 7.075757575757578)
    ).reduce((v1, v2) -> {
        return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
    }).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(12, 10, 9.000000000000004, 14, 12, 11.000000000000004),
            Block.makeCuboidShape(12, 10, 3.0000000000000036, 14, 12, 5.0000000000000036),
            Block.makeCuboidShape(14, 5, 2.0000000000000036, 17.000000000000004, 14, 18),
            Block.makeCuboidShape(12, 10, 15.000000000000004, 14, 12, 17.000000000000004),
            Block.makeCuboidShape(10, 10, 15.000000000000004, 12, 12, 17.000000000000004),
            Block.makeCuboidShape(7.999999999999999, 10, 15.000000000000004, 10, 12, 17.000000000000004),
            Block.makeCuboidShape(6.000000000000003, 10.5, 15.000000000000004, 7.999999999999999, 11.5, 17.000000000000004),
            Block.makeCuboidShape(5.000000000000003, 10.5, 15.500000000000004, 6.000000000000003, 11.5, 16.500000000000004),
            Block.makeCuboidShape(5.000000000000003, 9.5, 15.500000000000004, 6.000000000000003, 10.5, 16.500000000000004),
            Block.makeCuboidShape(4.000000000000003, 9.5, 15.500000000000004, 5.000000000000003, 10.5, 16.500000000000004),
            Block.makeCuboidShape(3.0000000000000027, 9.5, 15.500000000000004, 4.000000000000003, 10.5, 16.500000000000004),
            Block.makeCuboidShape(3.0000000000000027, 10.5, 15.500000000000004, 4.000000000000003, 11.5, 16.500000000000004),
            Block.makeCuboidShape(3.0000000000000027, 11.5, 15.500000000000004, 4.000000000000003, 12.5, 16.500000000000004),
            Block.makeCuboidShape(3.0000000000000027, 11.5, 3.5000000000000036, 4.000000000000003, 12.5, 4.5000000000000036),
            Block.makeCuboidShape(12, 10, 3.0000000000000036, 14, 12, 5.0000000000000036),
            Block.makeCuboidShape(7.999999999999999, 10, 3.0000000000000036, 10, 12, 5.0000000000000036),
            Block.makeCuboidShape(6.000000000000003, 10.5, 3.0000000000000036, 7.999999999999999, 11.5, 5.0000000000000036),
            Block.makeCuboidShape(5.000000000000003, 10.5, 3.5000000000000036, 6.000000000000003, 11.5, 4.5000000000000036),
            Block.makeCuboidShape(5.000000000000003, 9.5, 3.5000000000000036, 6.000000000000003, 10.5, 4.5000000000000036),
            Block.makeCuboidShape(4.000000000000003, 9.5, 3.5000000000000036, 5.000000000000003, 10.5, 4.5000000000000036),
            Block.makeCuboidShape(3.0000000000000027, 9.5, 3.5000000000000036, 4.000000000000003, 10.5, 4.5000000000000036),
            Block.makeCuboidShape(3.0000000000000027, 10.5, 3.5000000000000036, 4.000000000000003, 11.5, 4.5000000000000036),
            Block.makeCuboidShape(3.0000000000000027, 11.5, 9.500000000000004, 4.000000000000003, 12.5, 10.500000000000004),
            Block.makeCuboidShape(12, 10, 9.000000000000004, 14, 12, 11.000000000000004),
            Block.makeCuboidShape(10, 10, 9.000000000000004, 12, 12, 11.000000000000004),
            Block.makeCuboidShape(7.999999999999999, 10, 9.000000000000004, 10, 12, 11.000000000000004),
            Block.makeCuboidShape(6.000000000000003, 10.5, 9.000000000000004, 7.999999999999999, 11.5, 11.000000000000004),
            Block.makeCuboidShape(5.000000000000003, 10.5, 9.500000000000004, 6.000000000000003, 11.5, 10.500000000000004),
            Block.makeCuboidShape(5.000000000000003, 9.5, 9.500000000000004, 6.000000000000003, 10.5, 10.500000000000004),
            Block.makeCuboidShape(4.000000000000003, 9.5, 9.500000000000004, 5.000000000000003, 10.5, 10.500000000000004),
            Block.makeCuboidShape(3.0000000000000027, 9.5, 9.500000000000004, 4.000000000000003, 10.5, 10.500000000000004),
            Block.makeCuboidShape(3.0000000000000027, 10.5, 9.500000000000004, 4.000000000000003, 11.5, 10.500000000000004),
            Block.makeCuboidShape(10, 10, 3.0000000000000036, 12, 12, 5.0000000000000036)
    ).reduce((v1, v2) -> {
        return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
    }).get();


    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(6.0757575757575735, 10, 14.560606060606062, 8.075757575757574, 12, 16.560606060606062),
            Block.makeCuboidShape(12.075757575757574, 10, 14.560606060606062, 14.075757575757574, 12, 16.560606060606062),
            Block.makeCuboidShape(-0.924242424242423, 5, 16.560606060606062, 15.075757575757574, 14, 19.560606060606066),
            Block.makeCuboidShape(0.07575757575757347, 10, 14.560606060606062, 2.0757575757575735, 12, 16.560606060606062),
            Block.makeCuboidShape(0.07575757575757347, 10, 12.560606060606062, 2.0757575757575735, 12, 14.560606060606062),
            Block.makeCuboidShape(0.07575757575757347, 10, 10.560606060606062, 2.0757575757575735, 12, 12.560606060606062),
            Block.makeCuboidShape(0.07575757575757347, 10.5, 8.560606060606066, 2.0757575757575735, 11.5, 10.560606060606062),
            Block.makeCuboidShape(0.5757575757575735, 10.5, 7.560606060606066, 1.5757575757575735, 11.5, 8.560606060606066),
            Block.makeCuboidShape(0.5757575757575735, 9.5, 7.560606060606066, 1.5757575757575735, 10.5, 8.560606060606066),
            Block.makeCuboidShape(0.5757575757575735, 9.5, 6.560606060606066, 1.5757575757575735, 10.5, 7.560606060606066),
            Block.makeCuboidShape(0.5757575757575735, 9.5, 5.560606060606066, 1.5757575757575735, 10.5, 6.560606060606066),
            Block.makeCuboidShape(0.5757575757575735, 10.5, 5.560606060606066, 1.5757575757575735, 11.5, 6.560606060606066),
            Block.makeCuboidShape(0.5757575757575735, 11.5, 5.560606060606066, 1.5757575757575735, 12.5, 6.560606060606066),
            Block.makeCuboidShape(12.575757575757574, 11.5, 5.560606060606066, 13.575757575757574, 12.5, 6.560606060606066),
            Block.makeCuboidShape(12.075757575757574, 10, 14.560606060606062, 14.075757575757574, 12, 16.560606060606062),
            Block.makeCuboidShape(12.075757575757574, 10, 10.560606060606062, 14.075757575757574, 12, 12.560606060606062),
            Block.makeCuboidShape(12.075757575757574, 10.5, 8.560606060606066, 14.075757575757574, 11.5, 10.560606060606062),
            Block.makeCuboidShape(12.575757575757574, 10.5, 7.560606060606066, 13.575757575757574, 11.5, 8.560606060606066),
            Block.makeCuboidShape(12.575757575757574, 9.5, 7.560606060606066, 13.575757575757574, 10.5, 8.560606060606066),
            Block.makeCuboidShape(12.575757575757574, 9.5, 6.560606060606066, 13.575757575757574, 10.5, 7.560606060606066),
            Block.makeCuboidShape(12.575757575757574, 9.5, 5.560606060606066, 13.575757575757574, 10.5, 6.560606060606066),
            Block.makeCuboidShape(12.575757575757574, 10.5, 5.560606060606066, 13.575757575757574, 11.5, 6.560606060606066),
            Block.makeCuboidShape(6.5757575757575735, 11.5, 5.560606060606066, 7.5757575757575735, 12.5, 6.560606060606066),
            Block.makeCuboidShape(6.0757575757575735, 10, 14.560606060606062, 8.075757575757574, 12, 16.560606060606062),
            Block.makeCuboidShape(6.0757575757575735, 10, 12.560606060606062, 8.075757575757574, 12, 14.560606060606062),
            Block.makeCuboidShape(6.0757575757575735, 10, 10.560606060606062, 8.075757575757574, 12, 12.560606060606062),
            Block.makeCuboidShape(6.0757575757575735, 10.5, 8.560606060606066, 8.075757575757574, 11.5, 10.560606060606062),
            Block.makeCuboidShape(6.5757575757575735, 10.5, 7.560606060606066, 7.5757575757575735, 11.5, 8.560606060606066),
            Block.makeCuboidShape(6.5757575757575735, 9.5, 7.560606060606066, 7.5757575757575735, 10.5, 8.560606060606066),
            Block.makeCuboidShape(6.5757575757575735, 9.5, 6.560606060606066, 7.5757575757575735, 10.5, 7.560606060606066),
            Block.makeCuboidShape(6.5757575757575735, 9.5, 5.560606060606066, 7.5757575757575735, 10.5, 6.560606060606066),
            Block.makeCuboidShape(6.5757575757575735, 10.5, 5.560606060606066, 7.5757575757575735, 11.5, 6.560606060606066),
            Block.makeCuboidShape(12.075757575757574, 10, 12.560606060606062, 14.075757575757574, 12, 14.560606060606062)
    ).reduce((v1, v2) -> {
        return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
    }).get();


    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(0.5151515151515147, 10, 8.636363636363637, 2.5151515151515147, 12, 10.636363636363637),
            Block.makeCuboidShape(0.5151515151515147, 10, 14.636363636363637, 2.5151515151515147, 12, 16.636363636363637),
            Block.makeCuboidShape(-2.484848484848489, 5, 1.6363636363636402, 0.5151515151515147, 14, 17.636363636363637),
            Block.makeCuboidShape(0.5151515151515147, 10, 2.6363636363636367, 2.5151515151515147, 12, 4.636363636363637),
            Block.makeCuboidShape(2.5151515151515147, 10, 2.6363636363636367, 4.515151515151515, 12, 4.636363636363637),
            Block.makeCuboidShape(4.515151515151515, 10, 2.6363636363636367, 6.515151515151515, 12, 4.636363636363637),
            Block.makeCuboidShape(6.515151515151515, 10.5, 2.6363636363636367, 8.515151515151512, 11.5, 4.636363636363637),
            Block.makeCuboidShape(8.515151515151512, 10.5, 3.1363636363636367, 9.515151515151512, 11.5, 4.136363636363637),
            Block.makeCuboidShape(8.515151515151512, 9.5, 3.1363636363636367, 9.515151515151512, 10.5, 4.136363636363637),
            Block.makeCuboidShape(9.515151515151512, 9.5, 3.1363636363636367, 10.515151515151512, 10.5, 4.136363636363637),
            Block.makeCuboidShape(10.515151515151512, 9.5, 3.1363636363636367, 11.515151515151512, 10.5, 4.136363636363637),
            Block.makeCuboidShape(10.515151515151512, 10.5, 3.1363636363636367, 11.515151515151512, 11.5, 4.136363636363637),
            Block.makeCuboidShape(10.515151515151512, 11.5, 3.1363636363636367, 11.515151515151512, 12.5, 4.136363636363637),
            Block.makeCuboidShape(10.515151515151512, 11.5, 15.136363636363637, 11.515151515151512, 12.5, 16.136363636363637),
            Block.makeCuboidShape(0.5151515151515147, 10, 14.636363636363637, 2.5151515151515147, 12, 16.636363636363637),
            Block.makeCuboidShape(4.515151515151515, 10, 14.636363636363637, 6.515151515151515, 12, 16.636363636363637),
            Block.makeCuboidShape(6.515151515151515, 10.5, 14.636363636363637, 8.515151515151512, 11.5, 16.636363636363637),
            Block.makeCuboidShape(8.515151515151512, 10.5, 15.136363636363637, 9.515151515151512, 11.5, 16.136363636363637),
            Block.makeCuboidShape(8.515151515151512, 9.5, 15.136363636363637, 9.515151515151512, 10.5, 16.136363636363637),
            Block.makeCuboidShape(9.515151515151512, 9.5, 15.136363636363637, 10.515151515151512, 10.5, 16.136363636363637),
            Block.makeCuboidShape(10.515151515151512, 9.5, 15.136363636363637, 11.515151515151512, 10.5, 16.136363636363637),
            Block.makeCuboidShape(10.515151515151512, 10.5, 15.136363636363637, 11.515151515151512, 11.5, 16.136363636363637),
            Block.makeCuboidShape(10.515151515151512, 11.5, 9.136363636363637, 11.515151515151512, 12.5, 10.136363636363637),
            Block.makeCuboidShape(0.5151515151515147, 10, 8.636363636363637, 2.5151515151515147, 12, 10.636363636363637),
            Block.makeCuboidShape(2.5151515151515147, 10, 8.636363636363637, 4.515151515151515, 12, 10.636363636363637),
            Block.makeCuboidShape(4.515151515151515, 10, 8.636363636363637, 6.515151515151515, 12, 10.636363636363637),
            Block.makeCuboidShape(6.515151515151515, 10.5, 8.636363636363637, 8.515151515151512, 11.5, 10.636363636363637),
            Block.makeCuboidShape(8.515151515151512, 10.5, 9.136363636363637, 9.515151515151512, 11.5, 10.136363636363637),
            Block.makeCuboidShape(8.515151515151512, 9.5, 9.136363636363637, 9.515151515151512, 10.5, 10.136363636363637),
            Block.makeCuboidShape(9.515151515151512, 9.5, 9.136363636363637, 10.515151515151512, 10.5, 10.136363636363637),
            Block.makeCuboidShape(10.515151515151512, 9.5, 9.136363636363637, 11.515151515151512, 10.5, 10.136363636363637),
            Block.makeCuboidShape(10.515151515151512, 10.5, 9.136363636363637, 11.515151515151512, 11.5, 10.136363636363637),
            Block.makeCuboidShape(2.5151515151515147, 10, 14.636363636363637, 4.515151515151515, 12, 16.636363636363637)
    ).reduce((v1, v2) -> {
        return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
    }).get();


    public saddle_stand(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {

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

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if(!worldIn.isRemote())
        {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if(tileEntity instanceof saddle_stand_tile)
            {
                INamedContainerProvider containerProvider = new INamedContainerProvider()
                {
                    @Override
                    public ITextComponent getDisplayName()
                    {
                        return new TranslationTextComponent("screen.horse_mod.saddle_stand");
                    }

                    @Override
                    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity)
                    {
                        return new saddle_stand_container(i, worldIn, pos, playerInventory, playerEntity);
                    }
                };
                NetworkHooks.openGui((ServerPlayerEntity) player, containerProvider, tileEntity.getPos());
            }
            else
            {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return ActionResultType.SUCCESS;
    }




    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return ModTileEntities.SADDLE_STAND_TILE_ENTITY.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
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
