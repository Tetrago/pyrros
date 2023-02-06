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

public class RollingMillRecipe implements Recipe<Container>
{
    public static final RecipeType<RollingMillRecipe> TYPE = Registry.register(Registry.RECIPE_TYPE, Pyrros.loc("rolling_mill"), new RecipeType<RollingMillRecipe>(){});
    public static final Serializer SERIALIZER = new Serializer();

    private final ResourceLocation mId;
    private final Ingredient mIngredient;
    private final ItemStack mResult;

    public RollingMillRecipe(ResourceLocation id, Ingredient ingredient, ItemStack result)
    {
        mId = id;
        mIngredient = ingredient;
        mResult = result;
    }

    public Ingredient getIngredient()
    {
        return mIngredient;
    }

    @Override
    public boolean matches(Container pContainer, Level pLevel)
    {
        return false;
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

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<RollingMillRecipe>
    {
        @Override
        public RollingMillRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe)
        {
            Ingredient ingredient = Ingredient.fromJson(pSerializedRecipe.getAsJsonObject("ingredient"));
            ItemStack result = ShapedRecipe.itemStackFromJson(pSerializedRecipe.getAsJsonObject("result"));

            return new RollingMillRecipe(pRecipeId, ingredient, result);
        }

        @Nullable
        @Override
        public RollingMillRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer)
        {
            Ingredient ingredient = Ingredient.fromNetwork(pBuffer);
            ItemStack result = pBuffer.readItem();

            return new RollingMillRecipe(pRecipeId, ingredient, result);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, RollingMillRecipe pRecipe)
        {
            pRecipe.mIngredient.toNetwork(pBuffer);
            pBuffer.writeItem(pRecipe.mResult);
        }
    }
}
