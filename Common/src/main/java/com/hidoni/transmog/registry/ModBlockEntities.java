package com.hidoni.transmog.registry;

import com.hidoni.transmog.Constants;
import com.hidoni.transmog.block.entity.TransmogrificationTableBlockEntity;
import com.hidoni.transmog.platform.Services;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {
    public static final RegistryEntry<BlockEntityType<TransmogrificationTableBlockEntity>> TRANSMOGRIFICATION_TABLE = ModRegistries.BLOCK_ENTITIES.register(new ResourceLocation(Constants.MOD_ID, "transmog_station"), () -> Services.PRIVATE_INTERFACE.createBlockEntityTypeBuilder(TransmogrificationTableBlockEntity::new, ModBlocks.TRANSMOGRIFICATION_TABLE.get()).build(null));

    public static void register() {
    }
}
