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
    }
}
