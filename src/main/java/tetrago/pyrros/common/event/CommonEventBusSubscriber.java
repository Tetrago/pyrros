package tetrago.pyrros.common.event;

import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.common.block.MultiblockBlock;
import tetrago.pyrros.common.blockentity.MultiblockProviderBlockEntity;

@Mod.EventBusSubscriber(modid = Pyrros.MODID)
public class CommonEventBusSubscriber
{
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event)
    {
        if(event.getWorld().isClientSide()) return;

        if(event.getWorld().getBlockEntity(event.getPos()) instanceof MultiblockProviderBlockEntity provider && provider.isConstructed())
        {
            event.setCanceled(true);

            MultiblockBlock block = (MultiblockBlock)event.getWorld().getBlockState(provider.getMultiblockPos()).getBlock();
            block.deconstruct((ServerLevel)event.getWorld(), provider.getMultiblockPos());
        }
    }
}
