package tetrago.pyrros.common.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.Nullable;
import tetrago.pyrros.Pyrros;

import java.util.*;

public class MultiblockRecipe implements Recipe<Container>
{
    public static final RecipeType<MultiblockRecipe> TYPE = Registry.register(Registry.RECIPE_TYPE, Pyrros.loc("multiblock"), new RecipeType<MultiblockRecipe>(){});
    public static final Serializer SERIALIZER = new Serializer();

    private final ResourceLocation mId;
    private final Map<Character, Block[]> mKeys;
    private final char[][][] mBlocks;
    private final BlockPos mAnchor;

    public MultiblockRecipe(ResourceLocation id, Map<Character, Block[]> keys, char[][][] blocks, BlockPos anchor)
    {
        mId = id;
        mKeys = keys;
        mBlocks = blocks;
        mAnchor = anchor;
    }

    public boolean matches(Level level, BlockPos pos)
    {
        return findValidRotation(level, pos).isPresent();
    }

    public Optional<Rotation> findValidRotation(Level level, BlockPos pos)
    {
        for(Rotation rotation : Rotation.values())
        {
            Optional<List<BlockPos>> result = getBlocksForRotation(level, pos, rotation);

            if(result.isEmpty()) continue;
            return Optional.of(rotation);
        }

        return Optional.empty();
    }

    public Optional<List<BlockPos>> getBlocksForRotation(Level level, BlockPos pos, Rotation rotation)
    {
        List<BlockPos> positions = new ArrayList<>();
        BlockPos absolute = pos.offset(mAnchor.rotate(rotation).multiply(-1));

        for(int x = 0; x < mBlocks.length; ++x)
        {
            for(int y = 0; y < mBlocks[0].length; ++y)
            {
                for(int z = 0; z < mBlocks[0][0].length; ++z)
                {
                    char c = mBlocks[x][y][z];
                    if(c == ' ') continue;

                    BlockPos blockPos = absolute.offset(new BlockPos(x, y, z).rotate(rotation));

                    if(!mKeys.containsKey(c))
                    {
                        throw new IllegalStateException("Multiblock missing key: " + c);
                    }

                    Block[] blocks = mKeys.get(c);
                    BlockState state = level.getBlockState(blockPos);
                    if(Arrays.stream(blocks).noneMatch(state::is)) return Optional.empty();

                    positions.add(blockPos);
                }
            }
        }

        return Optional.of(positions);
    }

    public static Optional<MultiblockRecipe> getRecipeFor(Level level, BlockPos pos)
    {
        return level.getRecipeManager().getAllRecipesFor(TYPE).stream().filter(r -> r.matches(level, pos)).findFirst();
    }

    @Override
    public boolean matches(Container pContainer, Level pLevel)
    {
        return false;
    }

    @Override
    public ItemStack assemble(Container pContainer)
    {
        return null;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight)
    {
        return false;
    }

    @Override
    public ItemStack getResultItem()
    {
        return null;
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

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<MultiblockRecipe>
    {
        @Override
        public MultiblockRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe)
        {
            Map<Character, Block[]> keys = new HashMap<>();

            JsonObject table = pSerializedRecipe.getAsJsonObject("keys");
            for(String key : table.keySet())
            {
                JsonArray arr = table.get(key).getAsJsonArray();
                Block[] blocks = new Block[arr.size()];

                for(int i = 0; i < arr.size(); ++i)
                {
                    ResourceLocation location = ResourceLocation.tryParse(arr.get(i).getAsString());

                    Optional<Holder<Block>> block = ForgeRegistries.BLOCKS.getHolder(location);
                    if(block.isEmpty())
                    {
                        throw new IllegalStateException("Invalid multiblock block location: " + location.toString());
                    }

                    blocks[i] = block.get().value();
                }

                keys.put(key.charAt(0), blocks);
            }

            JsonArray layers = pSerializedRecipe.getAsJsonArray("layers");

            int height = layers.size();
            int depth = layers.get(0).getAsJsonArray().size();
            int width = layers.get(0).getAsJsonArray().get(0).getAsString().length();

            char[][][] blocks = new char[width][height][depth];

            for(int y = 0; y < height; ++y)
            {
                JsonArray layer = layers.get(y).getAsJsonArray();

                for(int z = 0, i = layer.size() - 1; z < depth; ++z, --i)
                {
                    String string = layer.get(i).getAsString();

                    for(int x = 0; x < width; ++x)
                    {
                        blocks[x][y][z] = string.charAt(width - x - 1);
                    }
                }
            }

            JsonObject anchor = pSerializedRecipe.getAsJsonObject("anchor");
            BlockPos pos = new BlockPos(width - anchor.get("x").getAsInt() - 1, anchor.get("y").getAsInt(), anchor.get("z").getAsInt());

            return new MultiblockRecipe(pRecipeId, keys, blocks, pos);
        }

        @Nullable
        @Override
        public MultiblockRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer)
        {
            Map<Character, Block[]> keys = pBuffer.readMap(b -> b.readChar(), b -> {
                Block[] blocks = new Block[b.readInt()];

                for(int i = 0; i < blocks.length; ++i)
                {
                    blocks[i] = ForgeRegistries.BLOCKS.getHolder(b.readResourceLocation()).get().value();
                }

                return blocks;
            });

            int width = pBuffer.readInt();
            int height = pBuffer.readInt();
            int depth = pBuffer.readInt();

            char[][][] blocks = new char[width][height][depth];

            for(int x = 0; x < width; ++x)
            {
                for(int y = 0; y < height; ++y)
                {
                    for(int z = 0; z < depth; ++z)
                    {
                        blocks[x][y][z] = pBuffer.readChar();
                    }
                }
            }

            return new MultiblockRecipe(pRecipeId, keys, blocks, pBuffer.readBlockPos());
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, MultiblockRecipe pRecipe)
        {
            pBuffer.writeMap(pRecipe.mKeys, (b, c) -> b.writeChar(c), (b, blocks) -> {
                b.writeInt(blocks.length);

                for(int i = 0; i < blocks.length; ++i)
                {
                    b.writeResourceLocation(blocks[i].getRegistryName());
                }
            });

            pBuffer.writeInt(pRecipe.mBlocks.length);
            pBuffer.writeInt(pRecipe.mBlocks[0].length);
            pBuffer.writeInt(pRecipe.mBlocks[0][0].length);

            for(int x = 0; x < pRecipe.mBlocks.length; ++x)
            {
                for(int y = 0; y < pRecipe.mBlocks[0].length; ++y)
                {
                    for(int z = 0; z < pRecipe.mBlocks[0][0].length; ++z)
                    {
                        pBuffer.writeChar(pRecipe.mBlocks[x][y][z]);
                    }
                }
            }

            pBuffer.writeBlockPos(pRecipe.mAnchor);
        }
    }
}