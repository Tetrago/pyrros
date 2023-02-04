package tetrago.pyrros.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import tetrago.pyrros.Pyrros;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Pyrros.MODID);

    public static final RegistryObject<Block> ARC_FURNACE = BLOCKS.register("arc_furnace", ArcFurnaceBlock::new);
    public static final RegistryObject<Block> ENERGY_PORT = BLOCKS.register("energy_port", EnergyPortBlock::new);
    public static final RegistryObject<Block> ITEM_PORT = BLOCKS.register("item_port", ItemPortBlock::new);
    public static final RegistryObject<Block> MACHINE_FRAME = BLOCKS.register("machine_frame", MachineFrameBlock::new);
    public static final RegistryObject<Block> ENCASED_MACHINE_FRAME = BLOCKS.register("encased_machine_frame", () -> new MultiblockComponentBlock(BlockBehaviour.Properties.of(Material.STONE).strength(1.5f, 6)));
    public static final RegistryObject<Block> REINFORCED_MACHINE_FRAME = BLOCKS.register("reinforced_machine_frame", () -> new MultiblockComponentBlock(BlockBehaviour.Properties.copy(ENCASED_MACHINE_FRAME.get())));
    public static final RegistryObject<Block> STEEL_BLOCK = BLOCKS.register("steel_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> TITANIUM_BLOCK = BLOCKS.register("titanium_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> BROOKITE_ORE = BLOCKS.register("brookite_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
    public static final RegistryObject<Block> DEEPSLATE_BROOKITE_ORE = BLOCKS.register("deepslate_brookite_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));

}
