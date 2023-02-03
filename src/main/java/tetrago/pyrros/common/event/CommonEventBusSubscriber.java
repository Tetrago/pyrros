package tetrago.pyrros.common.event;

import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.common.block.MultiblockBlock;
import tetrago.pyrros.common.blockentity.IMultiblockComponent;

@Mod.EventBusSubscriber(modid = Pyrros.MODID)
public class CommonEventBusSubscriber
{
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event)
    {
        if(event.getWorld().isClientSide()) return;

        if(event.getWorld().getBlockEntity(event.getPos()) instanceof IMultiblockComponent component && component.isConstructed())
        {
            event.setCanceled(true);

            MultiblockBlock block = (MultiblockBlock)event.getWorld().getBlockState(component.getMultiblockPos()).getBlock();
            block.deconstruct((ServerLevel)event.getWorld(), component.getMultiblockPos());
        }
    }
}
