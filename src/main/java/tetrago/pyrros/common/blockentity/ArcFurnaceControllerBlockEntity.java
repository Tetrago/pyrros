package tetrago.pyrros.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tetrago.pyrros.client.screen.ArcFurnaceControllerScreen;
import tetrago.pyrros.common.capability.ModEnergyStorage;
import tetrago.pyrros.common.container.ArcFurnaceControllerContainer;

public class ArcFurnaceControllerBlockEntity extends MultiblockBlockEntity implements MenuProvider
{
    private final ModEnergyStorage mEnergyStorage = new ModEnergyStorage(50000, 1000)
    {
        @Override
        public void onEnergyChanged()
        {
            setChanged();
        }
    };
    private final LazyOptional<IEnergyStorage> mEnergyStorageCapability = LazyOptional.of(() -> mEnergyStorage);

    private final ItemStackHandler mItemStackHandler = new ItemStackHandler(3);
    private final LazyOptional<IItemHandler> mItemStackHandlerCapability = LazyOptional.of(() -> mItemStackHandler);

    public ArcFurnaceControllerBlockEntity(BlockPos pPos, BlockState pBlockState)
    {
        super(ModBlockEntities.ARC_FURNACE_CONTROLLER.get(), pPos, pBlockState);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getMultiblockCapability(@NotNull Capability<T> cap, @Nullable Direction side)
    {
        if(cap == CapabilityEnergy.ENERGY)
        {
            return mEnergyStorageCapability.cast();
        }

        return super.getMultiblockCapability(cap, side);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side)
    {
        if(side == null)
        {
            if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            {
                return mItemStackHandlerCapability.cast();
            }
            else if(cap == CapabilityEnergy.ENERGY)
            {
                return mEnergyStorageCapability.cast();
            }
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void load(CompoundTag pTag)
    {
        super.load(pTag);

        mEnergyStorage.deserializeNBT(pTag.get("energy"));
        mItemStackHandler.deserializeNBT(pTag.getCompound("inventory"));
    }

    @Override
    protected void saveAdditional(CompoundTag pTag)
    {
        super.saveAdditional(pTag);

        pTag.put("energy", mEnergyStorage.serializeNBT());
        pTag.put("inventory", mItemStackHandler.serializeNBT());
    }

    @Override
    public void invalidateCaps()
    {
        super.invalidateCaps();

        mEnergyStorageCapability.invalidate();
        mItemStackHandlerCapability.invalidate();
    }

    @Override
    public Component getDisplayName()
    {
        return new TranslatableComponent(ArcFurnaceControllerScreen.UNLOCALIZED_NAME);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer)
    {
        return new ArcFurnaceControllerContainer(pContainerId, pPlayerInventory, this);
    }

    @Override
    public void onDeconstruct()
    {
        super.onDeconstruct();

        SimpleContainer inventory = new SimpleContainer(mItemStackHandler.getSlots());
        for(int i = 0; i < mItemStackHandler.getSlots(); ++i)
        {
            inventory.setItem(i, mItemStackHandler.getStackInSlot(i));
        }

        Containers.dropContents(level, worldPosition, inventory);
    }
}
