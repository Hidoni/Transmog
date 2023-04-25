package com.hidoni.transmog.data;

import com.hidoni.transmog.registry.ModBlocks;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootSubProvider extends BlockLoot {
    @Override
    protected void addTables() {
        this.add(ModBlocks.TRANSMOGRIFICATION_TABLE.get(), BlockLoot::createNameableBlockEntityTable);
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return Set.of(ModBlocks.TRANSMOGRIFICATION_TABLE.get());
    }
}
