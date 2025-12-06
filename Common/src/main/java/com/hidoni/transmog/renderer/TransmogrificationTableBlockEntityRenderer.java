package com.hidoni.transmog.renderer;

import com.hidoni.transmog.MathUtils;
import com.hidoni.transmog.block.entity.TransmogrificationTableBlockEntity;
import com.hidoni.transmog.registry.ModItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TransmogrificationTableBlockEntityRenderer implements BlockEntityRenderer<TransmogrificationTableBlockEntity, TransmogrificationTableBlockRenderState> {
    private final ItemModelResolver itemModelResolver;

    public TransmogrificationTableBlockEntityRenderer(BlockEntityRendererProvider.Context context)
    {
        this.itemModelResolver = context.itemModelResolver();
    }

    @Override
    public @NotNull TransmogrificationTableBlockRenderState createRenderState() {
        return new TransmogrificationTableBlockRenderState();
    }

    @Override
    public void extractRenderState(@NotNull TransmogrificationTableBlockEntity blockEntity, @NotNull TransmogrificationTableBlockRenderState renderState, float partialTick, @NotNull Vec3 cameraPosition, @Nullable ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, renderState, partialTick, cameraPosition, breakProgress);

        ItemStack stack = new ItemStack(ModItems.VOID_FRAGMENT.get());
        stack.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, false);
        this.itemModelResolver.updateForTopItem(renderState.voidFragmentItemStack, stack, ItemDisplayContext.NONE, blockEntity.getLevel(), null, 0);

        renderState.heightExtra = blockEntity.ticks + partialTick;

        float deltaRotation = MathUtils.angleWithinBounds(blockEntity.rotation - blockEntity.oldRotation);
        renderState.rotation = blockEntity.oldRotation + deltaRotation * partialTick;
    }

    @Override
    public void submit(@NotNull TransmogrificationTableBlockRenderState renderState, @NotNull PoseStack poseStack, @NotNull SubmitNodeCollector nodeCollector, @NotNull CameraRenderState cameraRenderState) {
        poseStack.pushPose();

        poseStack.translate(0.5F, 0.9625F, 0.5F);
        poseStack.translate(0, 0.1F + Mth.sin(renderState.heightExtra * 0.1F) * 0.01F, 0);
        poseStack.scale(0.5F, 0.5F, 0.5F);

        poseStack.mulPose(Axis.YP.rotation(-renderState.rotation));

        renderState.voidFragmentItemStack.submit(poseStack, nodeCollector, renderState.lightCoords, OverlayTexture.NO_OVERLAY, 0);
        poseStack.popPose();
    }
}
