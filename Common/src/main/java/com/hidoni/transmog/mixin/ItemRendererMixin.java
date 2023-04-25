package com.hidoni.transmog.mixin;

import com.hidoni.transmog.TransmogUtils;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {
    @ModifyVariable(method = "render", at=@At("HEAD"), argsOnly = true)
    private ItemStack transmog$render(ItemStack stack) {
        return TransmogUtils.getAppearanceStackOrOriginal(stack);
    }

    @ModifyVariable(method = "getModel", at=@At("HEAD"), argsOnly = true)
    private ItemStack transmog$getModel(ItemStack stack) {
        return TransmogUtils.getAppearanceStackOrOriginal(stack);
    }

    @ModifyVariable(method = "renderStatic(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/client/renderer/block/model/ItemTransforms$TransformType;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/world/level/Level;III)V", at=@At("HEAD"), argsOnly = true)
    private ItemStack transmog$renderStatic(ItemStack stack) {
        return TransmogUtils.getAppearanceStackOrOriginal(stack);
    }

    @ModifyVariable(method = "renderGuiItem(Lnet/minecraft/world/item/ItemStack;IILnet/minecraft/client/resources/model/BakedModel;)V", at=@At("HEAD"), argsOnly = true)
    private ItemStack transmog$renderGuiItem(ItemStack stack) {
        return TransmogUtils.getAppearanceStackOrOriginal(stack);
    }

    @ModifyVariable(method = "tryRenderGuiItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;IIII)V", at=@At("HEAD"), argsOnly = true)
    private ItemStack transmog$tryRenderGuiItem(ItemStack stack) {
        return TransmogUtils.getAppearanceStackOrOriginal(stack);
    }

    @ModifyVariable(method = "renderGuiItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V", at=@At("HEAD"), argsOnly = true)
    private ItemStack transmog$renderGuiItemDecorations(ItemStack stack) {
        return TransmogUtils.getAppearanceStackOrOriginal(stack);
    }
}
