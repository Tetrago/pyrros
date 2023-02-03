package tetrago.pyrros.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import tetrago.pyrros.common.blockentity.ArcFurnaceControllerBlockEntity;
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
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState)
    {
        return new ArcFurnaceControllerBlockEntity(pPos, pState);
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
