package tetrago.pyrros.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public  class MultiblockChildBlockEntity extends BlockEntity implements IMultiblockComponent
{
    private BlockPos mPosition = null;

    public MultiblockChildBlockEntity(BlockPos pPos, BlockState pBlockState)
    {
        this(ModBlockEntities.MULTIBLOCK_CHILD.get(), pPos, pBlockState);
    }

    public MultiblockChildBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState)
    {
        super(pType, pPos, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag)
    {
        super.saveAdditional(pTag);

        if(mPosition != null)
        {
            pTag.put("position", NbtUtils.writeBlockPos(mPosition));
        }
    }

    @Override
    public void load(CompoundTag pTag)
    {
        super.load(pTag);

        if(pTag.contains("position"))
        {
            mPosition = NbtUtils.readBlockPos(pTag.getCompound("position"));
        }
    }

    @Override
    public boolean isConstructed()
    {
        return mPosition != null;
    }

    @Override
    public BlockPos getMultiblockPos()
    {
        return mPosition;
    }
}
