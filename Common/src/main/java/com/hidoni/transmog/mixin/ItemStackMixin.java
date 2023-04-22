package com.hidoni.transmog.mixin;

import com.hidoni.transmog.TransmogUtils;
import com.hidoni.transmog.i18n.TranslationKeys;
import com.hidoni.transmog.registry.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    private static final String[] TRANSLATION_KEYS_TO_REMOVE = {"item.modifiers.*", "attribute.modifier.*", "item.unbreakable", "item.canBreak", "item.canPlace", "item.durability", "item.nbt_tags", "item.disabled"};

    @Inject(method = "getTooltipLines", at = @At("RETURN"))
    private void addTransmogTooltipToItemStack(Player player, TooltipFlag tooltipFlag, CallbackInfoReturnable<List<Component>> cir) {
        ItemStack thisStack = (ItemStack) ((Object) this);
        if (TransmogUtils.isItemStackTransmogged(thisStack)) {
            ItemStack transmogStack = TransmogUtils.getAppearanceItemStack(thisStack, true);
            List<Component> validComponentsList;
            if (transmogStack.is(ModItems.VOID_FRAGMENT.get())) {
                validComponentsList = List.of(Component.translatable(TranslationKeys.TRANSMOG_HIDDEN).withStyle(ChatFormatting.LIGHT_PURPLE));
            } else {
                validComponentsList = new ArrayList<>(transmogStack.getTooltipLines(player, tooltipFlag).stream().filter(ItemStackMixin::keepComponent).toList());
                if (validComponentsList.get(validComponentsList.size() - 1).equals(CommonComponents.EMPTY)) {
                    validComponentsList.remove(validComponentsList.size() - 1);
                }
            }
            List<Component> originalItemLines = cir.getReturnValue();
            originalItemLines.add(Component.translatable(TranslationKeys.TRANSMOG_DESCRIPTION_PREFIX).withStyle(ChatFormatting.LIGHT_PURPLE));
            originalItemLines.addAll(validComponentsList);
        }
    }

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
