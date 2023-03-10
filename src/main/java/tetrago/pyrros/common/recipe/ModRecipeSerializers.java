package tetrago.pyrros.common.recipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import tetrago.pyrros.Pyrros;

public class ModRecipeSerializers
{
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Pyrros.MODID);

    public static final RegistryObject<RecipeSerializer<ArcFurnaceRecipe>> ARC_FURNACE = RECIPE_SERIALIZERS.register("arc_furnace", () -> ArcFurnaceRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<RollingMillRecipe>> ROLLING_MILL = RECIPE_SERIALIZERS.register("rolling_mill", () -> RollingMillRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<MultiblockRecipe>> MULTIBLOCK = RECIPE_SERIALIZERS.register("multiblock", () -> MultiblockRecipe.SERIALIZER);
}
