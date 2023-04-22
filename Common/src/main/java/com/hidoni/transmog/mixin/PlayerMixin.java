package com.hidoni.transmog.mixin;

import com.hidoni.transmog.RenderUtils;
import com.hidoni.transmog.TransmogUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(method = "getItemBySlot", at=@At("RETURN"), cancellable = true)
    private void transmogItemBySlot(EquipmentSlot slot, CallbackInfoReturnable<ItemStack> cir) {
        if (RenderUtils.isCalledForRendering() && !RenderUtils.isCalledForInventory() && TransmogUtils.isItemStackTransmogged(cir.getReturnValue())) {
            cir.setReturnValue(TransmogUtils.getAppearanceItemStack(cir.getReturnValue(), false));
        }
    }
}
