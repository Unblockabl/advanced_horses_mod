package com.unblockablmodding.horse_mod.world.feature;

import com.unblockablmodding.horse_mod.block.ModFluids;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;

public class ModConfiguredFeatures
{
    public static final ConfiguredFeature<?, ?> OIL_LAKE =
            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "lake_oil", Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(ModFluids.OIL_BLOCK.get().getDefaultState()))
                    .withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(10))));

    public static final ConfiguredFeature<?, ?> PATCH_DEAD_BUSH = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,"patch_dead_bush", Feature.RANDOM_PATCH
            .withConfiguration(Features.Configs.DEAD_BUSH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT));


}
