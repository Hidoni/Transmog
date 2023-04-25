package com.hidoni.transmog;

import com.hidoni.transmog.gui.ConfigScreen;
import com.hidoni.transmog.gui.TransmogScreen;
import com.hidoni.transmog.registry.ModBlockEntities;
import com.hidoni.transmog.registry.ModBlocks;
import com.hidoni.transmog.registry.ModMenus;
import com.hidoni.transmog.renderer.TransmogrificationTableBlockEntityRenderer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.client.ConfigGuiHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class TransmogForgeClient {
    public static void init() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(TransmogForgeClient::registerClientOnly);
    }

    public static void registerClientOnly(FMLClientSetupEvent event) {
        MenuScreens.register(ModMenus.TRANSMOG_MENU.get(), TransmogScreen::new);
        BlockEntityRenderers.register(ModBlockEntities.TRANSMOGRIFICATION_TABLE.get(), TransmogrificationTableBlockEntityRenderer::new);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.TRANSMOGRIFICATION_TABLE.get(), RenderType.cutout());
        ModContainer modContainer = ModList.get().getModContainerById(Constants.MOD_ID).orElseThrow();
        modContainer.registerExtensionPoint(ConfigGuiHandler.ConfigGuiFactory.class, () -> new ConfigGuiHandler.ConfigGuiFactory((mc, screen) -> new ConfigScreen(screen)));
    }
}
