package com.hidoni.transmog.mixin;

import com.hidoni.transmog.RenderUtils;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;

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

    @WrapMethod(method = "extractGui")
    private void wrapExtractGui(DeltaTracker deltaTracker, boolean shouldRenderLevel, boolean resourcesLoaded, Operation<Void> original) {
        RenderUtils.enterInventoryClass();
        try {
            original.call(deltaTracker, shouldRenderLevel, resourcesLoaded);
        } finally {
            RenderUtils.exitInventoryClass();
        }
    }
}