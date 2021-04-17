package com.unblockablmodding.horse_mod.sound;

import com.unblockablmodding.horse_mod.horse_mod;
import com.unblockablmodding.horse_mod.util.Registration;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import org.lwjgl.system.CallbackI;

public class ModSoundEvents {

    public static final RegistryObject<SoundEvent> DROP_ITEM =
            Registration.SOUND_EVENTS.register("drop_item",
                    () -> new SoundEvent(new ResourceLocation(horse_mod.MOD_ID, "drop_item")));

    public static void register(){ }
}
