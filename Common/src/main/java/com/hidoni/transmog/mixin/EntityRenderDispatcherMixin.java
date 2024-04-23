package com.hidoni.transmog.mixin;

import com.hidoni.transmog.RenderUtils;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {
    @Inject(method = "render", at=@At("HEAD"))
    private void enterInventoryExcludedRender(CallbackInfo ci) {
        RenderUtils.enterInventoryExcludedClass();
    }
    @Inject(method = "render", at=@At("RETURN"))
    private void exitInventoryExcludedRender(CallbackInfo ci) {
        RenderUtils.exitInventoryExcludedClass();
    }
}
