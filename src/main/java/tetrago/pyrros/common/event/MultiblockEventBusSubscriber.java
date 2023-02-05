package tetrago.pyrros.common.event;

import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.common.block.*;
import tetrago.pyrros.common.blockentity.MultiblockProviderBlockEntity;

@Mod.EventBusSubscriber(modid = Pyrros.MODID)
public class MultiblockEventBusSubscriber
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

    @SubscribeEvent
    public static void onMultiblockConstruction(MultiblockConstructionEvent event)
    {
        if(event.getRecipe().getId().getPath().equals("arc_furnace"))
        {
            // Make sure all coils are vertical
            if(event.getBlockPositions().stream().map(event.getLevel()::getBlockState).filter(state -> state.getBlock() instanceof CoilBlock)
                    .noneMatch(state -> state.getValue(OmnidirectionalBlock.DIRECTION).getAxis() == Direction.Axis.Y))
            {
                event.setCanceled(true);
            }

            // Make sure the ArcFurnaceBlock is facing outward
            if(event.getLevel().getBlockState(event.getMultiblockPos()).getValue(FlatDirectionalBlock.DIRECTION) != event.getRotation().rotate(Direction.NORTH))
            {
                event.setCanceled(true);
            }
        }

        if(event.getRecipe().getId().getPath().equals("rolling_mill"))
        {
            // Make sure that all the directional blocks in the structure (the motors, bearings, and rollers) are all on the same axis.
            if(event.getBlockPositions().stream().map(event.getLevel()::getBlockState).filter(state -> state.hasProperty(OmnidirectionalBlock.DIRECTION))
                    .map(state -> state.getValue(OmnidirectionalBlock.DIRECTION)).map(Direction::getAxis).distinct().count() > 1)
            {
                event.setCanceled(true);
            }
        }
    }
}
