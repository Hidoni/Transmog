package com.hidoni.transmog.platform;

import com.hidoni.transmog.platform.services.IPrivateInterfaceAccessor;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ForgePrivateInterfaceAccessor implements IPrivateInterfaceAccessor {
    @Override
    public <T extends AbstractContainerMenu> MenuType<T> createMenu(MenuSupplier<T> menuSupplier) {
        return new MenuType<>(menuSupplier::create);
    }

    @Override
    public <T extends BlockEntity> BlockEntityType<T> createBlockEntityTypeBuilder(BlockEntitySupplier<T> blockEntitySupplier, Block... validBlocks) {
        return BlockEntityType.Builder.of(blockEntitySupplier::create, validBlocks).build(null);
    }

    @Override
    public SimpleParticleType createSimpleParticleType(boolean overrideLimiter) {
        return new SimpleParticleType(overrideLimiter);
    }
}
