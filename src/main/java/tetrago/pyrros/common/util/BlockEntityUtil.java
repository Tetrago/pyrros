package tetrago.pyrros.common.util;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;

public final class BlockEntityUtil
{
    public static <T extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> tickerOf(BlockEntityType<A> type, BlockEntityType<T> target, BlockEntityTicker<? super T> ticker)
    {
        return type == target ? (BlockEntityTicker<A>)ticker : null;
    }

    public static boolean openGui(Level level, BlockPos pos, Player player)
    {
        if(level.isClientSide()) return false;

        final BlockEntity be = level.getBlockEntity(pos);
        if(be instanceof MenuProvider tile)
        {
            NetworkHooks.openGui((ServerPlayer)player, tile, pos);
            return true;
        }

        return false;
    }

    public static SimpleContainer offload(ItemStackHandler itemStackHandler)
    {
        SimpleContainer container = new SimpleContainer(itemStackHandler.getSlots());
        for(int i = 0; i < itemStackHandler.getSlots(); ++i)
        {
            container.setItem(i, itemStackHandler.getStackInSlot(i));
        }

        return container;
    }
}
