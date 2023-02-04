package tetrago.pyrros.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class MultiblockProviderBlockEntity extends BlockEntity
{
    public MultiblockProviderBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState)
    {
        super(pType, pPos, pBlockState);
    }

    @Nonnull
    public abstract <T> LazyOptional<T> getMultiblockCapability(@Nonnull Capability<T> cap, @Nullable Direction side);

    public abstract boolean isConstructed();
    public abstract BlockPos getMultiblockPos();
}
