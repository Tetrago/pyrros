package tetrago.pyrros.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.common.block.ModBlocks;

public class ModBlockTagsProvider extends BlockTagsProvider
{
    public static final TagKey<Block> ORES_TITANIUM = BlockTags.create(new ResourceLocation("forge", "ores/titanium"));
    public static final TagKey<Block> ORES_ALUMINUM = BlockTags.create(new ResourceLocation("forge", "ores/aluminum"));

    public static final TagKey<Block> STORAGE_BLOCKS_STEEL = BlockTags.create(new ResourceLocation("forge", "storage_blocks/steel"));
    public static final TagKey<Block> STORAGE_BLOCKS_TITANIUM = BlockTags.create(new ResourceLocation("forge", "storage_blocks/titanium"));
    public static final TagKey<Block> STORAGE_BLOCKS_ALUMINUM = BlockTags.create(new ResourceLocation("forge", "storage_blocks/aluminum"));
    public static final TagKey<Block> COIL = BlockTags.create(Pyrros.loc("coil"));

    public ModBlockTagsProvider(DataGenerator pGenerator, ExistingFileHelper helper)
    {
        super(pGenerator, Pyrros.MODID, helper);
    }

    @Override
    protected void addTags()
    {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.BROOKITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_BROOKITE_ORE.get())
                .add(ModBlocks.CRYOLITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_CRYOLITE_ORE.get());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.BROOKITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_BROOKITE_ORE.get())
                .add(ModBlocks.CRYOLITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_CRYOLITE_ORE.get());

        tag(Tags.Blocks.ORES_IN_GROUND_STONE)
                .add(ModBlocks.BROOKITE_ORE.get())
                .add(ModBlocks.CRYOLITE_ORE.get());

        tag(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE)
                .add(ModBlocks.DEEPSLATE_BROOKITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_CRYOLITE_ORE.get());

        tag(Tags.Blocks.ORES)
                .add(ModBlocks.BROOKITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_BROOKITE_ORE.get())
                .add(ModBlocks.CRYOLITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_CRYOLITE_ORE.get());
        tag(ORES_TITANIUM)
                .add(ModBlocks.BROOKITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_BROOKITE_ORE.get());
        tag(ORES_ALUMINUM)
                .add(ModBlocks.CRYOLITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_CRYOLITE_ORE.get());

        tag(Tags.Blocks.STORAGE_BLOCKS)
                .add(ModBlocks.STEEL_BLOCK.get())
                .add(ModBlocks.TITANIUM_BLOCK.get())
                .add(ModBlocks.ALUMINUM_BLOCK.get());
        tag(STORAGE_BLOCKS_STEEL).add(ModBlocks.STEEL_BLOCK.get());
        tag(STORAGE_BLOCKS_TITANIUM).add(ModBlocks.TITANIUM_BLOCK.get());
        tag(STORAGE_BLOCKS_ALUMINUM).add(ModBlocks.ALUMINUM_BLOCK.get());

        tag(COIL)
                .add(ModBlocks.COPPER_COIL.get())
                .add(ModBlocks.GOLD_COIL.get());
    }
}
