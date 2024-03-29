package com.hidoni.transmog.platform;

import com.hidoni.transmog.platform.services.IPrivateInterfaceAccessor;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class NeoForgePrivateInterfaceAccessor implements IPrivateInterfaceAccessor {
    @Override
    public <T extends AbstractContainerMenu> MenuType<T> createMenu(MenuSupplier<T> menuSupplier, FeatureFlagSet featureFlagSet) {
        return new MenuType<>(menuSupplier::create, featureFlagSet);
    }

    @Override
    public <T extends BlockEntity> BlockEntityType.Builder<T> createBlockEntityTypeBuilder(BlockEntitySupplier<T> blockEntitySupplier, Block... validBlocks) {
        return BlockEntityType.Builder.of(blockEntitySupplier::create, validBlocks);
    }

    @Override
    public SimpleParticleType createSimpleParticleType(boolean overrideLimiter) {
        return new SimpleParticleType(overrideLimiter);
    }
}
