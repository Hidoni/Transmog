package com.hidoni.transmog.mixin;

import com.hidoni.transmog.RenderUtils;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Screen.class)
public class ScreenMixin {
    @WrapMethod(method = "renderWithTooltipAndSubtitles")
    private void wrapRenderWithTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, Operation<Void> original) {
        RenderUtils.enterInventoryClass();
        try {
            original.call(guiGraphics, mouseX, mouseY, partialTick);
        } finally {
            RenderUtils.exitInventoryClass();
        }
    }
}