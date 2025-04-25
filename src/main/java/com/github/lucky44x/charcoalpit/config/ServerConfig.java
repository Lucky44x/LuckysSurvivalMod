package com.github.lucky44x.charcoalpit.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ServerConfig {
    //public final ForgeConfigSpec.DoubleValue fireStarterChance;

    ServerConfig(ForgeConfigSpec.Builder builder) {
        builder.push("general");

        //fireStarterChance = builder.comment("The chance of a firestarter starting a fire every tick of use").defineInRange("fireStarterChance", 0.5, 0, 1);
    }
}
