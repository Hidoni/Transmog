package com.hidoni.transmog.mixin;

import com.hidoni.transmog.RenderUtils;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(method = "render", at=@At("HEAD"))
    private void onEnterRenderer(CallbackInfo ci) {
        RenderUtils.enterRenderClass();
    }

    @Inject(method = "render", at=@At("RETURN"))
    private void onExitRenderer(CallbackInfo ci) {
        RenderUtils.exitRenderClass();
    }

    @Inject(method = "render", at= @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;render(Lnet/minecraft/client/gui/GuiGraphics;F)V", shift= At.Shift.BEFORE))
    private void onEnterGui(CallbackInfo ci) {
        RenderUtils.enterInventoryClass();
    }

    @Inject(method = "render", at= @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;render(Lnet/minecraft/client/gui/GuiGraphics;F)V", shift= At.Shift.AFTER))
    private void onExitGui(CallbackInfo ci) {
        RenderUtils.exitInventoryClass();
    }
}