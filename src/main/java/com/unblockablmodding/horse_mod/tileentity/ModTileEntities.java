package com.unblockablmodding.horse_mod.tileentity;

import com.unblockablmodding.horse_mod.block.MyBlocks;
import com.unblockablmodding.horse_mod.util.Registration;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

public class ModTileEntities
{
    public static final RegistryObject<TileEntityType<saddle_stand_tile>> SADDLE_STAND_TILE_ENTITY
            = Registration.TILE_ENTITY_TYPES.register("saddle_stand_tile_entity", () -> TileEntityType.Builder.create(
            () -> new saddle_stand_tile(), MyBlocks.SADDLE_STAND.get()).build(null));

    public static final RegistryObject<TileEntityType<saddle_stand_tile>> LAPTOP_TILE_ENTITY
            = Registration.TILE_ENTITY_TYPES.register("laptopd_tile_entity", () -> TileEntityType.Builder.create(
            () -> new saddle_stand_tile(), MyBlocks.SADDLE_STAND.get()).build(null));

    public static void register() {}

}
