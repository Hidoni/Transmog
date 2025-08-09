package com.hidoni.transmog;

import com.hidoni.transmog.platform.ForgeRegistryHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(Constants.MOD_ID)
public class TransmogForge {
    public TransmogForge(FMLJavaModLoadingContext context) {
        ForgeRegistryHelper.setEventBus(context.getModBusGroup());
        Transmog.init();

        if (FMLEnvironment.dist.isClient()) {
            TransmogForgeClient.init(context);
        }
    }
}
