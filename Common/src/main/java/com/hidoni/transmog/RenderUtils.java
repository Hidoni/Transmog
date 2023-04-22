package com.hidoni.transmog;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;

import java.util.ArrayList;
import java.util.List;

public class RenderUtils {
    private static final List<Class<?>> RENDERING_CLASSES = new ArrayList<>();
    private static final List<Class<?>> INVENTORY_CLASSES = new ArrayList<>();
    private static final List<Class<?>> INVENTORY_EXCLUDED_CLASSES = new ArrayList<>();

    static {
        RENDERING_CLASSES.add(EntityRenderDispatcher.class);
        RENDERING_CLASSES.add(LevelRenderer.class);
        RENDERING_CLASSES.add(GameRenderer.class);

        INVENTORY_CLASSES.add(Gui.class);
        INVENTORY_CLASSES.add(Screen.class);
        INVENTORY_CLASSES.add(AbstractContainerScreen.class);

        INVENTORY_EXCLUDED_CLASSES.add(EntityRenderDispatcher.class);
    }

    private static boolean anyClassInStackFrame(List<Class<?>> classes) {
        StackWalker walker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
        return walker.walk(stackFrameStream -> stackFrameStream.map(StackWalker.StackFrame::getDeclaringClass).anyMatch(classes::contains));
    }

    public static boolean isCalledForRendering() {
        return anyClassInStackFrame(RENDERING_CLASSES);
    }

    public static boolean isCalledForInventory() {
        return anyClassInStackFrame(INVENTORY_CLASSES) && !anyClassInStackFrame(INVENTORY_EXCLUDED_CLASSES);
    }
}
