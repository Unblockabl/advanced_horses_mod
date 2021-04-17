package com.unblockablmodding.horse_mod;

import com.unblockablmodding.horse_mod.block.ModFluids;
import com.unblockablmodding.horse_mod.block.MyBlocks;
import com.unblockablmodding.horse_mod.container.ModContainers;
import com.unblockablmodding.horse_mod.events.ModEvents;
import com.unblockablmodding.horse_mod.item.ModItems;
import com.unblockablmodding.horse_mod.setup.ClientProxy;
import com.unblockablmodding.horse_mod.setup.IProxy;
import com.unblockablmodding.horse_mod.setup.ServerProxy;
import com.unblockablmodding.horse_mod.tileentity.ModTileEntities;
import com.unblockablmodding.horse_mod.util.Config;
import com.unblockablmodding.horse_mod.util.Registration;
import com.unblockablmodding.horse_mod.world.biome.ModBiomes;
import com.unblockablmodding.horse_mod.world.biome.ModSurfaceBuilders;
import net.java.games.input.Keyboard;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MyceliumBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

import net.minecraft.client.renderer.entity.model.PlayerModel;

import java.util.stream.Collectors;

import static com.unblockablmodding.horse_mod.horse_mod.MOD_ID;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(MOD_ID)
public class horse_mod<keyBindings>

{
    public static final String MOD_ID = "horse_mod";

    public static IProxy proxy;


    public static final ItemGroup ADVANCED_HORSES = new ItemGroup("advanced_horses") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.OAT_BAG.get());
        }
    };
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();



    public horse_mod() {
        proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);

        Registration.init();
        ModItems.register();
        MyBlocks.register();
        ModTileEntities.register();
        ModContainers.register();
        ModFluids.register();


        ModSurfaceBuilders.register();
        ModBiomes.register();


        MinecraftForge.EVENT_BUS.register(new ModEvents());

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        Config.loadConfigFile(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("horse_mod-client.toml").toString());
        Config.loadConfigFile(Config.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve("horse_mod-server.toml").toString());

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        proxy.init();

        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        RenderTypeLookup.setRenderLayer(MyBlocks.STABLE_WINDOW.get(), RenderType.getCutout());
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }




    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
