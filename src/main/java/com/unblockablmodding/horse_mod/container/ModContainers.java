package com.unblockablmodding.horse_mod.container;

import com.unblockablmodding.horse_mod.util.Registration;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;

public class ModContainers
{
    public static final RegistryObject<ContainerType<saddle_stand_container>> SADDLE_STAND_CONTAINER
            = Registration.CONTAINERS.register("saddle_stand_container",
            () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new saddle_stand_container(windowId, world, pos, inv, inv.player);
            }));


public static void register(){}

}
