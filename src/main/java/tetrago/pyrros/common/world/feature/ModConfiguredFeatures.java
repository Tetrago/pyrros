package tetrago.pyrros.common.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import tetrago.pyrros.common.block.ModBlocks;
import tetrago.pyrros.common.config.ModCommonConfig;

import java.util.List;

public class ModConfiguredFeatures
{
    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_BROOKITE_ORE = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.BROOKITE_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_BROOKITE_ORE.get().defaultBlockState()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> BROOKITE_ORE = FeatureUtils.register("brookite_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_BROOKITE_ORE, ModCommonConfig.BRROKITE_ORE_VEIN_SIZE.get()));
}
