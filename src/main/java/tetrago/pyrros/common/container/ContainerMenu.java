package tetrago.pyrros.common.container;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.Nullable;

public abstract class ContainerMenu extends AbstractContainerMenu
{
    protected final BlockEntity mBlockEntity;
    protected final IItemHandler mInventory;

    protected ContainerMenu(@Nullable MenuType<?> pMenuType, int pContainerId, BlockPos pos, Inventory inv)
    {
        super(pMenuType, pContainerId);

        mBlockEntity = inv.player.getCommandSenderWorld().getBlockEntity(pos);
        mInventory = new InvWrapper(inv);
    }
    public final ItemStack quickMoveStack(Player player, int index, int count)
    {
        Slot slot = slots.get(index);
        if(!slot.hasItem()) return ItemStack.EMPTY;
        ItemStack stack = slot.getItem();

        if(index < 36)
        {
            if(!moveItemStackTo(stack, 36, 36 + count, false))
            {
                return ItemStack.EMPTY;
            }
        }
        else if(index < 36 + count)
        {
            if(!moveItemStackTo(stack, 0, 36, false))
            {
                return ItemStack.EMPTY;
            }
        }
        else
        {
            return ItemStack.EMPTY;
        }

        if(stack.getCount() == 0)
        {
            slot.set(ItemStack.EMPTY);
        }
        else
        {
            slot.setChanged();
        }

        slot.onTake(player, stack);
        return stack.copy();
    }

    protected int addSlotRange(IItemHandler handler, int index, int x, int y, int count, int dx)
    {
        for(int i = 0; i < count; ++i)
        {
            addSlot(new SlotItemHandler(handler, index, x, y));

            ++index;
            x += dx;
        }

        return index;
    }

    protected void addPlayerInventory()
    {
        addPlayerInventory(8, 84, 18, 18);
    }

    protected void addPlayerInventory(int x, int y, int dx, int dy)
    {
        int index = 9;

        for(int i = 0; i < 3; ++i)
        {
            index = addSlotRange(mInventory, index, x, y, 9, dx);
            y += dy;
        }
    }
    protected void addPlayerHotbar()
    {
        addPlayerHotbar(8, 142, 18);
    }

    protected void addPlayerHotbar(int x, int y, int dx)
    {
        addSlotRange(mInventory, 0, x, y, 9, dx);
    }

    @Override
    public boolean stillValid(Player pPlayer)
    {
        return stillValid(ContainerLevelAccess.create(mBlockEntity.getLevel(), mBlockEntity.getBlockPos()), pPlayer, mBlockEntity.getBlockState().getBlock());
    }
}
