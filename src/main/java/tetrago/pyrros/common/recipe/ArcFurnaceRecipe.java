package tetrago.pyrros.common.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.Nullable;
import tetrago.pyrros.Pyrros;

public class ArcFurnaceRecipe implements Recipe<Container>
{
    public static final RecipeType<ArcFurnaceRecipe> TYPE = Registry.register(Registry.RECIPE_TYPE, Pyrros.loc("arc_furnace"), new RecipeType<ArcFurnaceRecipe>(){});
    public static final Serializer SERIALIZER = new Serializer();

    private final ResourceLocation mId;
    private final Ingredient mIngredient;
    private final ItemStack mResult;

    public ArcFurnaceRecipe(ResourceLocation id, Ingredient ingredient, ItemStack result)
    {
        mId = id;
        mIngredient = ingredient;
        mResult = result;
    }

    @Override
    public boolean matches(Container pContainer, Level pLevel)
    {
        return mIngredient.test(pContainer.getItem(0));
    }

    @Override
    public ItemStack assemble(Container pContainer)
    {
        return mResult;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight)
    {
        return true;
    }

    @Override
    public ItemStack getResultItem()
    {
        return mResult.copy();
    }

    @Override
    public ResourceLocation getId()
    {
        return mId;
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType()
    {
        return TYPE;
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<ArcFurnaceRecipe>
    {
        @Override
        public ArcFurnaceRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe)
        {
            Ingredient ingredient = Ingredient.fromJson(pSerializedRecipe.getAsJsonObject("ingredient"));
            ItemStack result = ShapedRecipe.itemStackFromJson(pSerializedRecipe.getAsJsonObject("result"));

            return new ArcFurnaceRecipe(pRecipeId, ingredient, result);
        }

        @Nullable
        @Override
        public ArcFurnaceRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer)
        {
            Ingredient ingredient = Ingredient.fromNetwork(pBuffer);
            ItemStack result = pBuffer.readItem();

            return new ArcFurnaceRecipe(pRecipeId, ingredient, result);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, ArcFurnaceRecipe pRecipe)
        {
            pRecipe.mIngredient.toNetwork(pBuffer);
            pBuffer.writeItem(pRecipe.mResult);
        }
    }
}
