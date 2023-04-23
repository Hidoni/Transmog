package com.hidoni.transmog;

import com.hidoni.transmog.config.Config;
import com.hidoni.transmog.registry.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;

public class TransmogUtils {
    public static boolean isItemStackTransmogged(ItemStack itemStack) {
        return itemStack.getTagElement(Constants.TRANSMOG_ITEM_TAG) != null;
    }

    public static boolean isHiddenItem(ItemStack appearanceItem) {
        return appearanceItem.is(ModItems.VOID_FRAGMENT.get());
    }

    public static ItemStack getAppearanceStackFromItemStack(ItemStack itemStack) {
        return ItemStack.of(Objects.requireNonNull(itemStack.getTagElement(Constants.TRANSMOG_ITEM_TAG)));
    }

    public static void transmogAppearanceOntoItemStack(ItemStack appearanceItem, ItemStack itemToTransmog) {
        itemToTransmog.addTagElement(Constants.TRANSMOG_ITEM_TAG, appearanceItem.save(new CompoundTag()));
    }

    public static ItemStack getAppearanceItemStack(ItemStack itemStack, boolean keepHiddenItem) {
        if (itemStack.getTagElement(Constants.TRANSMOG_ITEM_TAG) == null || itemStack.isEmpty()) {
            return itemStack;
        }
        ItemStack appearanceItem = getAppearanceStackFromItemStack(itemStack);
        if (!keepHiddenItem && isHiddenItem(appearanceItem)) {
            return ItemStack.EMPTY;
        }
        return appearanceItem;
    }

    public static ItemStack getAppearanceStackOrOriginal(ItemStack itemStack) {
        if (Config.showTransmogs && isItemStackTransmogged(itemStack)) {
            if (!RenderUtils.isCalledForInventory()) {
                return getAppearanceItemStack(itemStack, false);
            }
            else if (Config.showTransmogsInInventory) {
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
