package tetrago.pyrros.client.event;


import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.common.block.ModBlocks;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Pyrros.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventBusSubscriber
{
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.MACHINE_FRAME.get(), RenderType.cutout());
        });
    }
}
