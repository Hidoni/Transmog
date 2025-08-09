package com.hidoni.transmog.mixin;

import com.hidoni.transmog.RenderUtils;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(InventoryScreen.class)
public class InventoryScreenMixin {
    @WrapMethod(method = "renderEntityInInventory")
    private static void wrapRenderEntityInInventory(GuiGraphics guiGraphics, int x1, int y1, int x2, int y2, float scale, Vector3f translation, Quaternionf rotation, Quaternionf overrideCameraAngle, LivingEntity entity, Operation<Void> original) {
        RenderUtils.enterInventoryExcludedClass();
        try {
            original.call(guiGraphics, x1, y1, x2, y2, scale, translation, rotation, overrideCameraAngle, entity);
        } finally {
            RenderUtils.exitInventoryExcludedClass();
        }
    }
}
