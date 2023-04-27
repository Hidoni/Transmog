package com.hidoni.transmog;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;

import java.util.ArrayList;
import java.util.List;

// SecurityManager is marked for removal, and there's no planned replacement... I'll deal with that when it happens.
@SuppressWarnings("removal")
public class RenderUtils extends SecurityManager {
    private static final List<Class<?>> RENDERING_CLASSES = new ArrayList<>();
    private static final List<Class<?>> INVENTORY_CLASSES = new ArrayList<>();
    private static final List<Class<?>> INVENTORY_EXCLUDED_CLASSES = new ArrayList<>();
    public static final RenderUtils INSTANCE = new RenderUtils();

    static {
        RENDERING_CLASSES.add(EntityRenderDispatcher.class);
        RENDERING_CLASSES.add(LevelRenderer.class);
        RENDERING_CLASSES.add(GameRenderer.class);

        INVENTORY_CLASSES.add(Gui.class);
        INVENTORY_CLASSES.add(Screen.class);
        INVENTORY_CLASSES.add(AbstractContainerScreen.class);

        INVENTORY_EXCLUDED_CLASSES.add(EntityRenderDispatcher.class);
    }

    private boolean anyClassInStackFrame(List<Class<?>> classes) {
        // We extend SecurityManager for this one call. It is far faster than using the StackWalker API
        Class<?>[] callingClasses = getClassContext();
        for (int i = 2; i < callingClasses.length; i++) {
            if (classes.contains(callingClasses[i])) {
                return true;
            }
        }
        return false;
    }

    public boolean isCalledForRendering() {
        return RenderSystem.isOnRenderThread() && anyClassInStackFrame(RENDERING_CLASSES);
    }

    public boolean isCalledForInventory() {
        return RenderSystem.isOnRenderThread() && anyClassInStackFrame(INVENTORY_CLASSES) && !anyClassInStackFrame(INVENTORY_EXCLUDED_CLASSES);
    }
}
