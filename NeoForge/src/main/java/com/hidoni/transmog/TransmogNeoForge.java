package com.hidoni.transmog;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(Constants.MOD_ID)
public class TransmogNeoForge {
    public TransmogNeoForge() {
        Transmog.init();
        if (FMLEnvironment.dist == Dist.CLIENT) {
            TransmogNeoForgeClient.init();
        }
    }
}
