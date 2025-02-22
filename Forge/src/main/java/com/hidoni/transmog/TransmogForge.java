package com.hidoni.transmog;

import com.hidoni.transmog.platform.ForgeRegistryHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class TransmogForge {
    public TransmogForge(FMLJavaModLoadingContext context) {
        ForgeRegistryHelper.setEventBus(context.getModEventBus());
        Transmog.init();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> TransmogForgeClient.init(context));
    }
}
