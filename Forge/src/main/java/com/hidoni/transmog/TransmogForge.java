package com.hidoni.transmog;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class TransmogForge {
    public TransmogForge() {
        Transmog.init();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> TransmogForgeClient::init);
    }
}
