package com.hidoni.transmog.item.component;

import com.hidoni.transmog.TransmogUtils;
import com.hidoni.transmog.config.Config;
import com.hidoni.transmog.config.TooltipDetailLevel;
import com.hidoni.transmog.i18n.TranslationKeys;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;

public record TransmogAppearanceItem(ItemStack itemStack) implements TooltipProvider {
    public static final Codec<TransmogAppearanceItem> CODEC = RecordCodecBuilder.create(instance -> instance.group(ItemStack.SINGLE_ITEM_CODEC.fieldOf("itemStack").forGetter(TransmogAppearanceItem::itemStack)).apply(instance, TransmogAppearanceItem::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, TransmogAppearanceItem> STREAM_CODEC = StreamCodec.composite(ItemStack.OPTIONAL_STREAM_CODEC, TransmogAppearanceItem::itemStack, TransmogAppearanceItem::new);
    private static final String[] TRANSLATION_KEYS_TO_REMOVE = {"item.op_warning.*", "item.durability", "item.disabled", "item.components"};

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

    @Override
    public void addToTooltip(Item.@NotNull TooltipContext tooltipContext, @NotNull Consumer<Component> consumer, @NotNull TooltipFlag tooltipFlag, @NotNull DataComponentGetter dataComponentGetter) {
        consumer.accept(CommonComponents.EMPTY);
        consumer.accept(Component.translatable(TranslationKeys.TRANSMOG_DESCRIPTION_PREFIX).withStyle(ChatFormatting.LIGHT_PURPLE));
        if (TransmogUtils.isHiddenItem(itemStack)) {
            consumer.accept(Component.translatable(TranslationKeys.TRANSMOG_HIDDEN).withStyle(ChatFormatting.LIGHT_PURPLE));
        } else if (Config.tooltipDetailLevel == TooltipDetailLevel.MINIMAL) {
            consumer.accept(Component.empty().append(itemStack.getItem().getName(itemStack)).withStyle(itemStack.getRarity().color()));
            if (tooltipFlag.isAdvanced()) {
                consumer.accept(Component.literal(BuiltInRegistries.ITEM.getKey(itemStack.getItem()).toString()).withStyle(ChatFormatting.DARK_GRAY));
            }
        } else {
            List<Component> tooltipLines = itemStack.getTooltipLines(tooltipContext, null, tooltipFlag).stream().filter(TransmogAppearanceItem::keepTooltipLine).toList();
            if (tooltipLines.getLast().equals(CommonComponents.EMPTY)) {
                tooltipLines.removeLast();
            }
            for (Component tooltipLine : tooltipLines) {
                if (keepTooltipLine(tooltipLine)) {
                    consumer.accept(tooltipLine);
                }
            }
        }
    }

    private static boolean keepTooltipLine(Component tooltipLine) {
        if (!(tooltipLine instanceof MutableComponent mutableComponent)) {
            return true;
        }
        if (mutableComponent.getContents() instanceof TranslatableContents translatableContents) {
            for (String translationKey : TRANSLATION_KEYS_TO_REMOVE) {
                if (translationKey.endsWith("*") && translatableContents.getKey().startsWith(translationKey.replace("*", ""))) {
                    return false;
                } else if (translatableContents.getKey().equals(translationKey)) {
                    return false;
                }
            }
        }
        for (Component sibling : mutableComponent.getSiblings()) {
            if (!keepTooltipLine(sibling)) {
                return false;
            }
        }
        return true;
    }
}
