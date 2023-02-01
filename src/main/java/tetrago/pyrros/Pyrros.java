package tetrago.pyrros;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import tetrago.pyrros.common.block.ModBlocks;
import tetrago.pyrros.common.item.ModItems;

@Mod(Pyrros.MODID)
public class Pyrros
{
    public static final String MODID = "pyrros";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Pyrros()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
    }

    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(MODID)
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(ModItems.MACHINE_FRAME.get());
        }
    };

    public static ResourceLocation loc(String path)
    {
        return new ResourceLocation(MODID, path);
    }

    public static String modid(String format)
    {
        return format.replaceAll("\\{\\}", MODID);
    }
}
