package com.hidoni.transmog;

import com.hidoni.transmog.gui.TransmogScreen;
import com.hidoni.transmog.registry.ModBlockEntities;
import com.hidoni.transmog.registry.ModBlocks;
import com.hidoni.transmog.registry.ModMenus;
import com.hidoni.transmog.renderer.TransmogrificationTableBlockEntityRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.RenderType;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

public class TransmogQuiltClient implements ClientModInitializer {
    @Override
    public void onInitializeClient(ModContainer mod) {
        MenuScreens.register(ModMenus.TRANSMOG_MENU.get(), TransmogScreen::new);
        BlockRenderLayerMap.put(RenderType.cutout(), ModBlocks.TRANSMOGRIFICATION_TABLE.get());
        BlockEntityRendererRegistry.register(ModBlockEntities.TRANSMOGRIFICATION_TABLE.get(), TransmogrificationTableBlockEntityRenderer::new);
    }
}
