package tetrago.pyrros.integration.jei;

import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public abstract class RecipeCategory<T> implements IRecipeCategory<T>
{
    protected final int mX;
    protected final int mY;

    private final IDrawable mIcon;
    private final IDrawable mBackground;

    protected RecipeCategory(IGuiHelper helper, int x, int y, int width, int height)
    {
        mX = x;
        mY = y;

        mIcon = helper.createDrawableIngredient(makeIcon());
        mBackground = helper.createDrawable(getBackgroundTexture(), x, y, width, height);
    }

    @Override
    public IDrawable getIcon()
    {
        return mIcon;
    }

    @Override
    public IDrawable getBackground()
    {
        return mBackground;
    }

    protected abstract ItemStack makeIcon();
    protected abstract ResourceLocation getBackgroundTexture();
}
