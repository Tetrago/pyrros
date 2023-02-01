package tetrago.pyrros.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import tetrago.pyrros.Pyrros;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Pyrros.MODID);

    public static final RegistryObject<Block> MACHINE_FRAME = BLOCKS.register("machine_frame", MachineFrameBlock::new);
}
