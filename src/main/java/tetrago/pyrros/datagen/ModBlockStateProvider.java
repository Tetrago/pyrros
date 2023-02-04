package tetrago.pyrros.datagen;

import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
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
import tetrago.pyrros.common.block.OmnidirectionalBlock;

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
        flatDirectionalBlock(ModBlocks.ARC_FURNACE.get(), state -> {
            boolean constructed = state.getValue(MultiblockBlock.CONSTRUCTED);
            return models().orientable(ModBlocks.ARC_FURNACE.getId().getPath() + (constructed ? "_constructed" : ""),
                    modLoc("block/arc_furnace_side"),
                    modLoc("block/arc_furnace_front" + (constructed ? "_constructed" : "")),
                    modLoc("block/arc_furnace_top"));
        });

        simpleBlock(ModBlocks.ENERGY_PORT.get());
        simpleBlock(ModBlocks.ITEM_PORT.get());
        simpleBlock(ModBlocks.MACHINE_FRAME.get());
        simpleBlock(ModBlocks.ENCASED_MACHINE_FRAME.get());
        simpleBlock(ModBlocks.REINFORCED_MACHINE_FRAME.get());
        omnidirectionalBlock(ModBlocks.ROLLER.get(), models().getExistingFile(Pyrros.loc("block/roller")));
        motorBlock(ModBlocks.BASIC_MOTOR.get(), modLoc("block/basic_motor"));
        simpleBlock(ModBlocks.STEEL_BLOCK.get());
        simpleBlock(ModBlocks.TITANIUM_BLOCK.get());
        simpleBlock(ModBlocks.ALUMINUM_BLOCK.get());
        simpleBlock(ModBlocks.BROOKITE_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_BROOKITE_ORE.get());
        simpleBlock(ModBlocks.CRYOLITE_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_CRYOLITE_ORE.get());
        coilBlock(ModBlocks.COPPER_COIL.get());
        coilBlock(ModBlocks.GOLD_COIL.get());
    }

    private void flatDirectionalBlock(Block block, Function<BlockState, ModelFile> function)
    {
        getVariantBuilder(block).forAllStates(state ->
                ConfiguredModel.builder().modelFile(function.apply(state))
                        .rotationY((int)(state.getValue(FlatDirectionalBlock.DIRECTION).toYRot() + 180) % 360)
                        .build());
    }

    private void coilBlock(Block block)
    {
        omnidirectionalBlock(block, state -> {
            String path = block.getRegistryName().getPath();
            return models().cubeTop(path, modLoc("block/" + path + "_side"), modLoc("block/" + path + "_top"));
        });
    }

    private void motorBlock(Block block, ResourceLocation texture)
    {
        omnidirectionalBlock(block, state -> models().withExistingParent(block.getRegistryName().getPath(), modLoc("block/motor")).texture("texture", texture));
    }

    private void omnidirectionalBlock(Block block, ModelFile model)
    {
        omnidirectionalBlock(block, state -> model);
    }

    private void omnidirectionalBlock(Block block, Function<BlockState, ModelFile> function)
    {
        getVariantBuilder(block).forAllStates(state -> {
            Direction dir = state.getValue(OmnidirectionalBlock.DIRECTION);
            return ConfiguredModel.builder().modelFile(function.apply(state))
                    .rotationX((int)(dir.getRotation().toXYZDegrees().x() + 360) % 360)
                    .rotationY((int)(dir.toYRot() + 180) % 360)
                    .build();
        });
    }
}
