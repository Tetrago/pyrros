package tetrago.pyrros.integration.jei;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.client.screen.ArcFurnaceScreen;
import tetrago.pyrros.common.block.ModBlocks;
import tetrago.pyrros.common.blockentity.ArcFurnaceBlockEntity;
import tetrago.pyrros.common.item.ModItems;
import tetrago.pyrros.common.recipe.ArcFurnaceRecipe;

public class ArcFurnaceRecipeCategory extends RecipeCategory<ArcFurnaceRecipe>
{
    public static final ResourceLocation ID = Pyrros.loc("arc_furnace");

    private final IDrawable mArrow;

    public ArcFurnaceRecipeCategory(IGuiHelper helper)
    {
        super(helper, 38, 20, 115, 46);

        mArrow = helper.drawableBuilder(ArcFurnaceScreen.BACKGROUND, 176, 0, 22, 16)
                .buildAnimated(400, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public Component getTitle()
    {
        return new TranslatableComponent(ArcFurnaceScreen.TITLE);
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
        builder.addSlot(RecipeIngredientRole.INPUT, 44 - mX, 35 - mY).addIngredients(recipe.getIngredient());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 104 - mX, 35 - mY).addItemStack(recipe.getResultItem());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 131 - mX, 35 - mY).addItemStack(new ItemStack(ModItems.SLAG.get()));
    }

    @Override
    public void draw(ArcFurnaceRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY)
    {
        super.draw(recipe, recipeSlotsView, stack, mouseX, mouseY);
        mArrow.draw(stack, 68 - mX, 34 - mY);

        Minecraft.getInstance().font.draw(stack, String.format("%d%%", (int)(ArcFurnaceBlockEntity.SLAG_CHANCE * 100)), 131 - mX, 54 - mY, 0xff777777);
    }

    @Override
    protected ItemStack makeIcon()
    {
        return new ItemStack(ModBlocks.ARC_FURNACE.get());
    }

    @Override
    protected ResourceLocation getBackgroundTexture()
    {
        return ArcFurnaceScreen.BACKGROUND;
    }
}
