package tetrago.pyrros.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import tetrago.pyrros.common.block.MultiblockBlock;

public abstract class MultiblockParentBlockEntity extends BlockEntity implements IMultiblockComponent
{
    public MultiblockParentBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState)
    {
        super(pType, pPos, pBlockState);
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
}
