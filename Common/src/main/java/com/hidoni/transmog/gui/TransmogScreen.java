package com.hidoni.transmog.gui;

import com.hidoni.transmog.Constants;
import com.hidoni.transmog.inventory.TransmogMenu;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
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
    public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTick);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(@NotNull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        Lighting.setupForFlatItems();
        RenderSystem.setShaderTexture(0, GUI);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        blit(poseStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
        if (this.menu.getSlot(TransmogMenu.APPEARANCE_ITEM_SLOT).hasItem()) {
            blit(poseStack, x + 86, y + 40, 37, 40, 18, 18);
        }
        if (this.menu.hasFuel()) {
            blit(poseStack, x + 12, y + 38, 176, 0, 14, (int) Math.floor(21 * (this.menu.getFuel() / (float) Constants.TRANSMOG_FUEL_FROM_SHARD)));
        }
        if (this.menu.getSlot(TransmogMenu.ITEM_TO_TRANSMOG_SLOT).hasItem() && this.menu.getSlot(TransmogMenu.APPEARANCE_ITEM_SLOT).hasItem() && this.menu.hasFuel()) {
            ItemStack item = this.menu.getSlot(TransmogMenu.ITEM_TO_TRANSMOG_SLOT).getItem().copy();
            item.setCount(1);
            ItemStack transmoggedItem = this.menu.createTransmoggedItem(item);
            if (ItemStack.matches(transmoggedItem, item)) {
                blit(poseStack, x + 110, y + 39, 176, 21, 28, 21);
            }
        }
        Lighting.setupFor3DItems();
    }
}
