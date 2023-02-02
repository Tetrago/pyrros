package tetrago.pyrros.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.common.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider
{
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper)
    {
        super(gen, Pyrros.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        simpleBlock(ModBlocks.MACHINE_FRAME.get());
        simpleBlock(ModBlocks.ENCASED_MACHINE_FRAME.get());
        simpleBlock(ModBlocks.REINFORCED_MACHINE_FRAME.get());
    }
}
