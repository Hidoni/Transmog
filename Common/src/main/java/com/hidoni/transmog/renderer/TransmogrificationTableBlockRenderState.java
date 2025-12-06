package com.hidoni.transmog.renderer;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;

public class TransmogrificationTableBlockRenderState extends BlockEntityRenderState {
    public final ItemStackRenderState voidFragmentItemStack = new ItemStackRenderState();
    public float heightExtra;
    public float rotation;
}
