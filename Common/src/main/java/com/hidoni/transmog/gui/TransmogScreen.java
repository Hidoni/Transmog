package com.hidoni.transmog.gui;

import com.hidoni.transmog.Constants;
import com.hidoni.transmog.inventory.TransmogMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TransmogScreen extends AbstractContainerScreen<TransmogMenu> {
    private static final Identifier GUI = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "textures/gui/container/transmogrification_table.png");
    private static final int TEXTURE_WIDTH = 256;
    private static final int TEXTURE_HEIGHT = 256;

    public TransmogScreen(TransmogMenu menu, Inventory inventory, Component name) {
        super(menu, inventory, name);
    }

    @Override
    public void extractRenderState(@NotNull GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.extractRenderState(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public void extractBackground(@NotNull GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.extractBackground(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, GUI, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, TEXTURE_WIDTH, TEXTURE_HEIGHT);
        if (this.menu.getSlot(TransmogMenu.APPEARANCE_ITEM_SLOT).hasItem()) {
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, GUI, this.leftPos + 86, this.topPos + 40, 37, 40, 18, 18, TEXTURE_WIDTH, TEXTURE_HEIGHT);
        }
        if (this.menu.getSlot(TransmogMenu.FUEL_ITEM_SLOT).hasItem()) {
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, GUI, this.leftPos + 10, this.topPos + 16, 37, 40, 18, 18, TEXTURE_WIDTH, TEXTURE_HEIGHT);
        }
        if (this.menu.hasFuel()) {
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, GUI, this.leftPos + 12, this.topPos + 38, 176, 0, 14, (int) Math.floor(21 * (this.menu.getFuel() / (float) Constants.TRANSMOG_FUEL_FROM_SHARD)), TEXTURE_WIDTH, TEXTURE_HEIGHT);
        }
        if (this.menu.getSlot(TransmogMenu.ITEM_TO_TRANSMOG_SLOT).hasItem() && this.menu.getSlot(TransmogMenu.APPEARANCE_ITEM_SLOT).hasItem() && this.menu.hasFuel()) {
            ItemStack item = this.menu.getSlot(TransmogMenu.ITEM_TO_TRANSMOG_SLOT).getItem().copyWithCount(1);
            ItemStack transmoggedItem = this.menu.createTransmoggedItem(item);
            if (ItemStack.matches(transmoggedItem, item)) {
                guiGraphics.blit(RenderPipelines.GUI_TEXTURED, GUI, this.leftPos + 110, this.topPos + 39, 176, 21, 28, 21, TEXTURE_WIDTH, TEXTURE_HEIGHT);
            }
        }
    }
}
