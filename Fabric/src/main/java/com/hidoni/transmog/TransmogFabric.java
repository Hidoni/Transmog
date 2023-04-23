package com.hidoni.transmog;

import com.hidoni.transmog.i18n.TranslationKeys;
import com.hidoni.transmog.item.VoidFragmentItem;
import com.hidoni.transmog.registry.ModBlocks;
import com.hidoni.transmog.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class TransmogFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Transmog.init();
        FabricItemGroup.builder(new ResourceLocation(Constants.MOD_ID, "creative_tab"))
                .icon(() -> {
                    ItemStack itemStack = new ItemStack(ModItems.VOID_FRAGMENT.get());
                    itemStack.getOrCreateTag().putBoolean(VoidFragmentItem.VOID_FRAGMENT_SHOW_FOIL_KEY, false);
                    return itemStack;
                })
                .title(Component.translatable(TranslationKeys.TRANSMOG_CREATIVE_MODE_TAB_NAME))
                .displayItems(((itemDisplayParameters, output) -> {
                    output.accept(ModItems.VOID_FRAGMENT.get());
                    output.accept(ModBlocks.TRANSMOGRIFICATION_TABLE.get());
                }))
                .build();
    }
}
