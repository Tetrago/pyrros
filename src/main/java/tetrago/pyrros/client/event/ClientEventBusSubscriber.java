package tetrago.pyrros.client.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.client.input.ModKeyBindings;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Pyrros.MODID)
public class ClientEventBusSubscriber
{
    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event)
    {
        if(ModKeyBindings.sUniverseMap.consumeClick())
        {
            // Key logic
        }
    }
}
