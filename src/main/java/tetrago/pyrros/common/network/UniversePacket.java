package tetrago.pyrros.common.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UniversePacket
{
    public static void encode(UniversePacket msg, FriendlyByteBuf data)
    {

    }

    public static UniversePacket decode(FriendlyByteBuf data)
    {
        return new UniversePacket();
    }

    public void handle(Supplier<NetworkEvent.Context> ctx)
    {

    }
}
