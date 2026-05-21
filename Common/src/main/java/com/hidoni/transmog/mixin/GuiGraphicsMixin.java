package com.hidoni.transmog.mixin;

import com.hidoni.transmog.TransmogUtils;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GuiGraphicsExtractor.class)
public class GuiGraphicsMixin {
    @ModifyVariable(method = "item(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;III)V", at=@At("HEAD"), argsOnly = true)
    private ItemStack transmogItem(ItemStack stack) {
        return TransmogUtils.getAppearanceStackOrOriginal(stack, true);
    }
}
