package com.hidoni.transmog.data.client;

import com.hidoni.transmog.registry.ModBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ModelProvider;

public class ModBlockModelGenerator extends BlockModelGenerators {
    public ModBlockModelGenerator(ModelProvider.BlockStateGeneratorCollector blocks, ModelProvider.ItemInfoCollector items, ModelProvider.SimpleModelCollector models) {
        super(blocks, items, models);
    }

    @Override
    public void run() {
        this.createNonTemplateModelBlock(ModBlocks.TRANSMOGRIFICATION_TABLE.get());
    }
}
