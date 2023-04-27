package com.hidoni.transmog.mixin;

import com.hidoni.transmog.RenderUtils;
import com.hidoni.transmog.TransmogUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorStand.class)
public class ArmorStandMixin {
    @Inject(method = "getItemBySlot", at=@At("RETURN"), cancellable = true)
    private void transmogItemBySlot(EquipmentSlot slot, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack returnValue = cir.getReturnValue();
        if (TransmogUtils.isItemStackTransmogged(returnValue) && RenderUtils.INSTANCE.isCalledForRendering()) {
            cir.setReturnValue(TransmogUtils.getAppearanceStackOrOriginal(returnValue));
        }
    }
}
