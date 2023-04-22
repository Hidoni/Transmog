package com.hidoni.transmog;

import net.fabricmc.api.ModInitializer;

public class TransmogFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Transmog.init();
    }
}
