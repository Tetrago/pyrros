package tetrago.pyrros.client.event;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.client.input.ModKeyBindings;
import tetrago.pyrros.common.capability.ModCapabilities;
import tetrago.pyrros.common.data.Planet;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Pyrros.MODID)
public class ClientEventBusSubscriber
{
    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event)
    {
        if(ModKeyBindings.sUniverseMap.consumeClick())
        {
            Minecraft.getInstance().player.level.getCapability(ModCapabilities.UNIVERSE).ifPresent(cap ->
            {
                for(Planet planet : cap.getUniverse().getPlanets())
                {
                    Minecraft.getInstance().player.sendMessage(new TextComponent(planet.getName() + ": " + planet.getDistance().asMegameters() + "Mm"), Util.NIL_UUID);
                }
            });
        }
    }
}
