package com.hidoni.transmog.registry;

import com.hidoni.transmog.Constants;
import com.hidoni.transmog.i18n.TranslationKeys;
import com.hidoni.transmog.item.VoidFragmentItem;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {
    public static final RegistryEntry<CreativeModeTab> TRANSMOG_TAB = ModRegistries.CREATIVE_MODE_TABS.register(new ResourceLocation(Constants.MOD_ID, "creative_tab"), () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
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
            .build()
    );

    public static void register() {
    }
}
