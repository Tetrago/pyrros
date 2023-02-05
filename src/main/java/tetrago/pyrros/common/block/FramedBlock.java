package tetrago.pyrros.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class FramedBlock extends OmnidirectionalBlock
{
    public FramedBlock(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext)
    {
        return super.getStateForPlacement(pContext).setValue(OmnidirectionalBlock.DIRECTION, pContext.getClickedFace().getOpposite());
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext)
    {
        return Shapes.box(-0.00625, -0.00625, -0.00625, 1.00625, 1.00625, 1.00625);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext)
    {
        return Shapes.block();
    }

    @Override
    public VoxelShape getBlockSupportShape(BlockState pState, BlockGetter pReader, BlockPos pPos)
    {
        Direction.Axis axis = pState.getValue(OmnidirectionalBlock.DIRECTION).getAxis();
        return Shapes.box(
                axis.choose(0, 2.0f / 16, 2.0f / 16),
                axis.choose(2.0f / 16, 0, 2.0f/ 16),
                axis.choose(2.0f / 16, 2.0f / 16, 0),
                axis.choose(1, 14.0f / 16, 14.0f / 16),
                axis.choose(14.0f / 16, 1, 14.0f / 16),
                axis.choose(14.0f / 16, 14.0f / 16, 1));
    }
}
