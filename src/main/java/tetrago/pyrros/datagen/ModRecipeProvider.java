package tetrago.pyrros.datagen;

import com.google.common.collect.ImmutableList;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.common.block.ModBlocks;
import tetrago.pyrros.common.item.ModItems;
import tetrago.pyrros.datagen.recipe.ArcFurnaceRecipeBuilder;
import tetrago.pyrros.datagen.recipe.RollingMillRecipeBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider
{
    public ModRecipeProvider(DataGenerator pGenerator)
    {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> builder)
    {
        ore(builder, ImmutableList.of(ModBlocks.CRYOLITE_ORE.get(), ModBlocks.DEEPSLATE_CRYOLITE_ORE.get(), ModItems.RAW_CRYOLITE.get()), ModItems.ALUMINUM_INGOT.get());

        ShapedRecipeBuilder.shaped(ModBlocks.MACHINE_FRAME.get(), 4)
                .define('X', Tags.Items.INGOTS_IRON)
                .pattern("X X")
                .pattern(" X ")
                .pattern("X X")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Tags.Items.INGOTS_IRON))
                .save(builder);

        ShapelessRecipeBuilder.shapeless(ModBlocks.ENCASED_MACHINE_FRAME.get())
                .requires(ModBlocks.MACHINE_FRAME.get())
                .requires(Blocks.WHITE_CONCRETE_POWDER)
                .unlockedBy(getHasName(ModBlocks.MACHINE_FRAME.get()), has(ModBlocks.MACHINE_FRAME.get()))
                .save(builder);

        ShapedRecipeBuilder.shaped(ModBlocks.REINFORCED_MACHINE_FRAME.get())
                .define('X', ModBlocks.MACHINE_FRAME.get())
                .define('B', Items.BRICK)
                .pattern(" B ")
                .pattern("BXB")
                .pattern(" B ")
                .unlockedBy(getHasName(ModBlocks.MACHINE_FRAME.get()), has(ModBlocks.MACHINE_FRAME.get()))
                .save(builder);

        material(builder, "steel", ModItems.STEEL_NUGGET.get(), ModItems.STEEL_INGOT.get(), ModBlocks.STEEL_BLOCK.get());
        material(builder, "titanium", ModItems.TITANIUM_NUGGET.get(), ModItems.TITANIUM_INGOT.get(), ModBlocks.TITANIUM_BLOCK.get());
        material(builder, "aluminum", ModItems.ALUMINUM_NUGGET.get(), ModItems.ALUMINUM_INGOT.get(), ModBlocks.ALUMINUM_BLOCK.get());

        ArcFurnaceRecipeBuilder.recipe(ModItems.STEEL_INGOT.get())
                .require(Tags.Items.INGOTS_IRON)
                .save(builder);

        ArcFurnaceRecipeBuilder.recipe(ModItems.TITANIUM_INGOT.get())
                .require(ModItemTagsProvider.RAW_MATERIALS_TITANIUM)
                .save(builder);

        RollingMillRecipeBuilder.recipe(ModItems.IRON_PLATE.get())
                .require(Tags.Items.INGOTS_IRON)
                .save(builder);

        RollingMillRecipeBuilder.recipe(ModItems.COPPER_PLATE.get())
                .require(Tags.Items.INGOTS_COPPER)
                .save(builder);

        RollingMillRecipeBuilder.recipe(ModItems.GOLD_PLATE.get())
                .require(Tags.Items.INGOTS_GOLD)
                .save(builder);

        RollingMillRecipeBuilder.recipe(ModItems.STEEL_PLATE.get())
                .require(ModItemTagsProvider.INGOTS_STEEL)
                .save(builder);

        RollingMillRecipeBuilder.recipe(ModItems.ALUMINUM_PLATE.get())
                .require(ModItemTagsProvider.INGOTS_ALUMINUM)
                .save(builder);

        RollingMillRecipeBuilder.recipe(ModItems.TITANIUM_PLATE.get())
                .require(ModItemTagsProvider.INGOTS_TITANIUM)
                .save(builder);
    }

    private void ore(Consumer<FinishedRecipe> builder, ImmutableList<ItemLike> smeltables, Item item)
    {
        smeltables.forEach(i -> {
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(i), item, 0.7f, 200)
                    .unlockedBy(getHasName(i), has(i))
                    .save(builder, Pyrros.loc(i.asItem().getRegistryName().getPath() + "_smelting"));

            SimpleCookingRecipeBuilder.blasting(Ingredient.of(i), item, 0.7f, 100)
                    .unlockedBy(getHasName(i), has(i))
                    .save(builder, Pyrros.loc(i.asItem().getRegistryName().getPath() + "_blasting"));
        });
    }

    private void material(Consumer<FinishedRecipe> builder, String name, ItemLike nugget, ItemLike ingot, ItemLike block)
    {
        ShapelessRecipeBuilder.shapeless(nugget, 9)
                .requires(ingot)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(builder);

        ShapedRecipeBuilder.shaped(ingot)
                .define('X', nugget)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy(getHasName(nugget), has(nugget))
                .save(builder, Pyrros.loc(name + "_ingot_from_nuggets"));

        ShapelessRecipeBuilder.shapeless(ingot, 9)
                .requires(block)
                .unlockedBy(getHasName(block), has(block))
                .save(builder, Pyrros.loc(name + "_ingot_from_block"));

        ShapedRecipeBuilder.shaped(block)
                .define('X', ingot)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(builder);
    }
}
