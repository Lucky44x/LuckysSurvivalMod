package com.github.lucky44x.charcoalpit.core;

import com.github.lucky44x.charcoalpit.item.FireStarterItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;
import java.util.function.Supplier;

import static com.github.lucky44x.charcoalpit.Charcoalpit.MODID;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, MODID);

    public static final RegistryObject<Item> FIRESTARTER = register("firestarter", () -> new FireStarterItem(new Item.Properties().defaultDurability(8)));

    private static <T extends Item> RegistryObject<Item> register(String name, Supplier<T> item) {
        return ITEMS.register(name.toLowerCase(Locale.ROOT), item);
    }
}
