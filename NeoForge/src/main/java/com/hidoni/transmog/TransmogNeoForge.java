package com.hidoni.transmog;

import com.hidoni.transmog.platform.NeoForgeRegistryHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(Constants.MOD_ID)
public class TransmogNeoForge {
    public TransmogNeoForge(IEventBus eventBus) {
        NeoForgeRegistryHelper.setEventBus(eventBus);

        Transmog.init();
        if (FMLEnvironment.dist == Dist.CLIENT) {
            TransmogNeoForgeClient.init(eventBus);
        }
    }
}
