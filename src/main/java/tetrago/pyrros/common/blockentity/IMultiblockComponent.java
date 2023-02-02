package tetrago.pyrros.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface IMultiblockComponent
{
    @Nonnull
    default <T> LazyOptional<T> getMultiblockCapability(@Nonnull Capability<T> cap)
    {
        return getMultiblockCapability(cap, null);
    }

    @Nonnull
    <T> LazyOptional<T> getMultiblockCapability(@Nonnull Capability<T> cap, @Nullable Direction side);

    boolean isConstructed();
    BlockPos getMultiblockPos();
}
