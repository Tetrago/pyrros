package tetrago.pyrros.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.common.block.ModBlocks;
import tetrago.pyrros.common.block.MultiblockBlock;

public class ModBlockStateProvider extends BlockStateProvider
{
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper)
    {
        super(gen, Pyrros.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        getVariantBuilder(ModBlocks.ARC_FURNACE_CONTROLLER.get())
                .partialState().with(MultiblockBlock.CONSTRUCTED, true).modelForState().modelFile(models().cubeTop(ModBlocks.ARC_FURNACE_CONTROLLER.getId().getPath() + "_constructed", modLoc("block/arc_furnace_controller_side_constructed"), modLoc("block/arc_furnace_controller_top"))).addModel()
                .partialState().with(MultiblockBlock.CONSTRUCTED, false).modelForState().modelFile(models().cubeTop(ModBlocks.ARC_FURNACE_CONTROLLER.getId().getPath(), modLoc("block/arc_furnace_controller_side"), modLoc("block/arc_furnace_controller_top"))).addModel();

        simpleBlock(ModBlocks.ENERGY_PORT.get());
        simpleBlock(ModBlocks.MACHINE_FRAME.get());
        simpleBlock(ModBlocks.ENCASED_MACHINE_FRAME.get());
        simpleBlock(ModBlocks.REINFORCED_MACHINE_FRAME.get());
        simpleBlock(ModBlocks.STEEL_BLOCK.get());
    }
}
