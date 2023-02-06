package tetrago.pyrros.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tetrago.pyrros.client.screen.RollingMillScreen;
import tetrago.pyrros.common.block.FlatDirectionalBlock;
import tetrago.pyrros.common.capability.DirectionalItemStackHandler;
import tetrago.pyrros.common.capability.ModEnergyStorage;
import tetrago.pyrros.common.container.RollingMillContainer;
import tetrago.pyrros.common.recipe.RollingMillRecipe;
import tetrago.pyrros.common.util.BlockEntityUtil;
import tetrago.pyrros.common.util.ItemStackUtil;

import java.util.Map;
import java.util.Optional;

public class RollingMillBlockEntity extends MultiblockBlockEntity implements MenuProvider
{
    public static final int ENERGY_COST = 700;
    public static final int CRAFT_TIME = 150;

    private final ModEnergyStorage mEnergyStorage = new ModEnergyStorage(50000, 1000)
    {
        @Override
        public void onEnergyChanged()
        {
            setChanged();
        }
    };
    private final LazyOptional<IEnergyStorage> mEnergyStorageCapability = LazyOptional.of(() -> mEnergyStorage);

    private final ItemStackHandler mItemStackHandler = new ItemStackHandler(2);
    private final LazyOptional<IItemHandler> mItemHandlerCapability = LazyOptional.of(() -> mItemStackHandler);
    private final DirectionalItemStackHandler mDirectionalItemStackHandler = new DirectionalItemStackHandler(mItemStackHandler, Map.of(
            Rotation.CLOCKWISE_90, 0,
            Rotation.COUNTERCLOCKWISE_90, 1
    ));

    private final ContainerData mData;
    private int mProgress = 0;
    private int mMaxProgress = CRAFT_TIME;

    public RollingMillBlockEntity(BlockPos pPos, BlockState pBlockState)
    {
        super(ModBlockEntities.ROLLING_MILL.get(), pPos, pBlockState);

        mData = new ContainerData()
        {
            @Override
            public int get(int pIndex)
            {
                return switch(pIndex) {
                    default -> mProgress;
                    case 1 -> mMaxProgress;
                };
            }

            @Override
            public void set(int pIndex, int pValue)
            {
                switch(pIndex)
                {
                default -> mProgress = pValue;
                case 1 -> mMaxProgress = pIndex;
                }
            }

            @Override
            public int getCount()
            {
                return 2;
            }
        };
    }

    public static void tick(Level level, BlockPos pos, BlockState state, RollingMillBlockEntity blockEntity)
    {
        if(level.isClientSide()) return;

        if(hasRecipe(blockEntity))
        {
            ++blockEntity.mProgress;
            blockEntity.setChanged();

            blockEntity.mEnergyStorage.extractEnergy(ENERGY_COST / blockEntity.mMaxProgress, false);

            if(blockEntity.mProgress > blockEntity.mMaxProgress)
            {
                craft(blockEntity);
            }
        }
        else if(blockEntity.mProgress > 0)
        {
            blockEntity.mProgress = 0;
            blockEntity.setChanged();
        }
    }

    private static void craft(RollingMillBlockEntity blockEntity)
    {
        SimpleContainer container = BlockEntityUtil.offload(blockEntity.mItemStackHandler);
        Optional<RollingMillRecipe> recipe = blockEntity.level.getRecipeManager().getRecipeFor(RollingMillRecipe.TYPE, container, blockEntity.level);

        recipe.ifPresent(r -> {
            blockEntity.mItemStackHandler.extractItem(0, 1, false);
            ItemStackUtil.insertIntoItemStackHandler(blockEntity.mItemStackHandler, 1, r.getResultItem());

            blockEntity.mProgress = 0;
            blockEntity.setChanged();
        });
    }

    private static boolean hasRecipe(RollingMillBlockEntity blockEntity)
    {
        SimpleContainer container = BlockEntityUtil.offload(blockEntity.mItemStackHandler);
        Optional<RollingMillRecipe> recipe = blockEntity.level.getRecipeManager().getRecipeFor(RollingMillRecipe.TYPE, container, blockEntity.level);

        return recipe.isPresent() && canInsertIntoOutput(container, recipe.get().getResultItem()) && hasMinimumEnergy(blockEntity);
    }

    private static boolean canInsertIntoOutput(SimpleContainer container, ItemStack result)
    {
        return ItemStackUtil.canInsertIntoStack(container.getItem(1), result);
    }

    private static boolean hasMinimumEnergy(RollingMillBlockEntity blockEntity)
    {
        return blockEntity.mEnergyStorage.getEnergyStored() >= ENERGY_COST;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getMultiblockCapability(@NotNull Capability<T> cap, @NotNull Direction side)
    {
        if(cap == CapabilityEnergy.ENERGY)
        {
            return mEnergyStorageCapability.cast();
        }
        else if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            return mDirectionalItemStackHandler.getCapability(side, getBlockState().getValue(FlatDirectionalBlock.DIRECTION));
        }

        return super.getMultiblockCapability(cap, side);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side)
    {
        if(side == null)
        {
            if(cap == CapabilityEnergy.ENERGY)
            {
                return mEnergyStorageCapability.cast();
            }
            else if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            {
                return mItemHandlerCapability.cast();
            }
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps()
    {
        super.invalidateCaps();

        mEnergyStorageCapability.invalidate();
        mItemHandlerCapability.invalidate();
        mDirectionalItemStackHandler.invalidate();
    }

    @Override
    public void load(CompoundTag pTag)
    {
        super.load(pTag);

        mEnergyStorage.deserializeNBT(pTag.get("rolling_mill.energy"));
        mItemStackHandler.deserializeNBT(pTag.getCompound("rolling_mill.inventory"));
    }

    @Override
    protected void saveAdditional(CompoundTag pTag)
    {
        super.saveAdditional(pTag);

        pTag.put("rolling_mill.energy", mEnergyStorage.serializeNBT());
        pTag.put("rolling_mill.inventory", mItemStackHandler.serializeNBT());
    }

    @Override
    public Component getDisplayName()
    {
        return new TranslatableComponent(RollingMillScreen.TITLE);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer)
    {
        return new RollingMillContainer(pContainerId, pPlayerInventory, this, mData);
    }

    @Override
    public void onDeconstruct()
    {
        super.onDeconstruct();

        SimpleContainer container = BlockEntityUtil.offload(mItemStackHandler);
        Containers.dropContents(level, worldPosition, container);
    }
}
