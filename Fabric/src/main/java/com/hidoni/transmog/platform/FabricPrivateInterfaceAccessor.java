package com.hidoni.transmog.platform;

import com.hidoni.transmog.platform.services.IPrivateInterfaceAccessor;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class FabricPrivateInterfaceAccessor implements IPrivateInterfaceAccessor {
    @Override
    public <T extends AbstractContainerMenu> MenuType<T> createMenu(MenuSupplier<T> menuSupplier) {
        return new MenuType<>(menuSupplier::create);
    }

    @Override
    public <T extends BlockEntity> BlockEntityType<T> createBlockEntityTypeBuilder(BlockEntitySupplier<T> blockEntitySupplier, Block... validBlocks) {
        return FabricBlockEntityTypeBuilder.create(blockEntitySupplier::create, validBlocks).build();
    }

    @Override
    public SimpleParticleType createSimpleParticleType(boolean overrideLimiter) {
        return FabricParticleTypes.simple(overrideLimiter);
    }
}
