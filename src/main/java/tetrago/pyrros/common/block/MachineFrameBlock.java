package tetrago.pyrros.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class MachineFrameBlock extends MultiblockChildBlock
{
    public MachineFrameBlock()
    {
        super(Properties.of(Material.STONE).strength(1.5f, 6).noOcclusion());
    }

    @Override
    public boolean propagatesSkylightDown(BlockState pState, BlockGetter pLevel, BlockPos pPos)
    {
        return true;
    }
}
