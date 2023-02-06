package tetrago.pyrros.common.event;

import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.common.config.ModCommonConfig;
import tetrago.pyrros.common.world.feature.ModPlacedFeatures;

import java.util.List;

@Mod.EventBusSubscriber(modid = Pyrros.MODID)
public class CommonEventBusSubscriber
{
    @SubscribeEvent
    public static void onBiomeLoading(BiomeLoadingEvent event)
    {
        List<Holder<PlacedFeature>> base = event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);

        if(ModCommonConfig.BROOKITE_ORE_ENABLED.get()) base.add(ModPlacedFeatures.BROOKITE_ORE_PLACED);
        if(ModCommonConfig.CRYOLITE_ORE_ENABLED.get()) base.add(ModPlacedFeatures.CRYOLITE_ORE_PLACED);
    }
}
