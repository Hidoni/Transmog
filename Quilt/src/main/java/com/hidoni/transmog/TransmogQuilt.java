package com.hidoni.transmog;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class TransmogQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        Transmog.init();
    }
}
