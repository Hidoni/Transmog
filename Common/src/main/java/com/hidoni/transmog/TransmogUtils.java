package com.hidoni.transmog;

import com.hidoni.transmog.component.TransmogAppearanceItem;
import com.hidoni.transmog.config.Config;
import com.hidoni.transmog.registry.ModDataComponents;
import com.hidoni.transmog.registry.ModItems;
import net.minecraft.world.item.ItemStack;

import java.util.concurrent.TimeUnit;

public class TransmogUtils {
    private static boolean notInPvP = true;
    private static Thread pvpTimerThread = null;

    public static void startPvP() {
        if (Config.pvpDisableDuration > 0) {
            setNotInPvP(false);
            if (pvpTimerThread != null) {
                Constants.LOG.info("Client player still involved in PvP, Extending transmog disable for another {} seconds", Config.pvpDisableDuration);
                Constants.LOG.debug("Interrupting existing PvP timer thread: {}", pvpTimerThread);
                pvpTimerThread.interrupt();
            } else {
                Constants.LOG.info("Client player involved in PvP, Disabling transmogs for {} seconds", Config.pvpDisableDuration);
            }
            pvpTimerThread = new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(Config.pvpDisableDuration);
                    setNotInPvP(true);
                    Constants.LOG.info("PvP Timer finished, Transmog re-enabled.");
                } catch (InterruptedException ignored) {
                }
            });
            pvpTimerThread.start();
            Constants.LOG.debug("Created new PvP timer thread: {}", pvpTimerThread);
        }
    }

    private static synchronized void setNotInPvP(boolean notInPvP) {
        TransmogUtils.notInPvP = notInPvP;
    }

    public static boolean isItemStackTransmogged(ItemStack itemStack) {
        return itemStack.has(ModDataComponents.TRANSMOG_APPEARANCE_ITEM.get());
    }

    public static boolean isHiddenItem(ItemStack appearanceItem) {
        return appearanceItem.is(ModItems.VOID_FRAGMENT.get());
    }

    public static void transmogAppearanceOntoItemStack(ItemStack appearanceItem, ItemStack itemToTransmog) {
        itemToTransmog.set(ModDataComponents.TRANSMOG_APPEARANCE_ITEM.get(), new TransmogAppearanceItem(appearanceItem));
    }

    public static ItemStack getAppearanceItemStack(ItemStack itemStack, boolean keepHiddenItem) {
        if (!itemStack.has(ModDataComponents.TRANSMOG_APPEARANCE_ITEM.get())) {
            return itemStack;
        }
        TransmogAppearanceItem appearanceItem = itemStack.get(ModDataComponents.TRANSMOG_APPEARANCE_ITEM.get());
        if (appearanceItem == null) {
            return itemStack;
        }
        if (!keepHiddenItem && isHiddenItem(appearanceItem.itemStack())) {
            return ItemStack.EMPTY;
        }
        return appearanceItem.itemStack();
    }

    public static ItemStack getAppearanceStackOrOriginal(ItemStack itemStack) {
        if (notInPvP && Config.renderOption.renderInWorld && isItemStackTransmogged(itemStack)) {
            if (!RenderUtils.isCalledForInventory()) {
                return getAppearanceItemStack(itemStack, false);
            } else if (Config.renderOption.renderInInventory) {
                ItemStack appearanceItemStack = getAppearanceItemStack(itemStack, true);
                if (isHiddenItem(appearanceItemStack)) {
                    return itemStack;
                }
                appearanceItemStack.setCount(itemStack.getCount());
                return appearanceItemStack;
            }
        }
        return itemStack;
    }
}
