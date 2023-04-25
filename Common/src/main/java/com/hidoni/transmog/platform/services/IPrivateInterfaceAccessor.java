package com.hidoni.transmog.platform.services;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Several registry related classes have private interfaces which we can't do anything about.
 * Every single modloader makes these public, but not vanilla, so this is required...
 */
public interface IPrivateInterfaceAccessor {
    <T extends AbstractContainerMenu> MenuType<T> createMenu(MenuSupplier<T> menuSupplier);

    <T extends BlockEntity> BlockEntityType.Builder<T> createBlockEntityTypeBuilder(BlockEntitySupplier<T> blockEntitySupplier, Block... validBlocks);

    SimpleParticleType createSimpleParticleType(boolean overrideLimiter);

    @FunctionalInterface
    interface MenuSupplier<T extends AbstractContainerMenu> {
        T create(int i, Inventory inventory);
    }

    @FunctionalInterface
    interface BlockEntitySupplier<T extends BlockEntity> {
        T create(BlockPos pPos, BlockState pState);
    }
}
