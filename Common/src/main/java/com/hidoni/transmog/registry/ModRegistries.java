package com.hidoni.transmog.registry;

import com.hidoni.transmog.platform.Services;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModRegistries {
    public static final RegistryProvider<Block> BLOCKS = Services.REGISTRY.getRegistry(Registries.BLOCK);
    public static final RegistryProvider<Item> ITEM = Services.REGISTRY.getRegistry(Registries.ITEM);
    public static final RegistryProvider<BlockEntityType<?>> BLOCK_ENTITIES = Services.REGISTRY.getRegistry(Registries.BLOCK_ENTITY_TYPE);
    public static final RegistryProvider<MenuType<?>> MENUS = Services.REGISTRY.getRegistry(Registries.MENU);
    public static final RegistryProvider<ParticleType<?>> PARTICLES = Services.REGISTRY.getRegistry(Registries.PARTICLE_TYPE);

    public static void register() {
        ModBlocks.register();
        ModItems.register();
        ModBlockEntities.register();
        ModMenus.register();
    }
}
