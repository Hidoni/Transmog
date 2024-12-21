package com.hidoni.transmog.mixin;

import com.hidoni.transmog.RenderUtils;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @WrapMethod(method="render")
    private void wrapRender(DeltaTracker deltaTracker, boolean renderLevel, Operation<Void> original) {
        RenderUtils.enterRenderClass();
        try {
            original.call(deltaTracker, renderLevel);
        } finally {
            RenderUtils.exitRenderClass();
        }
    }

    @WrapOperation(method = "render", at= @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;render(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V"))
    private void wrapGuiRender(Gui instance, GuiGraphics guiGraphics, DeltaTracker deltaTracker, Operation<Void> original) {
        RenderUtils.enterInventoryClass();
        try {
            original.call(instance, guiGraphics, deltaTracker);
        } finally {
            RenderUtils.exitInventoryClass();
        }
    }
}