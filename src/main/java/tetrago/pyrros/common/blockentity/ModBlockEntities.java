package tetrago.pyrros.common.blockentity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.common.block.ModBlocks;

public class ModBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Pyrros.MODID);

    public static final RegistryObject<BlockEntityType<MultiblockComponentBlockEntity>> MULTIBLOCK_COMPONENT = BLOCK_ENTITIES.register("multiblock_component", () -> BlockEntityType.Builder.of(MultiblockComponentBlockEntity::new, ModBlocks.MACHINE_FRAME.get(), ModBlocks.ENCASED_MACHINE_FRAME.get(), ModBlocks.REINFORCED_MACHINE_FRAME.get()).build(null));
}
