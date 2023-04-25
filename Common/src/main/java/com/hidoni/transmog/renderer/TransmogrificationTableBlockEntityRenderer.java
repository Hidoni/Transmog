package com.hidoni.transmog.renderer;

import com.hidoni.transmog.MathUtils;
import com.hidoni.transmog.block.entity.TransmogrificationTableBlockEntity;
import com.hidoni.transmog.item.VoidFragmentItem;
import com.hidoni.transmog.registry.ModItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TransmogrificationTableBlockEntityRenderer implements BlockEntityRenderer<TransmogrificationTableBlockEntity> {
    public TransmogrificationTableBlockEntityRenderer(BlockEntityRendererProvider.Context ignoredContext)
    {
    }

    @Override
    public void render(@NotNull TransmogrificationTableBlockEntity blockEntity, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack stack = new ItemStack(ModItems.VOID_FRAGMENT.get());
        stack.getOrCreateTag().putBoolean(VoidFragmentItem.VOID_FRAGMENT_SHOW_FOIL_KEY, false);

        poseStack.translate(0.5F, 0.9625F, 0.5F);
        float heightExtra = blockEntity.ticks + partialTick;
        poseStack.translate(0, 0.1F + Mth.sin(heightExtra * 0.1F) * 0.01F, 0);
        poseStack.scale(0.5F, 0.5F, 0.5F);

        float deltaRotation = MathUtils.angleWithinBounds(blockEntity.rotation - blockEntity.oldRotation);
        float rotation = blockEntity.oldRotation + deltaRotation * partialTick;
        poseStack.mulPose(Vector3f.YP.rotation(-rotation));

        itemRenderer.renderStatic(null, stack, ItemTransforms.TransformType.NONE, false, poseStack, bufferSource, blockEntity.getLevel(), packedLight, OverlayTexture.NO_OVERLAY, 0);
        poseStack.popPose();
    }
}
