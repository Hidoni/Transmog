package com.hidoni.transmog.registry;

import com.hidoni.transmog.Constants;
import com.hidoni.transmog.item.VoidFragmentItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ModItems {
    public static final RegistryEntry<Item, VoidFragmentItem> VOID_FRAGMENT = ModRegistries.ITEM.register(new ResourceLocation(Constants.MOD_ID, "void_fragment"), () -> new VoidFragmentItem(new Item.Properties().rarity(Rarity.RARE)));

    public static void register() {
    }
}
