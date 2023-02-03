package tetrago.pyrros.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public  class MultiblockComponentBlockEntity extends BlockEntity implements IMultiblockComponent
{
    private BlockPos mPosition = null;

    public MultiblockComponentBlockEntity(BlockPos pPos, BlockState pBlockState)
    {
        this(ModBlockEntities.MULTIBLOCK_COMPONENT.get(), pPos, pBlockState);
    }

    public MultiblockComponentBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState)
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

    public void parent(BlockPos pos)
    {
        mPosition = pos;
        setChanged();
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getMultiblockCapability(@NotNull Capability<T> cap, @Nullable Direction side)
    {
        if(!isConstructed()) return LazyOptional.empty();

        return ((MultiblockBlockEntity)level.getBlockEntity(getMultiblockPos())).getMultiblockCapability(cap, side);
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
