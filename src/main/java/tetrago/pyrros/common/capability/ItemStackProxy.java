package tetrago.pyrros.common.capability;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class ItemStackProxy implements IItemHandler
{
    private final ItemStackHandler mHandler;
    private final int mSlot;

    public ItemStackProxy(ItemStackHandler handler, int slot)
    {
        mHandler = handler;
        mSlot = slot;
    }

    @Override
    public int getSlots()
    {
        return 1;
    }

    @NotNull
    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return mHandler.getStackInSlot(mSlot);
    }

    @NotNull
    @Override
    public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate)
    {
        return mHandler.insertItem(mSlot, stack, simulate);
    }

    @NotNull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate)
    {
        return mHandler.extractItem(mSlot, amount, simulate);
    }

    @Override
    public int getSlotLimit(int slot)
    {
        return mHandler.getSlotLimit(mSlot);
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack)
    {
        return mHandler.isItemValid(mSlot, stack);
    }
}
