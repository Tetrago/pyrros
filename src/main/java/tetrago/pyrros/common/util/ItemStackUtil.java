package tetrago.pyrros.common.util;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class ItemStackUtil
{
    public static boolean canInsertIntoStack(ItemStack to, ItemStack of)
    {
        if(to.isEmpty())
        {
            return true;
        }
        else if(to.getItem() != of.getItem())
        {
            return false;
        }
        else
        {
            return to.getCount() + of.getCount() <= to.getMaxStackSize();
        }
    }

    public static boolean canInsertIntoItemStackHandler(ItemStackHandler handler, int slot, ItemStack stack)
    {
        return canInsertIntoStack(handler.getStackInSlot(slot), stack);
    }

    public static boolean insertIntoItemStackHandler(ItemStackHandler handler, int slot, ItemStack stack)
    {
        if(!canInsertIntoItemStackHandler(handler, slot, stack)) return false;

        handler.setStackInSlot(slot, new ItemStack(stack.getItem(), handler.getStackInSlot(slot).getCount() + stack.getCount()));
        return true;
    }
}
