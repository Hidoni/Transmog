package com.hidoni.transmog.data.client;

import com.hidoni.transmog.i18n.TranslationKeys;
import com.hidoni.transmog.registry.ModBlocks;
import com.hidoni.transmog.registry.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(DataGenerator generator, String modid) {
        super(generator, modid, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(ModItems.VOID_FRAGMENT.get(), "Void Fragment");
        add(ModBlocks.TRANSMOGRIFICATION_TABLE.get(), "Transmogrification Table");
        add(TranslationKeys.TRANSMOG_DESCRIPTION_PREFIX, "Transmogrified to:");
        add(TranslationKeys.TRANSMOG_HIDDEN, "Hidden Item");
        add(TranslationKeys.TRANSMOG_CONTAINER_TITLE, "Transmogrify");
        add(TranslationKeys.TRANSMOG_CREATIVE_MODE_TAB_NAME, "Transmog");
        add("itemGroup." + TranslationKeys.TRANSMOG_CREATIVE_MODE_TAB_NAME, "Transmog"); // 1.19.2 Backport: Fabric does not let us override the item group tab name!
        add(TranslationKeys.TRANSMOG_CONFIG_TITLE, "Transmog");
        add(TranslationKeys.TRANSMOG_CONFIG_RENDER_OPTIONS, "Render Transmogs");
        add(TranslationKeys.TRANSMOG_CONFIG_RENDER_OPTION_OFF, "OFF");
        add(TranslationKeys.TRANSMOG_CONFIG_RENDER_OPTION_OFF_CAPTION, "Don't render transmogs in world or inventory, this effectively disables the mod on your client, only keeping tooltips in the inventory.");
        add(TranslationKeys.TRANSMOG_CONFIG_RENDER_OPTION_IN_WORLD, "In World");
        add(TranslationKeys.TRANSMOG_CONFIG_RENDER_OPTION_IN_WORLD_CAPTION, "Render transmogs in world only. Items in inventories and the hotbar will look like the original item and only show their transmogrified appearance when held, equipped or otherwise rendered in the world. This is the default option.");
        add(TranslationKeys.TRANSMOG_CONFIG_RENDER_OPTION_EVERYWHERE, "In World & Inventory");
        add(TranslationKeys.TRANSMOG_CONFIG_RENDER_OPTION_EVERYWHERE_CAPTION, "Render transmogs in world, inventories and the hotbar. Items will look like the item they're transmogrified as even in your inventory (Hidden items will still show up as the original item in the inventory). This might get confusing!");
    }
}
