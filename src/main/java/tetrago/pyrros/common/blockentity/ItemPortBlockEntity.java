package tetrago.pyrros.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemPortBlockEntity extends MultiblockComponentBlockEntity
{
    public ItemPortBlockEntity(BlockPos pPos, BlockState pBlockState)
    {
        super(ModBlockEntities.ITEM_PORT.get(), pPos, pBlockState);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side)
    {
        if(side != null && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            return getMultiblockCapability(cap, side);
        }

        return super.getCapability(cap, side);
    }
}
