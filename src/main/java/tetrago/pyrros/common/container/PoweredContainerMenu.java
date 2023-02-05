package tetrago.pyrros.common.container;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.Nullable;
import tetrago.pyrros.common.capability.ModEnergyStorage;
import tetrago.pyrros.common.container.data.EnergyData;

public class PoweredContainerMenu extends ContainerMenu
{
    protected PoweredContainerMenu(@Nullable MenuType<?> pMenuType, int pContainerId, BlockPos pos, Inventory inv)
    {
        super(pMenuType, pContainerId, pos, inv);

        addDataSlots(new EnergyData()
        {
            @Override
            protected int getEnergyStored()
            {
                return PoweredContainerMenu.this.getEnergyStored();
            }

            @Override
            protected void setEnergyStored(int energy)
            {
                mBlockEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(cap -> ((ModEnergyStorage)cap).setEnergyStored(energy));
            }
        });
    }

    public int getEnergyStored()
    {
        return mBlockEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
    }

    public int getMaxEnergyStored()
    {
        return mBlockEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getMaxEnergyStored).orElse(0);
    }
}
