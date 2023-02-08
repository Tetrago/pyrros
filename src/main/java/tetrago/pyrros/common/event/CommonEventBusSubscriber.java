package tetrago.pyrros.common.event;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.common.capability.ModCapabilities;
import tetrago.pyrros.common.capability.UniverseStorageProvider;
import tetrago.pyrros.common.config.ModCommonConfig;
import tetrago.pyrros.common.data.Universe;
import tetrago.pyrros.common.network.ModPacketHandler;
import tetrago.pyrros.common.network.UniversePacket;
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

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
        if(!event.getWorld().isClientSide() && event.getEntity() instanceof ServerPlayer player)
        {
            Universe universe = Universe.getInstance(event.getWorld());
            ModPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new UniversePacket(universe));
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event)
    {
        ModCapabilities.register(event);
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Level> event)
    {
        if(event.getObject().isClientSide())
        {
            event.addCapability(Pyrros.loc("universe"), new UniverseStorageProvider());
        }
    }
}
