package com.github.lucky44x.charcoalpit.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.github.lucky44x.charcoalpit.Charcoalpit.MODID;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> CHARCOAL_PIT = CREATIVE_MODE_TAB.register("charcoal_pit", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemgroup.charcoal_pit"))
            .icon(() -> new ItemStack(ModItems.FIRESTARTER.get()))
            .displayItems((params, output) -> {
                output.accept(ModItems.FIRESTARTER.get());
            })
            .build());
}
