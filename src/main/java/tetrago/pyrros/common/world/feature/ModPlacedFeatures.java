package tetrago.pyrros.common.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import tetrago.pyrros.common.config.ModCommonConfig;

public class ModPlacedFeatures
{
    public static final Holder<PlacedFeature> BROOKITE_ORE_PLACED = PlacementUtils.register("brookite_ore_placed",
            ModConfiguredFeatures.BROOKITE_ORE, ModOrePlacement.commonOrePlacement(ModCommonConfig.BRROKITE_ORE_VEINS_PER_CHUNK.get(),
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
}
