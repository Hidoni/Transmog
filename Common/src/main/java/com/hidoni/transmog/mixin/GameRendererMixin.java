package com.hidoni.transmog.mixin;

import com.hidoni.transmog.RenderUtils;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(method = "/^(?!<init>)/", at=@At("HEAD"))
    private void enterFunction(CallbackInfo ci) {
        RenderUtils.INSTANCE.enterRenderClass();
    }

    @Inject(method = "/^(?!<init>)/", at=@At("RETURN"))
    private void exitFunction(CallbackInfo ci) {
        RenderUtils.INSTANCE.exitRenderClass();
    }
}