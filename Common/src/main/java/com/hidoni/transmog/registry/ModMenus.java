package com.hidoni.transmog.registry;

import com.hidoni.transmog.Constants;
import com.hidoni.transmog.inventory.TransmogMenu;
import com.hidoni.transmog.platform.Services;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

public class ModMenus {
    public static final RegistryEntry<MenuType<?>, MenuType<TransmogMenu>> TRANSMOG_MENU = ModRegistries.MENUS.register(new ResourceLocation(Constants.MOD_ID, "transmog"), () -> Services.PRIVATE_INTERFACE.createMenu(TransmogMenu::new, FeatureFlags.VANILLA_SET));

    public static void register() {
    }
}
