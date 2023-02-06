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
    // --- Blocks ------------------------------------------------------------------------------------------------------

    public static final TagKey<Item> ORES_TITANIUM = ItemTags.create(new ResourceLocation("forge", "ores/titanium"));
    public static final TagKey<Item> ORES_ALUMINUM = ItemTags.create(new ResourceLocation("forge", "ores/aluminum"));
    public static final TagKey<Item> STORAGE_BLOCKS_STEEL = ItemTags.create(new ResourceLocation("forge", "storage_blocks/steel"));
    public static final TagKey<Item> STORAGE_BLOCKS_ALUMINUM = ItemTags.create(new ResourceLocation("forge", "storage_blocks/titanium"));
    public static final TagKey<Item> STORAGE_BLOCKS_TITANIUM = ItemTags.create(new ResourceLocation("forge", "storage_blocks/aluminum"));
    public static final TagKey<Item> COIL = ItemTags.create(Pyrros.loc("coil"));
    public static final TagKey<Item> MOTOR = ItemTags.create(Pyrros.loc("motor"));

    // --- Items -------------------------------------------------------------------------------------------------------

    public static final TagKey<Item> RAW_MATERIALS_TITANIUM = ItemTags.create(new ResourceLocation("forge", "raw_materials/titanium"));
    public static final TagKey<Item> RAW_MATERIALS_ALUMINUM = ItemTags.create(new ResourceLocation("forge", "raw_materials/aluminum"));
    public static final TagKey<Item> INGOTS_STEEL = ItemTags.create(new ResourceLocation("forge", "ingots/steel"));
    public static final TagKey<Item> NUGGETS_STEEL = ItemTags.create(new ResourceLocation("forge", "nuggets/steel"));
    public static final TagKey<Item> INGOTS_TITANIUM = ItemTags.create(new ResourceLocation("forge", "ingots/titanium"));
    public static final TagKey<Item> NUGGETS_TITANIUM = ItemTags.create(new ResourceLocation("forge", "nuggets/titanium"));
    public static final TagKey<Item> INGOTS_ALUMINUM = ItemTags.create(new ResourceLocation("forge", "ingots/aluminum"));
    public static final TagKey<Item> NUGGETS_ALUMINUM = ItemTags.create(new ResourceLocation("forge", "nuggets/aluminum"));
    public static final TagKey<Item> SLAG = ItemTags.create(new ResourceLocation("forge", "slag"));
    public static final TagKey<Item> PLATES = ItemTags.create(new ResourceLocation("forge", "plates"));
    public static final TagKey<Item> PLATES_IRON = ItemTags.create(new ResourceLocation("forge", "plates/iron"));

    public ModItemTagsProvider(DataGenerator pGenerator, BlockTagsProvider pBlockTagsProvider, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(pGenerator, pBlockTagsProvider, Pyrros.MODID, existingFileHelper);
    }

    @Override
    protected void addTags()
    {
        // --- Blocks --------------------------------------------------------------------------------------------------

        tag(Tags.Items.ORES_IN_GROUND_STONE)
                .add(ModItems.BROOKITE_ORE.get())
                .add(ModItems.CRYOLITE_ORE.get());

        tag(Tags.Items.ORES_IN_GROUND_DEEPSLATE)
                .add(ModItems.DEEPSLATE_BROOKITE_ORE.get())
                .add(ModItems.DEEPSLATE_CRYOLITE_ORE.get());

        tag(Tags.Items.ORES)
                .add(ModItems.BROOKITE_ORE.get())
                .add(ModItems.DEEPSLATE_BROOKITE_ORE.get())
                .add(ModItems.CRYOLITE_ORE.get())
                .add(ModItems.DEEPSLATE_CRYOLITE_ORE.get());
        tag(ORES_TITANIUM)
                .add(ModItems.BROOKITE_ORE.get())
                .add(ModItems.DEEPSLATE_BROOKITE_ORE.get());
        tag(ORES_ALUMINUM)
                .add(ModItems.CRYOLITE_ORE.get())
                .add(ModItems.DEEPSLATE_CRYOLITE_ORE.get());

        tag(Tags.Items.STORAGE_BLOCKS)
                .add(ModItems.STEEL_BLOCK.get())
                .add(ModItems.TITANIUM_BLOCK.get())
                .add(ModItems.ALUMINUM_BLOCK.get());
        tag(STORAGE_BLOCKS_STEEL).add(ModItems.STEEL_BLOCK.get());
        tag(STORAGE_BLOCKS_TITANIUM).add(ModItems.TITANIUM_BLOCK.get());
        tag(STORAGE_BLOCKS_ALUMINUM).add(ModItems.ALUMINUM_BLOCK.get());

        tag(COIL)
                .add(ModItems.COPPER_COIL.get())
                .add(ModItems.GOLD_COIL.get());

        tag(MOTOR)
                .add(ModItems.BASIC_MOTOR.get());

        // --- Items ---------------------------------------------------------------------------------------------------

        tag(Tags.Items.RAW_MATERIALS)
                .add(ModItems.RAW_BROOKITE.get())
                .add(ModItems.RAW_CRYOLITE.get());
        tag(RAW_MATERIALS_TITANIUM).add(ModItems.RAW_BROOKITE.get());
        tag(RAW_MATERIALS_ALUMINUM).add(ModItems.RAW_CRYOLITE.get());

        tag(Tags.Items.INGOTS)
                .add(ModItems.STEEL_INGOT.get())
                .add(ModItems.TITANIUM_INGOT.get())
                .add(ModItems.ALUMINUM_INGOT.get());
        tag(INGOTS_STEEL).add(ModItems.STEEL_INGOT.get());
        tag(INGOTS_TITANIUM).add(ModItems.TITANIUM_INGOT.get());
        tag(INGOTS_ALUMINUM).add(ModItems.ALUMINUM_INGOT.get());

        tag(Tags.Items.NUGGETS)
                .add(ModItems.STEEL_NUGGET.get())
                .add(ModItems.TITANIUM_NUGGET.get())
                .add(ModItems.ALUMINUM_NUGGET.get());
        tag(NUGGETS_STEEL).add(ModItems.STEEL_NUGGET.get());
        tag(NUGGETS_TITANIUM).add(ModItems.TITANIUM_NUGGET.get());
        tag(NUGGETS_ALUMINUM).add(ModItems.ALUMINUM_NUGGET.get());

        tag(SLAG).add(ModItems.SLAG.get());

        tag(PLATES)
                .add(ModItems.IRON_PLATE.get());
        tag(PLATES_IRON).add(ModItems.IRON_PLATE.get());
    }
}
