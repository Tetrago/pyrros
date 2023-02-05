package tetrago.pyrros.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.MinecraftForge;
import tetrago.pyrros.common.blockentity.MultiblockBlockEntity;
import tetrago.pyrros.common.blockentity.MultiblockComponentBlockEntity;
import tetrago.pyrros.common.event.MultiblockConstructionEvent;
import tetrago.pyrros.common.recipe.MultiblockRecipe;

import java.util.Optional;

public abstract class MultiblockBlock extends Block implements EntityBlock
{
    public static final Property<Boolean> CONSTRUCTED = BooleanProperty.create("constructed");

    public MultiblockBlock(Properties pProperties)
    {
        super(pProperties);

        registerDefaultState(getStateDefinition().any().setValue(CONSTRUCTED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder)
    {
        super.createBlockStateDefinition(pBuilder);

        pBuilder.add(CONSTRUCTED);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit)
    {
        if(pState.getValue(CONSTRUCTED)) return useConstructed(pState, pLevel, pPos, pPlayer, pHand, pHit);

        Optional<MultiblockRecipe> recipe = MultiblockRecipe.getRecipeFor(pLevel, pPos);
        if(recipe.isEmpty()) return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);

        if(!pLevel.isClientSide())
        {
            MultiblockConstructionEvent event = new MultiblockConstructionEvent(pLevel, pPos, recipe.get(),
                    recipe.get().findValidRotation(pLevel, pPos).orElseThrow(() -> new IllegalStateException("Invalid multiblock construction attempt")));

            MinecraftForge.EVENT_BUS.post(event);
            if(!event.isCanceled())
            {
                onConstruct(pLevel, pPos, recipe.get(), event.getRotation());
            }
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    protected InteractionResult useConstructed(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit)
    {
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    public void deconstruct(Level level, BlockPos pos)
    {
        if(level.isClientSide()) return;
        onDeconstruct(level, pos);
    }

    protected void onConstruct(Level level, BlockPos pos, MultiblockRecipe recipe, Rotation rotation)
    {
        level.setBlock(pos, level.getBlockState(pos).setValue(CONSTRUCTED, true), 2);
        recipe.getBlocksForRotation(level, pos, rotation).ifPresent(blocks -> blocks.forEach(blockPos -> {
            if(level.getBlockEntity(blockPos) instanceof MultiblockComponentBlockEntity child)
            {
                child.parent(pos);
            }
        }));

        ((MultiblockBlockEntity)level.getBlockEntity(pos)).onConstruct();
    }

    protected void onDeconstruct(Level level, BlockPos pos)
    {
        ((MultiblockBlockEntity)level.getBlockEntity(pos)).onDeconstruct();

        level.setBlock(pos, level.getBlockState(pos).setValue(CONSTRUCTED, false), 2);
        MultiblockRecipe.getRecipeFor(level, pos).ifPresent(recipe -> recipe.findValidRotation(level, pos).ifPresent(rotation -> recipe.getBlocksForRotation(level, pos, rotation).ifPresent(blocks -> blocks.forEach(blockPos -> {
            if(level.getBlockEntity(blockPos) instanceof MultiblockComponentBlockEntity child)
            {
                child.parent(null);
            }
        }))));
    }
}
