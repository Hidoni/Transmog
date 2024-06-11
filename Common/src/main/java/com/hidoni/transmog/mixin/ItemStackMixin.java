package com.hidoni.transmog.mixin;

import com.hidoni.transmog.TransmogUtils;
import com.hidoni.transmog.config.Config;
import com.hidoni.transmog.config.TooltipDetailLevel;
import com.hidoni.transmog.i18n.TranslationKeys;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Unique
    private static final String[] TRANSLATION_KEYS_TO_REMOVE = {"item.modifiers.*", "attribute.modifier.*", "item.unbreakable", "item.canBreak", "item.canPlace", "item.durability", "item.nbt_tags", "item.disabled", "item.components"};

    @Inject(method = "getTooltipLines", at = @At(value = "RETURN"))
    private void addTransmogTooltipToItemStack(Item.TooltipContext tooltipContext, Player player, TooltipFlag tooltipFlag, CallbackInfoReturnable<List<Component>> cir) {
        ItemStack thisStack = (ItemStack) ((Object) this);
        if (Config.tooltipDetailLevel == TooltipDetailLevel.NONE || thisStack.has(DataComponents.HIDE_TOOLTIP) || thisStack.has(DataComponents.HIDE_ADDITIONAL_TOOLTIP)) {
            return;
        }
        if (TransmogUtils.isItemStackTransmogged(thisStack)) {
            ItemStack appearanceStack = TransmogUtils.getAppearanceItemStack(thisStack, true);
            List<Component> originalItemLines = cir.getReturnValue();
            originalItemLines.add(Component.translatable(TranslationKeys.TRANSMOG_DESCRIPTION_PREFIX).withStyle(ChatFormatting.LIGHT_PURPLE));
            originalItemLines.addAll(getTransmogTooltipLines(tooltipContext, player, tooltipFlag, appearanceStack));
        }
    }

    @Unique
    @NotNull
    private static List<Component> getTransmogTooltipLines(Item.TooltipContext tooltipContext, Player player, TooltipFlag tooltipFlag, ItemStack appearanceStack) {
        List<Component> components;
        if (TransmogUtils.isHiddenItem(appearanceStack)) {
            components = List.of(Component.translatable(TranslationKeys.TRANSMOG_HIDDEN).withStyle(ChatFormatting.LIGHT_PURPLE));
        } else if (Config.tooltipDetailLevel == TooltipDetailLevel.MINIMAL) {
            components = new ArrayList<>(List.of(Component.empty().append(appearanceStack.getItem().getName(appearanceStack)).withStyle(appearanceStack.getRarity().color())));
            if (tooltipFlag.isAdvanced()) {
                components.add(Component.literal(BuiltInRegistries.ITEM.getKey(appearanceStack.getItem()).toString()).withStyle(ChatFormatting.DARK_GRAY));
            }
        } else {
            components = new ArrayList<>(appearanceStack.getTooltipLines(tooltipContext, player, tooltipFlag).stream().filter(ItemStackMixin::keepComponent).toList());
            if (components.getLast().equals(CommonComponents.EMPTY)) {
                components.removeLast();
            }
        }
        return components;
    }

    @Unique
    private static boolean keepComponent(Component component) {
        if (!(component instanceof MutableComponent mutableComponent)) {
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
            if (!keepComponent(sibling)) {
                return false;
            }
        }
        return true;
    }
}
