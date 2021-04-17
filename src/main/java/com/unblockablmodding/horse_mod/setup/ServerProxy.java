package com.unblockablmodding.horse_mod.setup;

import net.minecraft.world.World;

public class ServerProxy implements IProxy
{


    @Override
    public void init()
    {

    }

    @Override
    public World getClientWorld()
    {
        throw new IllegalStateException("Cannot be run on Server!");
    }
}