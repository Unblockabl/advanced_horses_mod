package com.unblockablmodding.horse_mod.events;

import com.unblockablmodding.horse_mod.block.MyBlocks;
import com.unblockablmodding.horse_mod.container.saddle_stand_container;
import com.unblockablmodding.horse_mod.item.ModItems;
import com.unblockablmodding.horse_mod.tileentity.saddle_stand_tile;
import net.minecraft.block.BlockState;
import net.minecraft.block.MyceliumBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.NetworkHooks;

public class ModEvents {

    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

    }


    @SubscribeEvent
    public void exp(ServerChatEvent event)
    {
        String message = "PlayerData";
        if (event.getMessage().equals(message))
        {
            String username = event.getUsername();
            PlayerEntity player = event.getPlayer();
            int exp = player.experienceLevel;
            Item heldItem = player.getHeldItemMainhand().getItem();
            float playerHealth = player.getHealth();

            if (!heldItem.equals("1 air") || !heldItem.equals("0 air") || !heldItem.equals("air")) {

                Minecraft.getInstance().player.sendChatMessage("I (" + username + ") has " + exp + " exp levels," + "and is holding " +
                        player.getHeldItemMainhand() + " block / item. " + "The player entity is: " + player + " .( That may not make sense!! )." +
                        " I have " + playerHealth + " health.");

            }
            else{

                Minecraft.getInstance().player.sendChatMessage("I (" + username + ") has " + exp + " exp levels," + "and is holding nothing." +
                        "The player entity is: " + player + " .( That may not make sense!! ). I have " + playerHealth + " health.");

            }


        }
    }



    @SubscribeEvent
    public void onOatHorse(AttackEntityEvent event)
    {
        if(event.getPlayer().getHeldItemMainhand().getItem() == ModItems.OAT_BAG.get())
        {
            if (event.getTarget().isAlive()){
                LivingEntity target = (LivingEntity)event.getTarget();
                if (target instanceof HorseEntity){
                    PlayerEntity player = event.getPlayer();

                    player.getHeldItemMainhand().shrink(1);
                    player.inventory.addItemStackToInventory(new ItemStack(ModItems.EMPTY_BAG.get()));

                    if (!player.world.isRemote) {
                        float floatmsg = target.getHealth();

                        String msg = String.valueOf(floatmsg);

                        player.sendMessage(new StringTextComponent(msg), player.getUniqueID());

                        target.heal(15000);

                        wait(5000);

                        player.sendMessage(new StringTextComponent(msg), player.getUniqueID());
                    }
                }
            }
        }

    }

}
