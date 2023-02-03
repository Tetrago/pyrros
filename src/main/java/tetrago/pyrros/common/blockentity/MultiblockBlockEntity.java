package tetrago.pyrros.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tetrago.pyrros.common.block.MultiblockBlock;

public abstract class MultiblockBlockEntity extends BlockEntity implements IMultiblockComponent
{
    public MultiblockBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState)
    {
        super(pType, pPos, pBlockState);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getMultiblockCapability(@NotNull Capability<T> cap, @Nullable Direction side)
    {
        return LazyOptional.empty();
    }

    @Override
    public boolean isConstructed()
    {
        return getBlockState().getValue(MultiblockBlock.CONSTRUCTED);
    }

    @Override
    public BlockPos getMultiblockPos()
    {
        return getBlockPos();
    }

    public void onConstruct() {}
    public void onDeconstruct() {}
}
