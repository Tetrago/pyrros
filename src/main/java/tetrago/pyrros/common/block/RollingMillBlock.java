package tetrago.pyrros.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;
import tetrago.pyrros.common.blockentity.RollingMillBlockEntity;

public class RollingMillBlock extends MultiblockBlock
{
    public RollingMillBlock()
    {
        super(Properties.of(Material.METAL).strength(1.5f, 6));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext)
    {
        return super.getStateForPlacement(pContext).setValue(FlatDirectionalBlock.DIRECTION, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder)
    {
        super.createBlockStateDefinition(pBuilder);

        pBuilder.add(FlatDirectionalBlock.DIRECTION);
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation)
    {
        return super.rotate(pState, pRotation).setValue(FlatDirectionalBlock.DIRECTION, pRotation.rotate(pState.getValue(FlatDirectionalBlock.DIRECTION)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror)
    {
        return super.mirror(pState, pMirror).setValue(FlatDirectionalBlock.DIRECTION, pMirror.mirror(pState.getValue(FlatDirectionalBlock.DIRECTION)));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState)
    {
        return new RollingMillBlockEntity(pPos, pState);
    }
}
