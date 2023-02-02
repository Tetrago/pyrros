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

    public static final RegistryObject<BlockEntityType<MultiblockChildBlockEntity>> MULTIBLOCK_CHILD = BLOCK_ENTITIES.register("multiblock_child", () -> BlockEntityType.Builder.of(MultiblockChildBlockEntity::new, ModBlocks.MACHINE_FRAME.get()).build(null));
}
