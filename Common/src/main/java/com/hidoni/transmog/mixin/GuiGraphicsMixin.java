package com.hidoni.transmog.mixin;

import com.hidoni.transmog.TransmogUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GuiGraphics.class)
public class GuiGraphicsMixin {
    @ModifyVariable(method = "renderItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;IIII)V", at=@At("HEAD"), argsOnly = true)
    private ItemStack transmog$renderItem(ItemStack stack) {
        return TransmogUtils.getAppearanceStackOrOriginal(stack);
    }

    @ModifyVariable(method = "renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V", at=@At("HEAD"), argsOnly = true)
    private ItemStack transmog$renderItemDecorations(ItemStack stack) {
        return TransmogUtils.getAppearanceStackOrOriginal(stack);
    }
}
