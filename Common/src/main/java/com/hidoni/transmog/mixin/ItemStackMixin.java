package com.hidoni.transmog.mixin;

import com.hidoni.transmog.config.Config;
import com.hidoni.transmog.config.TooltipDetailLevel;
import com.hidoni.transmog.registry.ModDataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(method = "addDetailsToTooltip", at = @At(value = "RETURN"))
    private void addTransmogTooltipToItemStack(Item.TooltipContext context, TooltipDisplay tooltipDisplay, Player player, TooltipFlag tooltipFlag, Consumer<Component> tooltipAdder, CallbackInfo ci) {
        if (Config.tooltipDetailLevel == TooltipDetailLevel.NONE) {
            return;
        }
        ItemStack thisStack = (ItemStack) ((Object) this);
        thisStack.addToTooltip(ModDataComponents.TRANSMOG_APPEARANCE_ITEM.get(), context, tooltipDisplay, tooltipAdder, tooltipFlag);
    }
}
