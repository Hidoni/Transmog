package com.hidoni.transmog.registry;

import com.hidoni.transmog.Constants;
import com.hidoni.transmog.block.TransmogrificationTableBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Supplier;

public class ModBlocks {
    public static final RegistryEntry<Block, TransmogrificationTableBlock> TRANSMOGRIFICATION_TABLE = registerWithItem(new ResourceLocation(Constants.MOD_ID, "transmogrification_table"), () -> new TransmogrificationTableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).sound(SoundType.AMETHYST).requiresCorrectToolForDrops().strength(1.5F).noOcclusion().dynamicShape()));

    public static void register() {
    }

    private static <T extends Block> RegistryEntry<Block, T> registerWithItem(ResourceLocation location, Supplier<T> blockSupplier) {
        RegistryEntry<Block, T> block = ModRegistries.BLOCKS.register(location, blockSupplier);
        ModRegistries.ITEM.register(location, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }
}
