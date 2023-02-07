package tetrago.pyrros.datagen.recipe;

import com.google.gson.JsonObject;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;
import tetrago.pyrros.common.recipe.ArcFurnaceRecipe;

import java.util.function.Consumer;

public class ArcFurnaceRecipeBuilder
{
    private final ItemStack mResult;
    private Ingredient mIngredient = null;

    private ArcFurnaceRecipeBuilder(ItemStack result)
    {
        mResult = result;
    }

    public static ArcFurnaceRecipeBuilder recipe(ItemLike item)
    {
        return recipe(item, 1);
    }

    public static ArcFurnaceRecipeBuilder recipe(ItemLike item, int count)
    {
        return new ArcFurnaceRecipeBuilder(new ItemStack(item.asItem(), count));
    }

    public ArcFurnaceRecipeBuilder require(TagKey<Item> tag)
    {
        return require(Ingredient.of(tag));
    }

    public ArcFurnaceRecipeBuilder require(ItemLike item)
    {
        return require(Ingredient.of(item.asItem()));
    }

    public ArcFurnaceRecipeBuilder require(Ingredient ingredient)
    {
        mIngredient = ingredient;
        return this;
    }

    public void save(Consumer<FinishedRecipe> builder)
    {
        save(builder, mResult.getItem().getRegistryName());
    }

    public void save(Consumer<FinishedRecipe> builder, ResourceLocation location)
    {
        builder.accept(new FinishedRecipe()
        {
            @Override
            public void serializeRecipeData(JsonObject pJson)
            {
                pJson.add("ingredient", mIngredient.toJson());
                pJson.add("result", new JsonObject());

                pJson.getAsJsonObject("result").addProperty("item", mResult.getItem().getRegistryName().toString());

                if(mResult.getCount() > 1)
                {
                    pJson.getAsJsonObject("result").addProperty("count", mResult.getCount());
                }
            }

            @Override
            public ResourceLocation getId()
            {
                return location;
            }

            @Override
            public RecipeSerializer<?> getType()
            {
                return ArcFurnaceRecipe.SERIALIZER;
            }

            @Nullable
            @Override
            public JsonObject serializeAdvancement()
            {
                return null;
            }

            @Nullable
            @Override
            public ResourceLocation getAdvancementId()
            {
                return null;
            }
        });
    }
}
