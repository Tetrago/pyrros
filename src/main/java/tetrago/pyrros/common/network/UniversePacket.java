package tetrago.pyrros.common.network;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import tetrago.pyrros.common.capability.ModCapabilities;
import tetrago.pyrros.common.capability.UniverseStorage;
import tetrago.pyrros.common.data.Universe;

import java.util.function.Supplier;

public class UniversePacket
{
    private final Universe mUniverse;

    public UniversePacket(Universe universe)
    {
        mUniverse = universe;
    }

    public static void encode(UniversePacket msg, FriendlyByteBuf data)
    {
        data.writeNbt(msg.mUniverse.save(new CompoundTag()));
    }

    public static UniversePacket decode(FriendlyByteBuf data)
    {
        return new UniversePacket(new Universe(data.readNbt()));
    }

    public void handle(Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->
                Minecraft.getInstance().player.level.getCapability(ModCapabilities.UNIVERSE).ifPresent(cap ->
                        ((UniverseStorage)cap).setUniverse(mUniverse))));

        ctx.get().setPacketHandled(true);
    }
}
