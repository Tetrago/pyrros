package tetrago.pyrros.common.capability;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class UniverseStorageProvider implements ICapabilityProvider
{
    private final UniverseStorage mUniverseStorage = new UniverseStorage();
    private final LazyOptional<IUniverseStorage> mUniverseStorageCapability = LazyOptional.of(() -> mUniverseStorage);

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side)
    {
        return cap == ModCapabilities.UNIVERSE ? mUniverseStorageCapability.cast() : null;
    }
}
