package tetrago.pyrros.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class RollingMillBlockEntity extends MultiblockBlockEntity
{

    public RollingMillBlockEntity(BlockPos pPos, BlockState pBlockState)
    {
        super(ModBlockEntities.ROLLING_MILL.get(), pPos, pBlockState);
    }
}
