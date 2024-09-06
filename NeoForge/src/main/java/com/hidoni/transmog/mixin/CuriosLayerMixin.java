package com.hidoni.transmog.mixin;

import com.hidoni.transmog.TransmogUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@SuppressWarnings("UnresolvedMixinReference")
@Pseudo
@Mixin(targets = "top.theillusivec4.curios.client.render.CuriosLayer")
public class CuriosLayerMixin {
    @Redirect(method = "lambda$render$1", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getItem()Lnet/minecraft/world/item/Item;"))
    public Item transmog$setFinalCuriosRenderItem(ItemStack in) {
        return TransmogUtils.getAppearanceStackOrOriginal(in).getItem();
    }
}
