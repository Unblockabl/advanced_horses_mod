package com.unblockablmodding.horse_mod.block;

import com.unblockablmodding.horse_mod.horse_mod;
import com.unblockablmodding.horse_mod.util.Registration;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class MyBlocks {
    public static final RegistryObject<Block> OAT_BLOCK = register("oat_block",
            () -> new Block(AbstractBlock.Properties.create(Material.WOOL).sound(SoundType.WET_GRASS)));

    public static final RegistryObject<Block> STABLE_CLADDING = register("stable_cladding",
            () -> new Block(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> MOORLAND_GRASS_BLOCK = register("moorland_grass_block",
            () -> new Block(AbstractBlock.Properties.create(Material.SEA_GRASS).sound(SoundType.GROUND)));


    public static final RegistryObject<Block> OIL_PUMP = register("oil_pump",
            () -> new oil_pump(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(4f).harvestTool(ToolType.PICKAXE)));

    public static final RegistryObject<Block> SADDLE_STAND = register("saddle_stand",
            () -> new saddle_stand(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(4f).harvestTool(ToolType.PICKAXE)));

    public static final RegistryObject<Block> CORNER_DESK = register("corner_desk",
            () -> new corner_desk(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(4f).harvestTool(ToolType.PICKAXE)));

    public static final RegistryObject<Block> CENTER_DESK = register("center_desk",
            () -> new center_desk(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(4f).harvestTool(ToolType.PICKAXE)));


    public static final RegistryObject<Block> RIGHT_SIDE_DESK = register("right_side_desk",
            () -> new right_side_desk(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(4f).harvestTool(ToolType.PICKAXE)));


    public static final RegistryObject<Block> STABLE_WINDOW = register("stable_window",
            () -> new stable_window(AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(4f).sound(SoundType.GLASS).harvestTool(ToolType.PICKAXE)));

    public static final RegistryObject<Block> LAPTOP = register("laptop",
            () -> new laptop(AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(4f).sound(SoundType.GLASS).harvestTool(ToolType.PICKAXE)));

    public static final RegistryObject<Block> RADIATOR = register("radiator",
            () -> new radiator(AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(4f).sound(SoundType.CHAIN).harvestTool(ToolType.PICKAXE)));

    public static final RegistryObject<Block> KIT_BOX = register("kit_box",
            () -> new kit_box(AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(4f).sound(SoundType.WOOD).harvestTool(ToolType.PICKAXE)));


    public static final RegistryObject<Block> VERTICAL_SLAB = register("vertical_slab",
            () -> new vertical_slab(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(4f).sound(SoundType.GLASS).harvestTool(ToolType.AXE)));


    public static void register(){};

    private static <T extends Block>RegistryObject<T> register(String name, Supplier<T> block) {

        RegistryObject<T> toReturn = Registration.BLOCKS.register(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties().group(horse_mod.ADVANCED_HORSES)));
        return toReturn;
    }


}
