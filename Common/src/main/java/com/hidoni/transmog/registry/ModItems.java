package com.hidoni.transmog.registry;

import com.hidoni.transmog.Constants;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ModItems {
    private static final Identifier VOID_FRAGMENT_IDENTIFIER = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "void_fragment");
    public static final RegistryEntry<Item, Item> VOID_FRAGMENT = ModRegistries.ITEM.register(VOID_FRAGMENT_IDENTIFIER, () -> new Item(new Item.Properties().rarity(Rarity.RARE).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true).setId(ResourceKey.create(Registries.ITEM, VOID_FRAGMENT_IDENTIFIER))));

    public static void register() {
    }
}
