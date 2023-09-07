package com.hidoni.transmog.mixin;

import com.hidoni.transmog.RenderUtils;
import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
    @Inject(method = "/^(?!<init>)/", at=@At("HEAD"))
    private void enterFunction(CallbackInfo ci) {
        RenderUtils.enterRenderClass();
    }

    @Inject(method = "/^(?!<init>)/", at=@At("RETURN"))
    private void exitFunction(CallbackInfo ci) {
        RenderUtils.exitRenderClass();
    }
}