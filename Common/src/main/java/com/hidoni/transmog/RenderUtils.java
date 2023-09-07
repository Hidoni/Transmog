package com.hidoni.transmog;

import com.mojang.blaze3d.systems.RenderSystem;

public class RenderUtils {
    public static final RenderUtils INSTANCE = new RenderUtils();

    private static int renderCount = 0;
    private static int inventoryCount = 0;
    private static int inventoryExcludedCount = 0;

    public void enterRenderClass() {
        if (RenderSystem.isOnRenderThread()) {
            renderCount++;
        }
    }

    public void exitRenderClass() {
        if (RenderSystem.isOnRenderThread()) {
            renderCount--;
        }
    }

    public void enterInventoryClass() {
        if (RenderSystem.isOnRenderThread()) {
            inventoryCount++;
        }
    }

    public void exitInventoryClass() {
        if (RenderSystem.isOnRenderThread()) {
            inventoryCount--;
        }
    }

    public void enterInventoryExcludedClass() {
        if (RenderSystem.isOnRenderThread()) {
            inventoryExcludedCount++;
        }
    }

    public void exitInventoryExcludedClass() {
        if (RenderSystem.isOnRenderThread()) {
            inventoryExcludedCount--;
        }
    }

    public boolean isCalledForRendering() {
        return RenderSystem.isOnRenderThread() && renderCount > 0;
    }

    public boolean isCalledForInventory() {
        return RenderSystem.isOnRenderThread() && inventoryCount > 0 && inventoryExcludedCount == 0;
    }
}
