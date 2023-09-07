package com.hidoni.transmog.mixin;

import com.hidoni.transmog.RenderUtils;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {
    @Inject(method = "/^(?!<init>)/", at=@At("HEAD"))
    private void enterFunction(CallbackInfo ci) {
        RenderUtils.enterInventoryClass();
    }

    @Inject(method = "/^(?!<init>)/", at=@At("RETURN"))
    private void exitFunction(CallbackInfo ci) {
        RenderUtils.exitInventoryClass();
    }
}