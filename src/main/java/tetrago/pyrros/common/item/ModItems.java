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

    // --- Blocks ------------------------------------------------------------------------------------------------------

    public static final RegistryObject<Item> ARC_FURNACE = fromBlock(ModBlocks.ARC_FURNACE);
    public static final RegistryObject<Item> ROLLING_MILL = fromBlock(ModBlocks.ROLLING_MILL);
    public static final RegistryObject<Item> ENERGY_PORT = fromBlock(ModBlocks.ENERGY_PORT);
    public static final RegistryObject<Item> ITEM_PORT = fromBlock(ModBlocks.ITEM_PORT);
    public static final RegistryObject<Item> MACHINE_FRAME = fromBlock(ModBlocks.MACHINE_FRAME);
    public static final RegistryObject<Item> ENCASED_MACHINE_FRAME = fromBlock(ModBlocks.ENCASED_MACHINE_FRAME);
    public static final RegistryObject<Item> REINFORCED_MACHINE_FRAME = fromBlock(ModBlocks.REINFORCED_MACHINE_FRAME);
    public static final RegistryObject<Item> ROLLER = fromBlock(ModBlocks.ROLLER);
    public static final RegistryObject<Item> BEARING = fromBlock(ModBlocks.BEARING);
    public static final RegistryObject<Item> BASIC_MOTOR = fromMotorBlock(ModBlocks.BASIC_MOTOR);
    public static final RegistryObject<Item> STEEL_BLOCK = fromBlock(ModBlocks.STEEL_BLOCK);
    public static final RegistryObject<Item> TITANIUM_BLOCK = fromBlock(ModBlocks.TITANIUM_BLOCK);
    public static final RegistryObject<Item> ALUMINUM_BLOCK = fromBlock(ModBlocks.ALUMINUM_BLOCK);
    public static final RegistryObject<Item> BROOKITE_ORE = fromBlock(ModBlocks.BROOKITE_ORE);
    public static final RegistryObject<Item> DEEPSLATE_BROOKITE_ORE = fromBlock(ModBlocks.DEEPSLATE_BROOKITE_ORE);
    public static final RegistryObject<Item> CRYOLITE_ORE = fromBlock(ModBlocks.CRYOLITE_ORE);
    public static final RegistryObject<Item> DEEPSLATE_CRYOLITE_ORE = fromBlock(ModBlocks.DEEPSLATE_CRYOLITE_ORE);
    public static final RegistryObject<Item> COPPER_COIL = fromCoilBlock(ModBlocks.COPPER_COIL);
    public static final RegistryObject<Item> GOLD_COIL = fromCoilBlock(ModBlocks.GOLD_COIL);

    private static RegistryObject<Item> fromBlock(RegistryObject<Block> block)
    {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), PROPERTIES));
    }

    private static RegistryObject<Item> fromCoilBlock(RegistryObject<Block> block)
    {
        return ITEMS.register(block.getId().getPath(), () -> new CoilBlockItem(block.get(), PROPERTIES));
    }

    private static RegistryObject<Item> fromMotorBlock(RegistryObject<Block> block)
    {
        return ITEMS.register(block.getId().getPath(), () -> new MotorBlockItem(block.get(), PROPERTIES));
    }

    // --- Items -------------------------------------------------------------------------------------------------------

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> RAW_BROOKITE = ITEMS.register("raw_brookite", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> RAW_CRYOLITE = ITEMS.register("raw_cryolite", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> TITANIUM_NUGGET = ITEMS.register("titanium_nugget", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> ALUMINUM_INGOT = ITEMS.register("aluminum_ingot", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> ALUMINUM_NUGGET = ITEMS.register("aluminum_nugget", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> SLAG = ITEMS.register("slag", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> IRON_PLATE = ITEMS.register("iron_plate", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> COPPER_PLATE = ITEMS.register("copper_plate", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> GOLD_PLATE = ITEMS.register("gold_plate", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> STEEL_PLATE = ITEMS.register("steel_plate", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> ALUMINUM_PLATE = ITEMS.register("aluminum_plate", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> TITANIUM_PLATE = ITEMS.register("titanium_plate", () -> new Item(PROPERTIES));
}
