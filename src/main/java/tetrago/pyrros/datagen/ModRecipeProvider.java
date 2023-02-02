package tetrago.pyrros.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import tetrago.pyrros.common.block.ModBlocks;

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
    }
}
