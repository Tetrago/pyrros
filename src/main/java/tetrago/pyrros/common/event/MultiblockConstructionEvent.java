package tetrago.pyrros.common.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Rotation;
import net.minecraftforge.eventbus.api.Event;
import tetrago.pyrros.common.recipe.MultiblockRecipe;

import java.util.List;

public class MultiblockConstructionEvent extends Event
{
    private final Level mLevel;
    private final BlockPos mBlockPos;
    private final MultiblockRecipe mRecipe;
    private final Rotation mRotation;

    public MultiblockConstructionEvent(Level level, BlockPos pos, MultiblockRecipe recipe, Rotation rotation)
    {
        mLevel = level;
        mBlockPos = pos;
        mRecipe = recipe;
        mRotation = rotation;
    }

    public List<BlockPos> getBlockPositions()
    {
        return mRecipe.getBlocksForRotation(mLevel, mBlockPos, mRotation)
                .orElseThrow(() -> new IllegalStateException("Invalid multiblock construction event"));
    }

    @Override
    public boolean isCancelable()
    {
        return true;
    }

    public Level getLevel()
    {
        return mLevel;
    }

    public BlockPos getMultiblockPos()
    {
        return mBlockPos;
    }

    public MultiblockRecipe getRecipe()
    {
        return mRecipe;
    }

    public Rotation getRotation()
    {
        return mRotation;
    }
}
