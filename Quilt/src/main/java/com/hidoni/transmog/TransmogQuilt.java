package com.hidoni.transmog;

import com.hidoni.transmog.i18n.TranslationKeys;
import com.hidoni.transmog.item.VoidFragmentItem;
import com.hidoni.transmog.registry.ModItemGroups;
import com.hidoni.transmog.registry.ModItems;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.item.group.api.QuiltItemGroup;

public class TransmogQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        ModItemGroups.TRANSMOG_CREATIVE_TAB = QuiltItemGroup.builder(new ResourceLocation(Constants.MOD_ID, "creative_tab"))
                .icon(() -> {
                    ItemStack itemStack = new ItemStack(ModItems.VOID_FRAGMENT.get());
                    itemStack.getOrCreateTag().putBoolean(VoidFragmentItem.VOID_FRAGMENT_SHOW_FOIL_KEY, false);
                    return itemStack;
                })
                .displayText(new TranslatableComponent(TranslationKeys.TRANSMOG_CREATIVE_MODE_TAB_NAME))
                .build();
        Transmog.init();
    }
}
