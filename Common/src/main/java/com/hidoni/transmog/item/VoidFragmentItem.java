package com.hidoni.transmog.item;

import com.hidoni.transmog.Constants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class VoidFragmentItem extends Item {
    public static final String VOID_FRAGMENT_SHOW_FOIL_KEY = Constants.MOD_ID + ":showFoil";
    public VoidFragmentItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(@NotNull ItemStack itemStack) {
        CompoundTag tag = itemStack.getTag();
        if (tag == null || tag.getTagType(VOID_FRAGMENT_SHOW_FOIL_KEY) != Tag.TAG_BYTE) {
            return true;
        }
        return tag.getBoolean(VOID_FRAGMENT_SHOW_FOIL_KEY);
    }
}
