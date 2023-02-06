package tetrago.pyrros.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.client.screen.ArcFurnaceScreen;
import tetrago.pyrros.client.screen.RollingMillScreen;
import tetrago.pyrros.common.block.ModBlocks;
import tetrago.pyrros.common.recipe.ArcFurnaceRecipe;
import tetrago.pyrros.common.recipe.RollingMillRecipe;

@JeiPlugin
public class ModJeiPlugin implements IModPlugin
{
    private static final RecipeType<ArcFurnaceRecipe> ARC_FURNACE = new RecipeType<>(ArcFurnaceRecipeCategory.ID, ArcFurnaceRecipe.class);
    private static final RecipeType<RollingMillRecipe> ROLLING_MILL = new RecipeType<>(RollingMillRecipeCategory.ID, RollingMillRecipe.class);

    @Override
    public ResourceLocation getPluginUid()
    {
        return Pyrros.loc("jei");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration)
    {
        registration.addRecipeCategories(new ArcFurnaceRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new RollingMillRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration)
    {
        final RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();

        registration.addRecipes(ARC_FURNACE, manager.getAllRecipesFor(ArcFurnaceRecipe.TYPE));
        registration.addRecipes(ROLLING_MILL, manager.getAllRecipesFor(RollingMillRecipe.TYPE));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
    {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ARC_FURNACE.get()), ARC_FURNACE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ROLLING_MILL.get()), ROLLING_MILL);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration)
    {
        registration.addRecipeClickArea(ArcFurnaceScreen.class, 66, 31, 26, 23, ARC_FURNACE);
        registration.addRecipeClickArea(RollingMillScreen.class, 64, 31, 36, 23, ROLLING_MILL);
    }
}
