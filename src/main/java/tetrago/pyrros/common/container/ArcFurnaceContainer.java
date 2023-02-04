package tetrago.pyrros.common.container;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import tetrago.pyrros.common.capability.ModEnergyStorage;
import tetrago.pyrros.common.container.data.EnergyData;
import tetrago.pyrros.common.container.slot.OutputSlot;

public class ArcFurnaceContainer extends ContainerMenu
{
    private final ContainerData mData;

    public ArcFurnaceContainer(int pContainerId, Inventory inventory, FriendlyByteBuf data)
    {
        this(pContainerId, inventory, inventory.player.level.getBlockEntity(data.readBlockPos()), new SimpleContainerData(2));
    }

    public ArcFurnaceContainer(int pContainerId, Inventory inv, BlockEntity blockEntity, ContainerData data)
    {
        super(ModContainers.ARC_FURNACE_CONTROLLER.get(), pContainerId, blockEntity.getBlockPos(), inv);

        mData = data;

        checkContainerSize(inv, 3);

        addPlayerInventory();
        addPlayerHotbar();

        blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(cap -> {
            addSlot(new SlotItemHandler(cap, 0, 44, 35));
            addSlot(new OutputSlot(cap, 1, 104, 35));
            addSlot(new OutputSlot(cap, 2, 131, 35));
        });

        addDataSlots(mData);

        addDataSlots(new EnergyData()
        {
            @Override
            protected int getEnergyStored()
            {
                return ArcFurnaceContainer.this.getEnergyStored();
            }

            @Override
            protected void setEnergyStored(int energy)
            {
                blockEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(cap -> ((ModEnergyStorage)cap).setEnergyStored(energy));
            }
        });
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex)
    {
        return quickMoveStack(pPlayer, pIndex, 3);
    }

    public boolean isCrafting()
    {
        return mData.get(0) > 0;
    }

    public int getScaledProgress()
    {
        int progress = mData.get(0);
        int maxProgress = mData.get(1);

        return maxProgress > 0 ? progress * 22 / maxProgress : 0;
    }

    public int getEnergyStored()
    {
        return mBlockEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
    }

    public int getMaxEnergyStored()
    {
        return mBlockEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getMaxEnergyStored).orElse(0);
    }
}
