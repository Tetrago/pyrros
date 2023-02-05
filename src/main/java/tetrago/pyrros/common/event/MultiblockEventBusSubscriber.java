package tetrago.pyrros.common.event;

import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.common.block.*;
import tetrago.pyrros.common.config.ModCommonConfig;
import tetrago.pyrros.common.world.feature.ModPlacedFeatures;

import java.util.List;

@Mod.EventBusSubscriber(modid = Pyrros.MODID)
public class MultiblockEventBusSubscriber
{
    @SubscribeEvent
    public static void onBiomeLoading(BiomeLoadingEvent event)
    {
        List<Holder<PlacedFeature>> base = event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);

        if(ModCommonConfig.BROOKITE_ORE_ENABLED.get()) base.add(ModPlacedFeatures.BROOKITE_ORE_PLACED);
        if(ModCommonConfig.CRYOLITE_ORE_ENABLED.get()) base.add(ModPlacedFeatures.CRYOLITE_ORE_PLACED);
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
