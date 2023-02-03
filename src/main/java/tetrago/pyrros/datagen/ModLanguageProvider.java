package tetrago.pyrros.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.client.screen.ArcFurnaceControllerScreen;
import tetrago.pyrros.common.block.ModBlocks;
import tetrago.pyrros.common.item.ModItems;

public class ModLanguageProvider extends LanguageProvider
{
    public ModLanguageProvider(DataGenerator gen, String locale)
    {
        super(gen, Pyrros.MODID, locale);
    }

    @Override
    protected void addTranslations()
    {
        add(ArcFurnaceControllerScreen.UNLOCALIZED_NAME, "Arc Furnace Controller");

        add(Pyrros.modid("itemGroup.{}"), "Pyrros");

        add(ModBlocks.ARC_FURNACE_CONTROLLER.get(), "Arc Furnace Controller");
        add(ModBlocks.ENERGY_PORT.get(), "Energy Port");
        add(ModBlocks.ITEM_PORT.get(), "Item Port");
        add(ModBlocks.MACHINE_FRAME.get(), "Machine Frame");
        add(ModBlocks.ENCASED_MACHINE_FRAME.get(), "Encased Machine Frame");
        add(ModBlocks.REINFORCED_MACHINE_FRAME.get(), "Reinforced Machine Frame");
        add(ModBlocks.STEEL_BLOCK.get(), "Block of Steel");
        add(ModBlocks.TITANIUM_BLOCK.get(), "Block of Titanium");
        add(ModBlocks.BROOKITE_ORE.get(), "Brookite Ore");
        add(ModBlocks.DEEPSLATE_BROOKITE_ORE.get(), "Deepslate Brookite Ore");

        add(ModItems.STEEL_INGOT.get(), "Steel Ingot");
        add(ModItems.STEEL_NUGGET.get(), "Steel Nugget");
        add(ModItems.RAW_BROOKITE.get(), "Raw Brookite");
        add(ModItems.TITANIUM_INGOT.get(), "Titanium Ingot");
        add(ModItems.TITANIUM_NUGGET.get(), "Titanium Nugget");
    }
}
