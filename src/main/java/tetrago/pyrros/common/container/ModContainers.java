package tetrago.pyrros.common.container;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import tetrago.pyrros.Pyrros;

public class ModContainers
{
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Pyrros.MODID);

    public static final RegistryObject<MenuType<ArcFurnaceContainer>> ARC_FURNACE = CONTAINERS.register("arc_furance", () -> IForgeMenuType.create(ArcFurnaceContainer::new));
    public static final RegistryObject<MenuType<RollingMillContainer>> ROLLING_MILL = CONTAINERS.register("rolling_mill", () -> IForgeMenuType.create(RollingMillContainer::new));
}
