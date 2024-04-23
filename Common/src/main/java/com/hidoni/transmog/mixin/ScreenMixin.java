package com.hidoni.transmog.mixin;

import com.hidoni.transmog.RenderUtils;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class ScreenMixin {
    @Inject(method = "renderWithTooltip", at=@At("HEAD"))
    private void enterRenderWithTooltip(CallbackInfo ci) {
        RenderUtils.enterInventoryClass();
    }

    @Inject(method = "renderWithTooltip", at=@At("RETURN"))
    private void exitRenderWithTooltip(CallbackInfo ci) {
        RenderUtils.exitInventoryClass();
    }
}