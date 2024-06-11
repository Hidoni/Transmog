package com.hidoni.transmog;

import com.hidoni.transmog.gui.ConfigScreen;
import com.hidoni.transmog.gui.TransmogScreen;
import com.hidoni.transmog.registry.ModBlockEntities;
import com.hidoni.transmog.registry.ModMenus;
import com.hidoni.transmog.renderer.TransmogrificationTableBlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

public class TransmogNeoForgeClient {
    public static void init(IEventBus modEventBus) {
        modEventBus.addListener(TransmogNeoForgeClient::registerClientOnly);
        modEventBus.addListener(TransmogNeoForgeClient::registerScreen);
    }

    public static void registerClientOnly(FMLClientSetupEvent event) {
        BlockEntityRenderers.register(ModBlockEntities.TRANSMOGRIFICATION_TABLE.get(), TransmogrificationTableBlockEntityRenderer::new);
        ModContainer modContainer = ModList.get().getModContainerById(Constants.MOD_ID).orElseThrow();
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, (mc, screen) -> new ConfigScreen(screen));
    }

    public static void registerScreen(RegisterMenuScreensEvent event) {
        event.register(ModMenus.TRANSMOG_MENU.get(), TransmogScreen::new);
    }
}
