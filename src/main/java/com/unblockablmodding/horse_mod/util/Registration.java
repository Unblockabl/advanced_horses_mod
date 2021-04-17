package com.unblockablmodding.horse_mod.util;


        import com.unblockablmodding.horse_mod.horse_mod;
        import net.minecraft.block.Block;
        import net.minecraft.entity.EntityType;
        import net.minecraft.fluid.Fluid;
        import net.minecraft.inventory.container.ContainerType;
        import net.minecraft.item.Item;
        import net.minecraft.tileentity.TileEntityType;
        import net.minecraft.util.SoundEvent;
        import net.minecraft.world.biome.Biome;
        import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
        import net.minecraftforge.eventbus.api.IEventBus;
        import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
        import net.minecraftforge.registries.DeferredRegister;
        import net.minecraftforge.registries.ForgeRegistries;

        import javax.swing.*;

public class Registration {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, horse_mod.MOD_ID);

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, horse_mod.MOD_ID);

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, horse_mod.MOD_ID);

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES
            = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, horse_mod.MOD_ID);

    public static final DeferredRegister<ContainerType<?>> CONTAINERS
            = DeferredRegister.create(ForgeRegistries.CONTAINERS, horse_mod.MOD_ID);

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS
            = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, horse_mod.MOD_ID);

    public static final DeferredRegister<Biome> BIOMES
            = DeferredRegister.create(ForgeRegistries.BIOMES, horse_mod.MOD_ID);


    public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS
            = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, horse_mod.MOD_ID);


    public static final DeferredRegister<EntityType<?>> ENTITIES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, horse_mod.MOD_ID);

    public static void init(){

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        TILE_ENTITY_TYPES.register(eventBus);
        CONTAINERS.register(eventBus);
        SOUND_EVENTS.register(eventBus);
        FLUIDS.register(eventBus);
        BIOMES.register(eventBus);
        SURFACE_BUILDERS.register(eventBus);
        ENTITIES.register(eventBus);

    }

}