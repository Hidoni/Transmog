package com.hidoni.transmog.data.client;

import com.hidoni.transmog.registry.ModBlocks;
import com.hidoni.transmog.registry.ModItems;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BlockItem;

import java.util.function.BiConsumer;

public class ModItemModelGenerators extends ItemModelGenerators {
    public ModItemModelGenerators(ItemModelOutput itemModelOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
        super(itemModelOutput, modelOutput);
    }

    @Override
    public void run() {
        this.generateFlatItem(ModItems.VOID_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);
        Identifier transmogrificationTableModel = ModelLocationUtils.getModelLocation(ModBlocks.TRANSMOGRIFICATION_TABLE.get());
        this.itemModelOutput.accept(ModBlocks.TRANSMOGRIFICATION_TABLE.get().asItem(), ItemModelUtils.plainModel(transmogrificationTableModel));
    }
}
