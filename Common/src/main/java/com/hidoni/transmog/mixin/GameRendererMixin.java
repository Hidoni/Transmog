package com.hidoni.transmog.mixin;

import com.hidoni.transmog.RenderUtils;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @WrapMethod(method="extract")
    private void wrapExtract(DeltaTracker deltaTracker, boolean advanceGameTime, Operation<Void> original) {
        RenderUtils.enterRenderClass();
        try {
            original.call(deltaTracker, advanceGameTime);
        } finally {
            RenderUtils.exitRenderClass();
        }
    }

    @WrapOperation(method = "extract", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;extractRenderState(Lnet/minecraft/client/DeltaTracker;ZZ)V"))
    private void wrapExtractGui(Gui instance, DeltaTracker deltaTracker, boolean shouldRenderLevel, boolean resourcesLoaded, Operation<Void> original) {
        RenderUtils.enterInventoryClass();
        try {
            original.call(instance, deltaTracker, shouldRenderLevel, resourcesLoaded);
        } finally {
            RenderUtils.exitInventoryClass();
        }
    }
}