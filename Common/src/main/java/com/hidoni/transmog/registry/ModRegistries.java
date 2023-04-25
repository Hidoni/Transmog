package com.hidoni.transmog.registry;

import com.hidoni.transmog.platform.Services;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModRegistries {
    public static final RegistryProvider<Block> BLOCKS = Services.REGISTRY.getRegistry(Registry.BLOCK_REGISTRY);
    public static final RegistryProvider<Item> ITEM = Services.REGISTRY.getRegistry(Registry.ITEM_REGISTRY);
    public static final RegistryProvider<BlockEntityType<?>> BLOCK_ENTITIES = Services.REGISTRY.getRegistry(Registry.BLOCK_ENTITY_TYPE_REGISTRY);
    public static final RegistryProvider<MenuType<?>> MENUS = Services.REGISTRY.getRegistry(Registry.MENU_REGISTRY);
    public static final RegistryProvider<ParticleType<?>> PARTICLES = Services.REGISTRY.getRegistry(Registry.PARTICLE_TYPE_REGISTRY);

    public static void register() {
        ModBlocks.register();
        ModItems.register();
        ModBlockEntities.register();
        ModMenus.register();
    }
}
