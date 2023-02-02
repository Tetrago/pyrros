package tetrago.pyrros.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import tetrago.pyrros.Pyrros;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Pyrros.MODID);

    public static final RegistryObject<Block> MACHINE_FRAME = BLOCKS.register("machine_frame", MachineFrameBlock::new);
    public static final RegistryObject<Block> ENCASED_MACHINE_FRAME = BLOCKS.register("encased_machine_frame", () -> new MultiblockChildBlock(BlockBehaviour.Properties.of(Material.STONE).strength(1.5f, 6)));
    public static final RegistryObject<Block> REINFORCED_MACHINE_FRAME = BLOCKS.register("reinforced_machine_frame", () -> new MultiblockChildBlock(BlockBehaviour.Properties.copy(ENCASED_MACHINE_FRAME.get())));
}