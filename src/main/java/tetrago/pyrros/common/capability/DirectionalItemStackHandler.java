package tetrago.pyrros.common.capability;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Rotation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class DirectionalItemStackHandler
{
    private final ItemStackHandler mHandler;
    private final Map<Rotation, LazyOptional<IItemHandler>> mItemHandlers = new HashMap<>();

    public DirectionalItemStackHandler(ItemStackHandler handler, Map<Rotation, Integer> mappings)
    {
        mHandler = handler;

        mappings.forEach((rotation, slot) -> {
            ItemStackProxy proxy = new ItemStackProxy(mHandler, slot);
            mItemHandlers.put(rotation, LazyOptional.of(() -> proxy));
        });
    }

    @NotNull
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side, Direction relativeTo)
    {
        if(cap != CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return LazyOptional.empty();

        for(Rotation rotation : mItemHandlers.keySet())
        {
            if(side != rotation.rotate(relativeTo)) continue;
            return mItemHandlers.get(rotation).cast();
        }

        return LazyOptional.empty();
    }

    public void invalidate()
    {
        mItemHandlers.forEach((rotation, cap) -> cap.invalidate());
    }
}
