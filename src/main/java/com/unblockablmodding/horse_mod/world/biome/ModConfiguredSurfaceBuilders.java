package com.unblockablmodding.horse_mod.world.biome;

import com.unblockablmodding.horse_mod.horse_mod;
import com.unblockablmodding.horse_mod.util.Registration;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;

public class ModConfiguredSurfaceBuilders
{
    public static RegistryKey<ConfiguredSurfaceBuilder<?>> MOORLAND_SURFACE
            = RegistryKey.getOrCreateKey(Registry.CONFIGURED_SURFACE_BUILDER_KEY,
            new ResourceLocation(horse_mod.MOD_ID, "moorland_surface"));


}
