package com.unblockablmodding.horse_mod.setup;

import com.unblockablmodding.horse_mod.container.ModContainers;
import com.unblockablmodding.horse_mod.horse_mod;
import com.unblockablmodding.horse_mod.screens.saddle_stand_screen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = horse_mod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientProxy implements IProxy
{
    @Override
    public void init()
    {

        ScreenManager.registerFactory(ModContainers.SADDLE_STAND_CONTAINER.get(), saddle_stand_screen::new);

    }

    @Override
    public World getClientWorld()
    {
        return Minecraft.getInstance().world;
    }
}
