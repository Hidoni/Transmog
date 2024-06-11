package com.hidoni.transmog.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

public record TransmogAppearanceItem(ItemStack itemStack) {
    public static final Codec<TransmogAppearanceItem> CODEC = RecordCodecBuilder.create(instance -> instance.group(ItemStack.SINGLE_ITEM_CODEC.fieldOf("itemStack").forGetter(TransmogAppearanceItem::itemStack)).apply(instance, TransmogAppearanceItem::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, TransmogAppearanceItem> STREAM_CODEC = StreamCodec.composite(ItemStack.OPTIONAL_STREAM_CODEC, TransmogAppearanceItem::itemStack, TransmogAppearanceItem::new);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransmogAppearanceItem that = (TransmogAppearanceItem) o;
        return ItemStack.isSameItemSameComponents(itemStack, that.itemStack);
    }

    @Override
    public int hashCode() {
        return ItemStack.hashItemAndComponents(itemStack);
    }
}
