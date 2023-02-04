package tetrago.pyrros.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import tetrago.pyrros.common.blockentity.MultiblockComponentBlockEntity;

public class RollerBlock extends OmnidirectionalBlock implements EntityBlock
{
    public RollerBlock()
    {
        super(Properties.of(Material.METAL).strength(1.5f, 6));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext)
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

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState)
    {
        return new MultiblockComponentBlockEntity(pPos, pState);
    }
}
