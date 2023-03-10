package tetrago.pyrros.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.client.input.ModKeyBindings;
import tetrago.pyrros.client.screen.ArcFurnaceScreen;
import tetrago.pyrros.client.screen.RollingMillScreen;
import tetrago.pyrros.common.block.ModBlocks;
import tetrago.pyrros.common.item.CoilBlockItem;
import tetrago.pyrros.common.item.ModItems;
import tetrago.pyrros.common.item.MotorBlockItem;
import tetrago.pyrros.integration.waila.ModWailaPlugin;

public class ModLanguageProvider extends LanguageProvider
{
    public ModLanguageProvider(DataGenerator gen, String locale)
    {
        super(gen, Pyrros.MODID, locale);
    }

    @Override
    protected void addTranslations()
    {
        add(ModKeyBindings.CATEGORY, "Pyrros");
        add(ModKeyBindings.UNIVERSE_MAP, "Universe Map");

        add(ArcFurnaceScreen.TITLE, "Arc Furnace");
        add(RollingMillScreen.TITLE, "Rolling Mill");

        add(CoilBlockItem.TOOLTIP_COIL_STRENGTH, "Coil Strength");
        add(MotorBlockItem.TOOLTIP_MOTOR_STRENGTH, "Motor Strength");
        add(ModWailaPlugin.TOOLTIP_CONSTRUCTED, "Multiblock");

        add(Pyrros.modid("itemGroup.{}"), "Pyrros");

        // --- Blocks --------------------------------------------------------------------------------------------------

        add(ModBlocks.ARC_FURNACE.get(), "Arc Furnace");
        add(ModBlocks.ROLLING_MILL.get(), "Rolling Mill");
        add(ModBlocks.ENERGY_PORT.get(), "Energy Port");
        add(ModBlocks.ITEM_PORT.get(), "Item Port");
        add(ModBlocks.MACHINE_FRAME.get(), "Machine Frame");
        add(ModBlocks.ENCASED_MACHINE_FRAME.get(), "Encased Machine Frame");
        add(ModBlocks.REINFORCED_MACHINE_FRAME.get(), "Reinforced Machine Frame");
        add(ModBlocks.ROLLER.get(), "Roller");
        add(ModBlocks.BEARING.get(), "Bearing");
        add(ModBlocks.BASIC_MOTOR.get(), "Basic Motor");
        add(ModBlocks.STEEL_BLOCK.get(), "Block of Steel");
        add(ModBlocks.TITANIUM_BLOCK.get(), "Block of Titanium");
        add(ModBlocks.ALUMINUM_BLOCK.get(), "Block of Aluminum");
        add(ModBlocks.BROOKITE_ORE.get(), "Brookite Ore");
        add(ModBlocks.DEEPSLATE_BROOKITE_ORE.get(), "Deepslate Brookite Ore");
        add(ModBlocks.CRYOLITE_ORE.get(), "Cryolite Ore");
        add(ModBlocks.DEEPSLATE_CRYOLITE_ORE.get(), "Deepslate Cryolite Ore");
        add(ModBlocks.COPPER_COIL.get(), "Copper Coil");
        add(ModBlocks.GOLD_COIL.get(), "Gold Coil");

        // --- Items ---------------------------------------------------------------------------------------------------

        add(ModItems.STEEL_INGOT.get(), "Steel Ingot");
        add(ModItems.STEEL_NUGGET.get(), "Steel Nugget");
        add(ModItems.RAW_BROOKITE.get(), "Raw Brookite");
        add(ModItems.RAW_CRYOLITE.get(), "Raw Cryolite");
        add(ModItems.TITANIUM_INGOT.get(), "Titanium Ingot");
        add(ModItems.TITANIUM_NUGGET.get(), "Titanium Nugget");
        add(ModItems.ALUMINUM_INGOT.get(), "Aluminum Ingot");
        add(ModItems.ALUMINUM_NUGGET.get(), "Aluminum Nugget");
        add(ModItems.SLAG.get(), "Slag");
        add(ModItems.IRON_PLATE.get(), "Iron Plate");
        add(ModItems.COPPER_PLATE.get(), "Copper Plate");
        add(ModItems.GOLD_PLATE.get(), "Gold Plate");
        add(ModItems.STEEL_PLATE.get(), "Steel Plate");
        add(ModItems.ALUMINUM_PLATE.get(), "Aluminum Plate");
        add(ModItems.TITANIUM_PLATE.get(), "Titanium Plate");
    }
}
