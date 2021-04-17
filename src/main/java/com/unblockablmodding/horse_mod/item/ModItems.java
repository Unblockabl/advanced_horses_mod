package com.unblockablmodding.horse_mod.item;

import com.unblockablmodding.horse_mod.block.ModFluids;
import com.unblockablmodding.horse_mod.horse_mod;
import com.unblockablmodding.horse_mod.util.Registration;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {




    public static final RegistryObject<Item> OAT_BAG = Registration.ITEMS.register("oat_bag", () -> new Item(new Item.Properties().group(horse_mod.ADVANCED_HORSES)));
    public static final RegistryObject<Item> EMPTY_BAG = Registration.ITEMS.register("empty_bag", () -> new Item(new Item.Properties().group(horse_mod.ADVANCED_HORSES)));

    public static final RegistryObject<Item> OIL_BUCKET = Registration.ITEMS
            .register("oil_bucket", () -> new BucketItem(ModFluids.OIL_FLUID::get,
                    new Item.Properties().group(horse_mod.ADVANCED_HORSES).maxStackSize(1)));


    public static final RegistryObject<Item> OATS = Registration.ITEMS.register("oats", () -> new Item(new Item.Properties().group(horse_mod.ADVANCED_HORSES)));




    public enum ModArmourMaterial implements IArmorMaterial
    {
        BRONZE(50, new int[] {2, 7, 5, 3},
                10, SoundEvents.ITEM_ARMOR_EQUIP_IRON,
                Ingredient.fromStacks(new ItemStack(ModItems.OATS.get())),
                horse_mod.MOD_ID + ":bronze", 0, 0.1f);

        private final int durability;
        private final int[] damageReductionAmountArray;
        private final int enchantability;
        private final SoundEvent soundEvent;
        private final Ingredient repairMaterial;
        private final String name;
        private final float toughness;
        private final float knockbackResistance;

        ModArmourMaterial(int durability, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, Ingredient repairMaterial, String name, float toughness, float knockbackResistance) {
            this.durability = durability;
            this.damageReductionAmountArray = damageReductionAmountArray;
            this.enchantability = enchantability;
            this.soundEvent = soundEvent;
            this.repairMaterial = repairMaterial;
            this.name = name;
            this.toughness = toughness;
            this.knockbackResistance = knockbackResistance;
        }

        @Override
        public int getDurability(EquipmentSlotType slotIn) {
            return durability;
        }

        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotIn) {
            return damageReductionAmountArray[slotIn.getIndex()];
        }

        @Override
        public int getEnchantability() {
            return enchantability;
        }

        @Override
        public SoundEvent getSoundEvent() {
            return soundEvent;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return repairMaterial;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public float getToughness() {
            return toughness;
        }

        @Override
        public float getKnockbackResistance() {
            return knockbackResistance;
        }


    }

    public static void register(){ }

    public enum ModItemTier implements IItemTier
    {
        BRONZE(2, 150, 3f, 0, 15, Ingredient.fromStacks(new ItemStack(ModItems.OATS.get())));

        private final int harvestLevel;
        private final int maxUses;
        private final float efficiency;

        private final float attackDamage;
        private final int enchantability;
        private final Ingredient repairMaterial;

        ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Ingredient repairMaterial) {
            this.harvestLevel = harvestLevel;
            this.maxUses = maxUses;
            this.efficiency = efficiency;
            this.attackDamage = attackDamage;
            this.enchantability = enchantability;
            this.repairMaterial = repairMaterial;
        }

        @Override
        public int getMaxUses() {
            return maxUses;
        }

        @Override
        public float getEfficiency() {
            return efficiency;
        }

        @Override
        public float getAttackDamage() {
            return attackDamage;
        }

        @Override
        public int getHarvestLevel() {
            return harvestLevel;
        }

        @Override
        public int getEnchantability() {
            return enchantability;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return repairMaterial;
        }
    }

}
