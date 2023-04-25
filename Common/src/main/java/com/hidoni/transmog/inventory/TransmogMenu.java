package com.hidoni.transmog.inventory;

import com.hidoni.transmog.Constants;
import com.hidoni.transmog.TransmogUtils;
import com.hidoni.transmog.block.entity.TransmogrificationTableBlockEntity;
import com.hidoni.transmog.registry.ModBlocks;
import com.hidoni.transmog.registry.ModMenus;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public class TransmogMenu extends AbstractContainerMenu {
    public static final int ITEM_TO_TRANSMOG_SLOT = 0;
    public static final int APPEARANCE_ITEM_SLOT = 1;
    public static final int AMETHYST_SHARD_SLOT = 2;
    public static final int OUTPUT_SLOT = 3;
    private static final int INVENTORY_START = 4;
    private static final int INVENTORY_END = INVENTORY_START + 36;
    private static final int HOTBAR_START = INVENTORY_END - 9;
    public static final int CONTAINER_DATA_SLOT_COUNT = 1;
    private final Container inputContainer = new SimpleContainer(3) {
        public void setChanged() {
            super.setChanged();
            TransmogMenu.this.slotsChanged(this);
        }
    };
    private final ResultContainer resultContainer = new ResultContainer() {
        public void setChanged() {
            TransmogMenu.this.slotsChanged(this);
            super.setChanged();
        }
    };
    private final ContainerLevelAccess access;
    private final ContainerData transmogBlockData;

    public TransmogMenu(int i, Inventory inventory) {
        this(i, inventory, ContainerLevelAccess.NULL, new SimpleContainerData(CONTAINER_DATA_SLOT_COUNT));
    }

    public TransmogMenu(int i, Inventory inventory, ContainerLevelAccess access, ContainerData transmogBlockData) {
        super(ModMenus.TRANSMOG_MENU.get(), i);
        checkContainerDataCount(transmogBlockData, CONTAINER_DATA_SLOT_COUNT);
        this.access = access;
        this.transmogBlockData = transmogBlockData;

        this.addSlot(new Slot(this.inputContainer, ITEM_TO_TRANSMOG_SLOT, 38, 41));
        this.addSlot(new Slot(this.inputContainer, APPEARANCE_ITEM_SLOT, 87, 41) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });
        this.addSlot(new Slot(this.inputContainer, AMETHYST_SHARD_SLOT, 11, 17) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return itemStack.is(Items.AMETHYST_SHARD);
            }
        });

        this.addSlot(new Slot(this.resultContainer, OUTPUT_SLOT, 145, 41) {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return false;
            }

            @Override
            public void onTake(@NotNull Player player, @NotNull ItemStack stack) {
                super.onTake(player, stack);
                if (TransmogMenu.this.hasFuel()) {
                    TransmogMenu.this.consumeFuel();
                }
                TransmogMenu.this.getSlot(ITEM_TO_TRANSMOG_SLOT).remove(1);
                TransmogMenu.this.broadcastChanges();
            }
        });
        this.addDataSlots(transmogBlockData);

        for (int j = 0; j < 3; ++j) {
            for (int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(inventory, k + j * 9 + 9, 8 + k * 18, 84 + j * 18));
            }
        }

        for (int j = 0; j < 9; ++j) {
            this.addSlot(new Slot(inventory, j, 8 + j * 18, 142));
        }
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        Slot slot = this.getSlot(index);
        if (!slot.hasItem()) {
            return ItemStack.EMPTY;
        }
        ItemStack item = slot.getItem();
        ItemStack returnStack = item.copy();
        if (index < INVENTORY_START) {
            boolean isOutputSlot = index == OUTPUT_SLOT;
            if (!this.moveItemStackTo(item, INVENTORY_START, INVENTORY_END, isOutputSlot)) {
                return ItemStack.EMPTY;
            }
            if (isOutputSlot) {
                slot.onQuickCraft(item, returnStack);
            }
        } else {
            boolean move = true;
            if (item.is(Items.AMETHYST_SHARD)) {
                move = this.moveItemStackTo(item, AMETHYST_SHARD_SLOT, AMETHYST_SHARD_SLOT + 1, false);
            }
            if (move) {
                if (!this.moveItemStackTo(item, ITEM_TO_TRANSMOG_SLOT, OUTPUT_SLOT, false)) {
                    if (index < HOTBAR_START) {
                        if (!this.moveItemStackTo(item, HOTBAR_START, INVENTORY_END, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.moveItemStackTo(item, INVENTORY_START, HOTBAR_START, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }
        }
        if (item.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        }
        slot.setChanged();
        if (item.getCount() == returnStack.getCount()) {
            return ItemStack.EMPTY;
        }
        slot.onTake(player, item);
        this.broadcastChanges();
        return returnStack;
    }

    @Override
    public void slotsChanged(@NotNull Container container) {
        if (getFuel() == 0 && this.getSlot(AMETHYST_SHARD_SLOT).hasItem()) {
            refuel();
            this.getSlot(AMETHYST_SHARD_SLOT).remove(1);
        }
        if (canTransmogItem()) {
            this.setupResultSlot();
        } else {
            this.resultContainer.removeItemNoUpdate(OUTPUT_SLOT);
        }
    }

    private boolean canTransmogItem() {
        return this.getSlot(ITEM_TO_TRANSMOG_SLOT).hasItem() && hasFuel();
    }

    private void setupResultSlot() {
        this.access.execute((level, pos) -> {
            if (!this.getSlot(APPEARANCE_ITEM_SLOT).hasItem()) {
                if (TransmogUtils.isItemStackTransmogged(this.getSlot(ITEM_TO_TRANSMOG_SLOT).getItem())) {
                    this.outputRemovedTransmog();
                } else {
                    this.resultContainer.removeItemNoUpdate(OUTPUT_SLOT);
                }
            } else {
                this.outputTransmoggedItem();
            }
        });
    }

    private void outputRemovedTransmog() {
        ItemStack item = this.getSlot(ITEM_TO_TRANSMOG_SLOT).getItem().copy();
        item.setCount(1);
        item.removeTagKey(Constants.TRANSMOG_ITEM_TAG);
        this.resultContainer.setItem(OUTPUT_SLOT, item);
        this.broadcastChanges();
    }

    private void outputTransmoggedItem() {
        ItemStack originalItemToTransmog = this.getSlot(ITEM_TO_TRANSMOG_SLOT).getItem().copy();
        originalItemToTransmog.setCount(1);
        ItemStack itemToTransmog = createTransmoggedItem(originalItemToTransmog);
        if (ItemStack.matches(itemToTransmog, originalItemToTransmog)) {
            this.resultContainer.removeItemNoUpdate(OUTPUT_SLOT);
        } else {
            this.resultContainer.setItem(OUTPUT_SLOT, itemToTransmog);
            this.broadcastChanges();
        }
    }

    @NotNull
    public ItemStack createTransmoggedItem(ItemStack itemToTransmog) {
        ItemStack itemCopy = itemToTransmog.copy();
        itemCopy.setCount(1);
        ItemStack appearanceItem = TransmogUtils.getAppearanceItemStack(this.getSlot(APPEARANCE_ITEM_SLOT).getItem(), true).copy();
        appearanceItem.setCount(1);
        TransmogUtils.transmogAppearanceOntoItemStack(appearanceItem, itemCopy);
        return itemCopy;
    }


    private void refuel() {
        this.transmogBlockData.set(TransmogrificationTableBlockEntity.FUEL_INDEX, Constants.TRANSMOG_FUEL_FROM_SHARD);
    }

    private void consumeFuel() {
        this.transmogBlockData.set(TransmogrificationTableBlockEntity.FUEL_INDEX, this.getFuel() - 1);
    }

    public int getFuel() {
        return this.transmogBlockData.get(TransmogrificationTableBlockEntity.FUEL_INDEX);
    }

    public boolean hasFuel() {
        return this.getFuel() > 0;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(this.access, player, ModBlocks.TRANSMOGRIFICATION_TABLE.get());
    }

    @Override
    public void removed(@NotNull Player player) {
        super.removed(player);
        this.resultContainer.removeItemNoUpdate(OUTPUT_SLOT);
        this.access.execute(($$1, $$2) -> this.clearContainer(player, this.inputContainer));
    }
}
