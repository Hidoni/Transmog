package com.hidoni.transmog;

import com.mojang.blaze3d.systems.RenderSystem;

public class RenderUtils {
    private static int renderCount = 0;
    private static int inventoryCount = 0;
    private static int inventoryExcludedCount = 0;

    public static void enterRenderClass() {
        if (RenderSystem.isOnRenderThread()) {
            renderCount++;
        }
    }

    public static void exitRenderClass() {
        if (RenderSystem.isOnRenderThread()) {
            renderCount--;
        }
    }

    public static void enterInventoryClass() {
        if (RenderSystem.isOnRenderThread()) {
            inventoryCount++;
        }
    }

    public static void exitInventoryClass() {
        if (RenderSystem.isOnRenderThread()) {
            inventoryCount--;
        }
    }

    public static void enterInventoryExcludedClass() {
        if (RenderSystem.isOnRenderThread()) {
            inventoryExcludedCount++;
        }
    }

    public static void exitInventoryExcludedClass() {
        if (RenderSystem.isOnRenderThread()) {
            inventoryExcludedCount--;
        }
    }

    public static boolean isCalledForRendering() {
        return RenderSystem.isOnRenderThread() && renderCount > 0;
    }

    public static boolean isCalledForInventory() {
        return RenderSystem.isOnRenderThread() && inventoryCount > 0 && inventoryExcludedCount == 0;
    }
}
