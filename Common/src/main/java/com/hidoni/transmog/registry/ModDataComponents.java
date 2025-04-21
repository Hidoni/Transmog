package com.hidoni.transmog.registry;

import com.hidoni.transmog.Constants;
import com.hidoni.transmog.item.component.TransmogAppearanceItem;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceLocation;

public class ModDataComponents {
    public static final RegistryEntry<DataComponentType<?>, DataComponentType<TransmogAppearanceItem>> TRANSMOG_APPEARANCE_ITEM = ModRegistries.DATA_COMPONENT_TYPES.register(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "appearance_item"), () -> DataComponentType.<TransmogAppearanceItem>builder().persistent(TransmogAppearanceItem.CODEC).networkSynchronized(TransmogAppearanceItem.STREAM_CODEC).build());

    public static void register() {
    }
}
