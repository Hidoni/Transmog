package com.hidoni.transmog.registry;

import com.hidoni.transmog.Constants;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ModItems {
    public static final RegistryEntry<Item, Item> VOID_FRAGMENT = ModRegistries.ITEM.register(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "void_fragment"), () -> new Item(new Item.Properties().rarity(Rarity.RARE).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));

    public static void register() {
    }
}
