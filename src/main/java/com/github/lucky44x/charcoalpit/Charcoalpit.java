package com.github.lucky44x.charcoalpit;

import com.github.lucky44x.charcoalpit.config.CharcoalpitConfig;
import com.github.lucky44x.charcoalpit.core.ModBlocks;
import com.github.lucky44x.charcoalpit.core.ModCreativeTab;
import com.github.lucky44x.charcoalpit.core.ModItems;
import com.github.lucky44x.charcoalpit.core.ModSounds;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Charcoalpit.MODID)
public class Charcoalpit {

    public static final String MODID = "charcoalpit";
    public static final String MODNAME = "Charcoalpit";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Charcoalpit(FMLJavaModLoadingContext context) {
        LOGGER.info("Starting Charcoalpit");

        final IEventBus bus = context.getModEventBus();

        bus.addListener(this::setup);

        ModCreativeTab.CREATIVE_MODE_TAB.register(bus);

        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModSounds.SOUNDS.register(bus);

        ForgeMod.enableMilkFluid();
    }

    public void setup(FMLCommonSetupEvent event) {
        LOGGER.info("Charcoalpit setup");
    }

    public void registerCapabilities(RegisterCapabilitiesEvent event) {

    }
}
