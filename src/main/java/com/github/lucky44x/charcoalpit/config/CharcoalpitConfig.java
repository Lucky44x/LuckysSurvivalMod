package com.github.lucky44x.charcoalpit.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.lang3.tuple.Pair;

import java.util.function.Function;

public class CharcoalpitConfig {
    public static final ServerConfig SERVER;

    private static final ForgeConfigSpec SERVER_SPEC;
    private static FMLJavaModLoadingContext modLoadingContext;

    static {
        final Pair<ServerConfig, ForgeConfigSpec> pair = register(ModConfig.Type.SERVER, ServerConfig::new, "server");

        SERVER = pair.getKey();
        SERVER_SPEC = pair.getRight();
    }

    public static void init(FMLJavaModLoadingContext context) {
        modLoadingContext = context;
    }

    private static <C> Pair<C, ForgeConfigSpec> register(final ModConfig.Type type, Function<ForgeConfigSpec.Builder, C> factory, String name) {
        final Pair<C, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(builder -> factory.apply(new ForgeConfigSpec.Builder()));
        modLoadingContext.registerConfig(type, specPair.getRight());
        return specPair;
    }
}
