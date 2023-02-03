package tetrago.pyrros.common.event;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.common.block.MultiblockBlock;
import tetrago.pyrros.common.blockentity.IMultiblockComponent;
import tetrago.pyrros.common.config.ModCommonConfig;
import tetrago.pyrros.common.world.feature.ModPlacedFeatures;

import java.util.List;

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

    @SubscribeEvent
    public static void onBiomeLoading(BiomeLoadingEvent event)
    {
        List<Holder<PlacedFeature>> base = event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);

        if(ModCommonConfig.BROOKITE_ORE_ENABLED.get())
        {
            base.add(ModPlacedFeatures.BROOKITE_ORE_PLACED);
        }
    }
}
