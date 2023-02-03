package tetrago.pyrros.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;
import tetrago.pyrros.common.blockentity.ItemPortBlockEntity;

public class ItemPortBlock extends Block implements EntityBlock
{
    public ItemPortBlock()
    {
        super(Properties.of(Material.METAL).strength(1.5f, 6));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState)
    {
        return new ItemPortBlockEntity(pPos, pState);
    }
}
