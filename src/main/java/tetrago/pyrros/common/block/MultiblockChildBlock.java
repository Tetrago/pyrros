package tetrago.pyrros.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import tetrago.pyrros.common.blockentity.MultiblockChildBlockEntity;

public class MultiblockChildBlock extends Block implements EntityBlock
{
    public MultiblockChildBlock(Properties pProperties)
    {
        super(pProperties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState)
    {
        return new MultiblockChildBlockEntity(pPos, pState);
    }
}
