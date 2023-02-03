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

    public static final RegistryObject<BlockEntityType<ArcFurnaceControllerBlockEntity>> ARC_FURNACE_CONTROLLER = BLOCK_ENTITIES.register("arc_furnace_controller", () -> BlockEntityType.Builder.of(ArcFurnaceControllerBlockEntity::new, ModBlocks.ARC_FURNACE_CONTROLLER.get()).build(null));
    public static final RegistryObject<BlockEntityType<EnergyPortBlockEntity>> ENERGY_PORT = BLOCK_ENTITIES.register("energy_port", () -> BlockEntityType.Builder.of(EnergyPortBlockEntity::new, ModBlocks.ENERGY_PORT.get()).build(null));
    public static final RegistryObject<BlockEntityType<ItemPortBlockEntity>> ITEM_PORT = BLOCK_ENTITIES.register("item_port", () -> BlockEntityType.Builder.of(ItemPortBlockEntity::new, ModBlocks.ITEM_PORT.get()).build(null));
    public static final RegistryObject<BlockEntityType<MultiblockComponentBlockEntity>> MULTIBLOCK_COMPONENT = BLOCK_ENTITIES.register("multiblock_component", () -> BlockEntityType.Builder.of(MultiblockComponentBlockEntity::new, ModBlocks.MACHINE_FRAME.get(), ModBlocks.ENCASED_MACHINE_FRAME.get(), ModBlocks.REINFORCED_MACHINE_FRAME.get()).build(null));
}
