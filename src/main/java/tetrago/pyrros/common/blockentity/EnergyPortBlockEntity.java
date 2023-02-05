package tetrago.pyrros.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EnergyPortBlockEntity extends MultiblockComponentBlockEntity
{
    public EnergyPortBlockEntity(BlockPos pPos, BlockState pBlockState)
    {
        super(ModBlockEntities.ENERGY_PORT.get(), pPos, pBlockState);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side)
    {
        if(side != null && cap == CapabilityEnergy.ENERGY)
        {
            return getMultiblockCapability(cap, side);
        }

        return super.getCapability(cap, side);
    }
}
