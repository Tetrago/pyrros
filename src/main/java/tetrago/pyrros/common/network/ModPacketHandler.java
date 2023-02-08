package tetrago.pyrros.common.network;

import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import tetrago.pyrros.Pyrros;

public class ModPacketHandler
{
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(Pyrros.loc("main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals);

    private static int sId = 0;

    private static int makeId()
    {
        return sId++;
    }

    public static void register()
    {
        INSTANCE.registerMessage(makeId(), UniversePacket.class, UniversePacket::encode, UniversePacket::decode, UniversePacket::handle);
    }
}
