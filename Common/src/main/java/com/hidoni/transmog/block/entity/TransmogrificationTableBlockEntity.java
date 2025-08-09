package com.hidoni.transmog.block.entity;

import com.hidoni.transmog.MathUtils;
import com.hidoni.transmog.i18n.TranslationKeys;
import com.hidoni.transmog.inventory.TransmogMenu;
import com.hidoni.transmog.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class TransmogrificationTableBlockEntity extends BlockEntity implements MenuProvider, Nameable {
    public static final int FUEL_INDEX = 0;
    private Component name;
    private final ContainerData dataAccess;
    public int fuel;
    public float oldRotation;
    public float rotation;
    public float targetRotation;
    public int ticks;

    public TransmogrificationTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TRANSMOGRIFICATION_TABLE.get(), pos, state);
        this.dataAccess = new ContainerData() {
            @Override
            public int get(int index) {
                if (index != FUEL_INDEX) {
                    return 0;
                }
                return TransmogrificationTableBlockEntity.this.fuel;
            }

            @Override
            public void set(int index, int value) {
                if (index == FUEL_INDEX) {
                    TransmogrificationTableBlockEntity.this.fuel = value;
                    TransmogrificationTableBlockEntity.this.setChanged();
                }
            }

            @Override
            public int getCount() {
                return 1;
            }
        };
    }

    public static void rotationTick(Level level, BlockPos pos, BlockState state, TransmogrificationTableBlockEntity entity) {
        entity.oldRotation = entity.rotation;
        entity.rotation = MathUtils.angleWithinBounds(entity.rotation);
        entity.targetRotation = MathUtils.angleWithinBounds(entity.targetRotation + 0.02F);
        float rotationAdd = MathUtils.angleWithinBounds(entity.targetRotation - entity.rotation);
        entity.rotation += rotationAdd * 0.4F;
        entity.ticks++;
    }

    @Override
    protected void saveAdditional(@NotNull ValueOutput output) {
        super.saveAdditional(output);
        output.putByte("Fuel", (byte) this.fuel);
        output.storeNullable("CustomName", ComponentSerialization.CODEC, this.name);
    }

    @Override
    public void loadAdditional(@NotNull ValueInput input) {
        super.loadAdditional(input);
        this.fuel = input.getByteOr("Fuel", (byte) 0);
        this.name = parseCustomNameSafe(input, "CustomName");
    }

    @Override
    public @NotNull Component getName() {
        return this.name != null ? this.name : Component.translatable(TranslationKeys.TRANSMOG_CONTAINER_TITLE);
    }

    public void setCustomName(@Nullable Component component) {
        this.name = component;
    }

    @Nullable
    public Component getCustomName() {
        return this.name;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return this.getName();
    }

    @Override
    @NotNull
    public AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory, @NotNull Player player) {
        return new TransmogMenu(containerId, inventory, level == null ? ContainerLevelAccess.NULL : ContainerLevelAccess.create(this.level, this.worldPosition), this.dataAccess);
    }

    @Override
    protected void applyImplicitComponents(@NotNull DataComponentGetter componentGetter) {
        super.applyImplicitComponents(componentGetter);
        this.name = componentGetter.get(DataComponents.CUSTOM_NAME);
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.@NotNull Builder dataComponentInput) {
        super.collectImplicitComponents(dataComponentInput);
        dataComponentInput.set(DataComponents.CUSTOM_NAME, this.name);
    }
}
