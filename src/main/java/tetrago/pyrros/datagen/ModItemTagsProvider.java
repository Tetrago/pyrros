package tetrago.pyrros.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.common.item.ModItems;

public class ModItemTagsProvider extends ItemTagsProvider
{
    public static final TagKey<Item> INGOTS_STEEL = ItemTags.create(new ResourceLocation("forge", "ingots/steel"));
    public static final TagKey<Item> NUGGETS_STEEL = ItemTags.create(new ResourceLocation("forge", "nuggets/steel"));
    public static final TagKey<Item> STORAGE_BLOCKS_STEEL = ItemTags.create(new ResourceLocation("forge", "storage_blocks/steel"));

    public ModItemTagsProvider(DataGenerator pGenerator, BlockTagsProvider pBlockTagsProvider, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(pGenerator, pBlockTagsProvider, Pyrros.MODID, existingFileHelper);
    }

    @Override
    protected void addTags()
    {
        tag(Tags.Items.INGOTS)
                .add(ModItems.STEEL_INGOT.get());
        tag(INGOTS_STEEL).add(ModItems.STEEL_INGOT.get());

        tag(Tags.Items.NUGGETS)
                .add(ModItems.STEEL_NUGGET.get());
        tag(NUGGETS_STEEL).add(ModItems.STEEL_NUGGET.get());

        tag(Tags.Items.STORAGE_BLOCKS)
                .add(ModItems.STEEL_BLOCK.get());
        tag(STORAGE_BLOCKS_STEEL).add(ModItems.STEEL_BLOCK.get());
    }
}
