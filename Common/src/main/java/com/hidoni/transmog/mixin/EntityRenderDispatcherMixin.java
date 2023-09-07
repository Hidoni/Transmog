package com.hidoni.transmog.mixin;

import com.hidoni.transmog.RenderUtils;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {
    @Inject(method = "/^(?!<init>)/", at=@At("HEAD"))
    private void enterFunction(CallbackInfo ci) {
        RenderUtils.enterRenderClass();
        RenderUtils.enterInventoryExcludedClass();
    }

    @Inject(method = "/^(?!<init>)/", at=@At("RETURN"))
    private void exitFunction(CallbackInfo ci) {
        RenderUtils.exitRenderClass();
        RenderUtils.exitInventoryExcludedClass();
    }
}
