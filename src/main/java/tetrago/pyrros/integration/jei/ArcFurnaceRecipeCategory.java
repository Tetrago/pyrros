package tetrago.pyrros.integration.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.client.screen.ArcFurnaceScreen;
import tetrago.pyrros.common.block.ModBlocks;
import tetrago.pyrros.common.recipe.ArcFurnaceRecipe;

public class ArcFurnaceRecipeCategory implements IRecipeCategory<ArcFurnaceRecipe>
{
    public static final ResourceLocation ID = Pyrros.loc("arc_furnace");

    private final IDrawable mBackground;
    private final IDrawable mIcon;

    public ArcFurnaceRecipeCategory(IGuiHelper helper)
    {
        mBackground = helper.createDrawable(ArcFurnaceScreen.BACKGROUND, 4, 4, 167, 77);
        mIcon = helper.createDrawableIngredient(new ItemStack(ModBlocks.ARC_FURNACE.get()));
    }

    @Override
    public Component getTitle()
    {
        return new TranslatableComponent(ArcFurnaceScreen.TITLE);
    }

    @Override
    public IDrawable getBackground()
    {
        return mBackground;
    }

    @Override
    public IDrawable getIcon()
    {
        return mIcon;
    }

    @Override
    public ResourceLocation getUid()
    {
        return ID;
    }

    @Override
    public Class<? extends ArcFurnaceRecipe> getRecipeClass()
    {
        return ArcFurnaceRecipe.class;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ArcFurnaceRecipe recipe, IFocusGroup focuses)
    {
        builder.addSlot(RecipeIngredientRole.INPUT, 40, 31).addIngredients(recipe.getIngredient());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 100, 31).addItemStack(recipe.getResultItem());
    }
}
