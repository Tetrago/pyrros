package tetrago.pyrros.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;
import tetrago.pyrros.common.blockentity.MultiblockComponentBlockEntity;

public class CoilBlock extends OmnidirectionalBlock implements EntityBlock
{
    private final int mStrength;

    public CoilBlock(int strength)
    {
        super(Properties.of(Material.METAL).strength(1.5f, 6));

        mStrength = strength;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState)
    {
        return new MultiblockComponentBlockEntity(pPos, pState);
    }

    public int getCoilStrength()
    {
        return mStrength;
    }
}
