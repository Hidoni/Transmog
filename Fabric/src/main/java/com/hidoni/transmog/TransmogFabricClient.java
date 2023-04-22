package com.hidoni.transmog;

import com.hidoni.transmog.gui.TransmogScreen;
import com.hidoni.transmog.registry.ModBlockEntities;
import com.hidoni.transmog.registry.ModBlocks;
import com.hidoni.transmog.registry.ModMenus;
import com.hidoni.transmog.renderer.TransmogrificationTableBlockEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

public class TransmogFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MenuScreens.register(ModMenus.TRANSMOG_MENU.get(), TransmogScreen::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TRANSMOGRIFICATION_TABLE.get(), RenderType.cutout());
        BlockEntityRenderers.register(ModBlockEntities.TRANSMOGRIFICATION_TABLE.get(), TransmogrificationTableBlockEntityRenderer::new);
    }
}
