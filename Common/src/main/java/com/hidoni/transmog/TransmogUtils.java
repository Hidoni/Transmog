package com.hidoni.transmog;

import com.hidoni.transmog.config.Config;
import com.hidoni.transmog.registry.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TransmogUtils {
    private static boolean notInPvP = true;
    private static Thread pvpTimerThread = null;

    private static final int MAX_CACHE_ENTRIES = 100;

    private static final Map<Integer, ItemStack> TRANSMOG_ITEM_CACHE = new LinkedHashMap<>(MAX_CACHE_ENTRIES, 0.75f, true) {
        public boolean removeEldestEntry(Map.Entry<Integer, ItemStack> eldest) {
            return size() > MAX_CACHE_ENTRIES + 1;
        }
    };

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
        return itemStack.getTagElement(Constants.TRANSMOG_ITEM_TAG) != null;
    }

    public static boolean isHiddenItem(ItemStack appearanceItem) {
        return appearanceItem.is(ModItems.VOID_FRAGMENT.get());
    }

    public static ItemStack getAppearanceStackFromTag(@NotNull CompoundTag transmogTag) {
        return ItemStack.of(transmogTag);
    }

    public static void transmogAppearanceOntoItemStack(ItemStack appearanceItem, ItemStack itemToTransmog) {
        itemToTransmog.addTagElement(Constants.TRANSMOG_ITEM_TAG, appearanceItem.save(new CompoundTag()));
    }

    public static ItemStack getAppearanceItemStack(ItemStack itemStack, boolean keepHiddenItem) {
        CompoundTag transmogTag = itemStack.getTagElement(Constants.TRANSMOG_ITEM_TAG);
        if (transmogTag == null || itemStack.isEmpty()) {
            return itemStack;
        }
        ItemStack appearanceItem = TRANSMOG_ITEM_CACHE.computeIfAbsent(transmogTag.hashCode(), (unused) -> getAppearanceStackFromTag(transmogTag));
        if (!keepHiddenItem && isHiddenItem(appearanceItem)) {
            return ItemStack.EMPTY;
        }
        return appearanceItem;
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
