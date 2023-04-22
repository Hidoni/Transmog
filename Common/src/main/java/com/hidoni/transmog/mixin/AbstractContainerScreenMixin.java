package com.hidoni.transmog.mixin;

import com.hidoni.transmog.TransmogUtils;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(AbstractContainerScreen.class)
public class AbstractContainerScreenMixin {
    @ModifyVariable(method = "renderSlot", at = @At("STORE"))
    private ItemStack transmog$renderSlot(ItemStack stack) {
        return TransmogUtils.getAppearanceStackOrOriginal(stack);
    }
}
