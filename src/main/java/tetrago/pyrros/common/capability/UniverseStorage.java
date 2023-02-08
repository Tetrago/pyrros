package tetrago.pyrros.common.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;
import tetrago.pyrros.common.data.Universe;

public class UniverseStorage implements IUniverseStorage, INBTSerializable<CompoundTag>
{
    private Universe mUniverse = null;

    public void setUniverse(Universe universe)
    {
        mUniverse = universe;
    }

    @Override
    public Universe getUniverse()
    {
        return mUniverse;
    }

    @Override
    public CompoundTag serializeNBT()
    {
        return mUniverse != null ? mUniverse.save(new CompoundTag()) : new CompoundTag();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt)
    {
        mUniverse = new Universe(nbt);
    }
}
