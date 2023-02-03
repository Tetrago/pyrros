package tetrago.pyrros.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.common.block.FlatDirectionalBlock;
import tetrago.pyrros.common.block.ModBlocks;
import tetrago.pyrros.common.block.MultiblockBlock;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider
{
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper)
    {
        super(gen, Pyrros.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        flatDirectionalBlock(ModBlocks.ARC_FURNACE_CONTROLLER.get(), state -> {
            boolean constructed = state.getValue(MultiblockBlock.CONSTRUCTED);
            return models().orientable(ModBlocks.ARC_FURNACE_CONTROLLER.getId().getPath() + (constructed ? "_constructed" : ""),
                    modLoc("block/arc_furnace_controller_side"),
                    modLoc("block/arc_furnace_controller_front" + (constructed ? "_constructed" : "")),
                    modLoc("block/arc_furnace_controller_top"));
        });

        simpleBlock(ModBlocks.ENERGY_PORT.get());
        simpleBlock(ModBlocks.ITEM_PORT.get());
        simpleBlock(ModBlocks.MACHINE_FRAME.get());
        simpleBlock(ModBlocks.ENCASED_MACHINE_FRAME.get());
        simpleBlock(ModBlocks.REINFORCED_MACHINE_FRAME.get());
        simpleBlock(ModBlocks.STEEL_BLOCK.get());
    }

    private void flatDirectionalBlock(Block block, Function<BlockState, ModelFile> function)
    {
        getVariantBuilder(block).forAllStates(state ->
                ConfiguredModel.builder().modelFile(function.apply(state))
                        .rotationY((int)(state.getValue(FlatDirectionalBlock.DIRECTION).toYRot() + 180) % 360)
                        .build());
    }
}
