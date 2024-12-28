package com.hidoni.transmog.data.client;

import com.hidoni.transmog.registry.ModBlocks;
import com.hidoni.transmog.registry.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class ModModelProvider extends ModelProvider {

    public ModModelProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected @NotNull Stream<Block> getKnownBlocks() {
        return Stream.of(ModBlocks.TRANSMOGRIFICATION_TABLE.get());
    }

    @Override
    protected @NotNull Stream<Item> getKnownItems() {
        return Stream.of(ModItems.VOID_FRAGMENT.get());
    }

    @Override
    protected @NotNull BlockModelGenerators getBlockModelGenerators(@NotNull BlockStateGeneratorCollector blocks, @NotNull ItemInfoCollector items, @NotNull SimpleModelCollector models) {
        return new ModBlockModelGenerator(blocks, items, models);
    }

    @Override
    protected @NotNull ItemModelGenerators getItemModelGenerators(@NotNull ItemInfoCollector items, @NotNull SimpleModelCollector models) {
        return new ModItemModelGenerators(items, models);
    }
}
