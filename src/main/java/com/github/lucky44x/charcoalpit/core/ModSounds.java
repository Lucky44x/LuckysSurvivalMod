package com.github.lucky44x.charcoalpit.core;

import com.github.lucky44x.charcoalpit.util.Helpers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.github.lucky44x.charcoalpit.Charcoalpit.MODID;

public final class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, MODID);

    // Items
    public static final RegistryObject<SoundEvent> FIRESTARTER = create("item.firestarter.use");

    // Sound Types
    public static final ForgeSoundType CHARCOAL = createBlock("charcoal");

    private static RegistryObject<SoundEvent> create(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(Helpers.identifier(name)));
    }

    private static ForgeSoundType createBlock(String name) {
        return new ForgeSoundType(1.0f, 1.0f,
                create("block.%s.break".formatted(name)),
                create("block.%s.step".formatted(name)),
                create("block.%s.place".formatted(name)),
                create("block.%s.hit".formatted(name)),
                create("block.%s.fall".formatted(name)));
    }
}
