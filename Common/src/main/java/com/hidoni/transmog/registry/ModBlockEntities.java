package com.hidoni.transmog.registry;

import com.hidoni.transmog.Constants;
import com.hidoni.transmog.block.entity.TransmogrificationTableBlockEntity;
import com.hidoni.transmog.platform.Services;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {
    public static final RegistryEntry<BlockEntityType<?>, BlockEntityType<TransmogrificationTableBlockEntity>> TRANSMOGRIFICATION_TABLE = ModRegistries.BLOCK_ENTITIES.register(Identifier.fromNamespaceAndPath(Constants.MOD_ID, "transmog_station"), () -> Services.PRIVATE_INTERFACE.createBlockEntityType(TransmogrificationTableBlockEntity::new, ModBlocks.TRANSMOGRIFICATION_TABLE.get()));

    public static void register() {
    }
}
