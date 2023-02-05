package tetrago.pyrros.client.event;


import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.client.screen.ArcFurnaceScreen;
import tetrago.pyrros.common.block.ModBlocks;
import tetrago.pyrros.common.container.ModContainers;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Pyrros.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventBusSubscriber
{
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.MACHINE_FRAME.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.BEARING.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.BASIC_MOTOR.get(), RenderType.cutout());

            MenuScreens.register(ModContainers.ARC_FURNACE_CONTROLLER.get(), ArcFurnaceScreen::new);
        });
    }
}
