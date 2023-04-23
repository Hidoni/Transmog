package com.hidoni.transmog.data.client;

import com.hidoni.transmog.i18n.TranslationKeys;
import com.hidoni.transmog.registry.ModBlocks;
import com.hidoni.transmog.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output, String modid) {
        super(output, modid, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(ModItems.VOID_FRAGMENT.get(), "Void Fragment");
        add(ModBlocks.TRANSMOGRIFICATION_TABLE.get(), "Transmogrification Table");
        add(TranslationKeys.TRANSMOG_DESCRIPTION_PREFIX, "Transmogrified to:");
        add(TranslationKeys.TRANSMOG_HIDDEN, "Hidden Item");
        add(TranslationKeys.TRANSMOG_CONTAINER_TITLE, "Transmogrify");
        add(TranslationKeys.TRANSMOG_CREATIVE_MODE_TAB_NAME, "Transmog");
        add(TranslationKeys.TRANSMOG_CONFIG_TITLE, "Transmog");
        add(TranslationKeys.TRANSMOG_CONFIG_OPTION_SHOW_TRANSMOGS, "Show Transmogs");
        add(TranslationKeys.TRANSMOG_CONFIG_OPTION_SHOW_TRANSMOGS_DESCRIPTION, "Control whether transmogged appearances are shown. If this is OFF, the original appearance of transmogrified items will be shown.");
        add(TranslationKeys.TRANSMOG_CONFIG_OPTION_SHOW_TRANSMOGS_IN_INVENTORY, "Show Transmogs in Inventory");
        add(TranslationKeys.TRANSMOG_CONFIG_OPTION_SHOW_TRANSMOGS_IN_INVENTORY_DESCRIPTION, "Control whether transmogged appearances are shown in the inventory and hotbar. If this is ON, Transmogrified items will look like what they're transmogrified to in the inventory too (Hidden items will still show up as themselves).");
    }
}
