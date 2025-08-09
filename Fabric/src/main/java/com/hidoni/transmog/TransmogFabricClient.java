package com.hidoni.transmog;

import com.hidoni.transmog.gui.TransmogScreen;
import com.hidoni.transmog.registry.ModBlockEntities;
import com.hidoni.transmog.registry.ModBlocks;
import com.hidoni.transmog.registry.ModMenus;
import com.hidoni.transmog.renderer.TransmogrificationTableBlockEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;

public class TransmogFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MenuScreens.register(ModMenus.TRANSMOG_MENU.get(), TransmogScreen::new);
        BlockRenderLayerMap.putBlock(ModBlocks.TRANSMOGRIFICATION_TABLE.get(), ChunkSectionLayer.CUTOUT);
        BlockEntityRenderers.register(ModBlockEntities.TRANSMOGRIFICATION_TABLE.get(), TransmogrificationTableBlockEntityRenderer::new);
    }
}
