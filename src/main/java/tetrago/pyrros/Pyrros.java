package tetrago.pyrros;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import tetrago.pyrros.client.input.ModKeyBindings;
import tetrago.pyrros.client.screen.ArcFurnaceScreen;
import tetrago.pyrros.client.screen.RollingMillScreen;
import tetrago.pyrros.common.block.ModBlocks;
import tetrago.pyrros.common.blockentity.ModBlockEntities;
import tetrago.pyrros.common.config.ModCommonConfig;
import tetrago.pyrros.common.container.ModContainers;
import tetrago.pyrros.common.item.ModItems;
import tetrago.pyrros.common.network.ModPacketHandler;
import tetrago.pyrros.common.recipe.ModRecipeSerializers;

@Mod(Pyrros.MODID)
public class Pyrros
{
    public static final String MODID = "pyrros";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Pyrros()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::onClientSetup);
        bus.addListener(this::onCommonSetup);

        ModBlocks.BLOCKS.register(bus);
        ModBlockEntities.BLOCK_ENTITIES.register(bus);
        ModContainers.CONTAINERS.register(bus);
        ModItems.ITEMS.register(bus);
        ModRecipeSerializers.RECIPE_SERIALIZERS.register(bus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModCommonConfig.SPEC, "pyrros-common.toml");
    }

    private void onClientSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.MACHINE_FRAME.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.BEARING.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.BASIC_MOTOR.get(), RenderType.cutout());

            MenuScreens.register(ModContainers.ARC_FURNACE.get(), ArcFurnaceScreen::new);
            MenuScreens.register(ModContainers.ROLLING_MILL.get(), RollingMillScreen::new);
        });

        ModKeyBindings.register();
    }

    private void onCommonSetup(FMLCommonSetupEvent event)
    {
        ModPacketHandler.register();
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
