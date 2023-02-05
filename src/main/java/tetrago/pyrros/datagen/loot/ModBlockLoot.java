package tetrago.pyrros.datagen.loot;

import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import tetrago.pyrros.common.block.ModBlocks;
import tetrago.pyrros.common.item.ModItems;

public class ModBlockLoot extends BlockLoot
{
    @Override
    protected void addTables()
    {
        add(ModBlocks.BROOKITE_ORE.get(), block -> createOreDrop(block, ModItems.RAW_BROOKITE.get()));
        add(ModBlocks.DEEPSLATE_BROOKITE_ORE.get(), block -> createOreDrop(block, ModItems.RAW_BROOKITE.get()));
        add(ModBlocks.CRYOLITE_ORE.get(), block -> createOreDrop(block, ModItems.RAW_CRYOLITE.get()));
        add(ModBlocks.DEEPSLATE_CRYOLITE_ORE.get(), block -> createOreDrop(block, ModItems.RAW_CRYOLITE.get()));

        dropSelf(ModBlocks.ARC_FURNACE.get());
        dropSelf(ModBlocks.ENERGY_PORT.get());
        dropSelf(ModBlocks.ITEM_PORT.get());
        dropSelf(ModBlocks.MACHINE_FRAME.get());
        dropSelf(ModBlocks.ENCASED_MACHINE_FRAME.get());
        dropSelf(ModBlocks.REINFORCED_MACHINE_FRAME.get());
        dropSelf(ModBlocks.ROLLER.get());
        dropSelf(ModBlocks.BEARING.get());
        dropSelf(ModBlocks.BASIC_MOTOR.get());
        dropSelf(ModBlocks.STEEL_BLOCK.get());
        dropSelf(ModBlocks.TITANIUM_BLOCK.get());
        dropSelf(ModBlocks.ALUMINUM_BLOCK.get());
        dropSelf(ModBlocks.COPPER_COIL.get());
        dropSelf(ModBlocks.GOLD_COIL.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
