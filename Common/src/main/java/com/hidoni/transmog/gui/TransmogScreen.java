package com.hidoni.transmog.gui;

import com.hidoni.transmog.Constants;
import com.hidoni.transmog.inventory.TransmogMenu;
import com.mojang.blaze3d.platform.Lighting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TransmogScreen extends AbstractContainerScreen<TransmogMenu> {
    private static final ResourceLocation GUI = new ResourceLocation(Constants.MOD_ID, "textures/gui/container/transmogrification_table.png");

    public TransmogScreen(TransmogMenu menu, Inventory inventory, Component name) {
        super(menu, inventory, name);
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        Lighting.setupForFlatItems();
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(GUI, x, y, 0, 0, this.imageWidth, this.imageHeight);
        if (this.menu.getSlot(TransmogMenu.APPEARANCE_ITEM_SLOT).hasItem()) {
            guiGraphics.blit(GUI, x + 86, y + 40, 37, 40, 18, 18);
        }
        if (this.menu.hasFuel()) {
            guiGraphics.blit(GUI, x + 12, y + 38, 176, 0, 14, (int) Math.floor(21 * (this.menu.getFuel() / (float) Constants.TRANSMOG_FUEL_FROM_SHARD)));
        }
        if (this.menu.getSlot(TransmogMenu.ITEM_TO_TRANSMOG_SLOT).hasItem() && this.menu.getSlot(TransmogMenu.APPEARANCE_ITEM_SLOT).hasItem() && this.menu.hasFuel()) {
            ItemStack item = this.menu.getSlot(TransmogMenu.ITEM_TO_TRANSMOG_SLOT).getItem().copyWithCount(1);
            ItemStack transmoggedItem = this.menu.createTransmoggedItem(item);
            if (ItemStack.matches(transmoggedItem, item)) {
                guiGraphics.blit(GUI, x + 110, y + 39, 176, 21, 28, 21);
            }
        }
        Lighting.setupFor3DItems();
    }
}
