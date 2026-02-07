package com.hidoni.transmog.registry;

import com.hidoni.transmog.Constants;
import com.hidoni.transmog.block.TransmogrificationTableBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Supplier;

public class ModBlocks {
    private static final Identifier TRANSMOGRIFICATION_TABLE_IDENTIFIER = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "transmogrification_table");
    public static final RegistryEntry<Block, TransmogrificationTableBlock> TRANSMOGRIFICATION_TABLE = registerWithItem(TRANSMOGRIFICATION_TABLE_IDENTIFIER, () -> new TransmogrificationTableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).sound(SoundType.AMETHYST).requiresCorrectToolForDrops().strength(1.5F).noOcclusion().dynamicShape().setId(ResourceKey.create(Registries.BLOCK, TRANSMOGRIFICATION_TABLE_IDENTIFIER))));

    public static void register() {
    }

    private static <T extends Block> RegistryEntry<Block, T> registerWithItem(Identifier identifier, Supplier<T> blockSupplier) {
        RegistryEntry<Block, T> block = ModRegistries.BLOCKS.register(identifier, blockSupplier);
        ModRegistries.ITEM.register(identifier, () -> new BlockItem(block.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, identifier)).useBlockDescriptionPrefix()));
        return block;
    }
}
