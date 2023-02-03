package tetrago.pyrros.common.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.common.block.ModBlocks;

public class ModItems
{
    public static final Item.Properties PROPERTIES = new Item.Properties().tab(Pyrros.ITEM_GROUP);

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Pyrros.MODID);

    public static final RegistryObject<Item> ARC_FURNACE_CONTROLLER = fromBlock(ModBlocks.ARC_FURNACE_CONTROLLER);
    public static final RegistryObject<Item> ENERGY_PORT = fromBlock(ModBlocks.ENERGY_PORT);
    public static final RegistryObject<Item> MACHINE_FRAME = fromBlock(ModBlocks.MACHINE_FRAME);
    public static final RegistryObject<Item> ENCASED_MACHINE_FRAME = fromBlock(ModBlocks.ENCASED_MACHINE_FRAME);
    public static final RegistryObject<Item> REINFORCED_MACHINE_FRAME = fromBlock(ModBlocks.REINFORCED_MACHINE_FRAME);

    private static RegistryObject<Item> fromBlock(RegistryObject<Block> block)
    {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), PROPERTIES));
    }
}
