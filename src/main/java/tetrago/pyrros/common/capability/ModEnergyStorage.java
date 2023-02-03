package tetrago.pyrros.common.capability;

import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.energy.EnergyStorage;

public abstract class ModEnergyStorage extends EnergyStorage
{
    public ModEnergyStorage(int capacity, int maxTransfer)
    {
        super(capacity, maxTransfer);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate)
    {
        int energy = super.receiveEnergy(maxReceive, simulate);
        if(energy != 0 && !simulate)
        {
            onEnergyChanged();
        }

        return energy;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate)
    {
        int energy = super.extractEnergy(maxExtract, simulate);
        if(energy != 0 && !simulate)
        {
            onEnergyChanged();
        }

        return energy;
    }

    public void setEnergyStored(int energy)
    {
        this.energy = Math.min(capacity, energy);
        onEnergyChanged();
    }

    public abstract void onEnergyChanged();

    @Override
    public Tag serializeNBT()
    {
        return IntTag.valueOf(energy);
    }

    @Override
    public void deserializeNBT(Tag nbt)
    {
        energy = ((IntTag)nbt).getAsInt();
    }
}
