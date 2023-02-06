package tetrago.pyrros.integration.jei;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.client.screen.RollingMillScreen;
import tetrago.pyrros.common.block.ModBlocks;
import tetrago.pyrros.common.recipe.RollingMillRecipe;

public class RollingMillRecipeCategory extends RecipeCategory<RollingMillRecipe>
{
    public static final ResourceLocation ID = Pyrros.loc("rolling_mill");

    private final IDrawable mArrow;

    public RollingMillRecipeCategory(IGuiHelper helper)
    {
        super(helper, 38, 20, 88, 46);

        mArrow = helper.drawableBuilder(RollingMillScreen.BACKGROUND, 176, 0, 28, 16)
                .buildAnimated(400, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    protected ItemStack makeIcon()
    {
        return new ItemStack(ModBlocks.ROLLING_MILL.get());
    }

    @Override
    protected ResourceLocation getBackgroundTexture()
    {
        return RollingMillScreen.BACKGROUND;
    }

    @Override
    public Component getTitle()
    {
        return new TranslatableComponent(RollingMillScreen.TITLE);
    }

    @Override
    public ResourceLocation getUid()
    {
        return ID;
    }

    @Override
    public Class<? extends RollingMillRecipe> getRecipeClass()
    {
        return RollingMillRecipe.class;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RollingMillRecipe recipe, IFocusGroup focuses)
    {
        builder.addSlot(RecipeIngredientRole.INPUT, 44 - mX, 35 - mY).addIngredients(recipe.getIngredient());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 104 - mX, 35 - mY).addItemStack(recipe.getResultItem());
    }

    @Override
    public void draw(RollingMillRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY)
    {
        super.draw(recipe, recipeSlotsView, stack, mouseX, mouseY);
        mArrow.draw(stack, 68 - mX, 35 - mY);
    }
}
