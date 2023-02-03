package tetrago.pyrros.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import tetrago.pyrros.common.blockentity.ArcFurnaceControllerBlockEntity;
import tetrago.pyrros.common.blockentity.ModBlockEntities;
import tetrago.pyrros.common.util.BlockEntityUtil;

public class ArcFurnaceControllerBlock extends MultiblockBlock
{
    public ArcFurnaceControllerBlock()
    {
        super(Properties.of(Material.METAL).strength(1.5f, 6).lightLevel(state ->
                state.getValue(MultiblockBlock.CONSTRUCTED) ? 2 : 0));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext)
    {
        return super.getStateForPlacement(pContext).setValue(FlatDirectionalBlock.DIRECTION, pContext.getNearestLookingDirection().getOpposite());
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
        return new ArcFurnaceControllerBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType)
    {
        return BlockEntityUtil.tickerOf(pBlockEntityType, ModBlockEntities.ARC_FURNACE_CONTROLLER.get(), ArcFurnaceControllerBlockEntity::tick);
    }

    @Override
    protected InteractionResult useConstructed(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit)
    {
        if(!pLevel.isClientSide())
        {
            BlockEntityUtil.openGui(pLevel, pPos, pPlayer);
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }
}
