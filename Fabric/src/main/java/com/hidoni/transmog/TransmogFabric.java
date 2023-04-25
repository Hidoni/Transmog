package com.hidoni.transmog;

import com.hidoni.transmog.item.VoidFragmentItem;
import com.hidoni.transmog.registry.ModBlocks;
import com.hidoni.transmog.registry.ModItemGroups;
import com.hidoni.transmog.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class TransmogFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ModItemGroups.TRANSMOG_CREATIVE_TAB = FabricItemGroupBuilder.create(new ResourceLocation(Constants.MOD_ID, "creative_tab"))
                .icon(() -> {
                    ItemStack itemStack = new ItemStack(ModItems.VOID_FRAGMENT.get());
                    itemStack.getOrCreateTag().putBoolean(VoidFragmentItem.VOID_FRAGMENT_SHOW_FOIL_KEY, false);
                    return itemStack;
                })
                .appendItems((itemStacks -> itemStacks.addAll(List.of(new ItemStack(ModItems.VOID_FRAGMENT.get()), new ItemStack(ModBlocks.TRANSMOGRIFICATION_TABLE.get())))))
                .build();
        Transmog.init();
    }
}
