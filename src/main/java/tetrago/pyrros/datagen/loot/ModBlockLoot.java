package tetrago.pyrros.datagen.loot;

import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import tetrago.pyrros.common.block.ModBlocks;

public class ModBlockLoot extends BlockLoot
{
    @Override
    protected void addTables()
    {
        dropSelf(ModBlocks.ARC_FURNACE_CONTROLLER.get());
        dropSelf(ModBlocks.ENERGY_PORT.get());
        dropSelf(ModBlocks.ITEM_PORT.get());
        dropSelf(ModBlocks.MACHINE_FRAME.get());
        dropSelf(ModBlocks.ENCASED_MACHINE_FRAME.get());
        dropSelf(ModBlocks.REINFORCED_MACHINE_FRAME.get());
        dropSelf(ModBlocks.STEEL_BLOCK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
