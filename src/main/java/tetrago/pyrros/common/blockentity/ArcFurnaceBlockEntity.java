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
import tetrago.pyrros.client.screen.ArcFurnaceScreen;
import tetrago.pyrros.common.block.FlatDirectionalBlock;
import tetrago.pyrros.common.capability.DirectionalItemStackHandler;
import tetrago.pyrros.common.capability.ModEnergyStorage;
import tetrago.pyrros.common.container.ArcFurnaceContainer;
import tetrago.pyrros.common.item.ModItems;
import tetrago.pyrros.common.recipe.ArcFurnaceRecipe;
import tetrago.pyrros.common.util.BlockEntityUtil;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class ArcFurnaceBlockEntity extends MultiblockBlockEntity implements MenuProvider
{
    private final ModEnergyStorage mEnergyStorage = new ModEnergyStorage(50000, 1000)
    {
        @Override
        public void onEnergyChanged()
        {
            setChanged();
        }
    };
    private final LazyOptional<IEnergyStorage> mEnergyStorageCapability = LazyOptional.of(() -> mEnergyStorage);

    private final ItemStackHandler mItemStackHandler = new ItemStackHandler(3);
    private final LazyOptional<IItemHandler> mItemHandlerCapability = LazyOptional.of(() -> mItemStackHandler);
    private final DirectionalItemStackHandler mDirectionalItemStackHandler = new DirectionalItemStackHandler(mItemStackHandler, Map.of(
            Rotation.CLOCKWISE_90, 0,
            Rotation.CLOCKWISE_180, 2,
            Rotation.COUNTERCLOCKWISE_90, 1
    ));

    private final ContainerData mData;
    private int mProgress = 0;
    private int mMaxProgress = 150;

    public ArcFurnaceBlockEntity(BlockPos pPos, BlockState pBlockState)
    {
        super(ModBlockEntities.ARC_FURNACE_CONTROLLER.get(), pPos, pBlockState);

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
                case 1 -> mMaxProgress = pValue;
                }
            }

            @Override
            public int getCount()
            {
                return 2;
            }
        };
    }

    public static void tick(Level level, BlockPos pos, BlockState state, ArcFurnaceBlockEntity blockEntity)
    {
        if(level.isClientSide()) return;

        if(hasRecipe(blockEntity))
        {
            ++blockEntity.mProgress;
            setChanged(level, pos, state);

            blockEntity.mEnergyStorage.extractEnergy(700 / blockEntity.mMaxProgress, false);

            if(blockEntity.mProgress > blockEntity.mMaxProgress)
            {
                craft(blockEntity);
            }
        }
        else if(blockEntity.mProgress > 0)
        {
            blockEntity.mProgress = 0;
            setChanged(level, pos, state);
        }
    }

    private static void craft(ArcFurnaceBlockEntity blockEntity)
    {
        SimpleContainer container = BlockEntityUtil.offload(blockEntity.mItemStackHandler);
        Optional<ArcFurnaceRecipe> recipe = blockEntity.level.getRecipeManager().getRecipeFor(ArcFurnaceRecipe.TYPE, container, blockEntity.level);

        recipe.ifPresent(r -> {
            blockEntity.mItemStackHandler.extractItem(0, 1, false);
            blockEntity.mItemStackHandler.setStackInSlot(1, new ItemStack(r.getResultItem().getItem(), blockEntity.mItemStackHandler.getStackInSlot(1).getCount() + r.getResultItem().getCount()));

            ItemStack stack = blockEntity.mItemStackHandler.getStackInSlot(2);
            if((stack.isEmpty() || stack.getCount() + 1 <= stack.getMaxStackSize()) && new Random().nextDouble() <= 0.2)
            {
                blockEntity.mItemStackHandler.setStackInSlot(2, new ItemStack(ModItems.SLAG.get(), stack.getCount() + 1));
            }

            blockEntity.mProgress = 0;
        });
    }

    private static boolean hasRecipe(ArcFurnaceBlockEntity blockEntity)
    {
        SimpleContainer container = BlockEntityUtil.offload(blockEntity.mItemStackHandler);
        Optional<ArcFurnaceRecipe> recipe = blockEntity.level.getRecipeManager().getRecipeFor(ArcFurnaceRecipe.TYPE, container, blockEntity.level);

        return recipe.isPresent() && canInsertIntoOutput(container, recipe.get().getResultItem()) && hasMinimumEnergy(blockEntity);
    }

    private static boolean canInsertIntoOutput(SimpleContainer inventory, ItemStack result)
    {
        final ItemStack stack = inventory.getItem(1);
        return stack.isEmpty() || (stack.getItem() == result.getItem() && stack.getCount() + result.getCount() <= stack.getMaxStackSize());
    }

    private static boolean hasMinimumEnergy(ArcFurnaceBlockEntity blockEntity)
    {
        return blockEntity.mEnergyStorage.getEnergyStored() > 800;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getMultiblockCapability(@NotNull Capability<T> cap, @Nullable Direction side)
    {
        if(cap == CapabilityEnergy.ENERGY)
        {
            return mEnergyStorageCapability.cast();
        }
        else if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            return mDirectionalItemStackHandler.getCapability(cap, side, level.getBlockState(getBlockPos()).getValue(FlatDirectionalBlock.DIRECTION));
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
    public void load(CompoundTag pTag)
    {
        super.load(pTag);

        mEnergyStorage.deserializeNBT(pTag.get("arc_furnace.energy"));
        mItemStackHandler.deserializeNBT(pTag.getCompound("arc_furnace.inventory"));
    }

    @Override
    protected void saveAdditional(CompoundTag pTag)
    {
        super.saveAdditional(pTag);

        pTag.put("arc_furnace.energy", mEnergyStorage.serializeNBT());
        pTag.put("arc_furnace.inventory", mItemStackHandler.serializeNBT());
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
    public Component getDisplayName()
    {
        return new TranslatableComponent(ArcFurnaceScreen.UNLOCALIZED_NAME);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer)
    {
        return new ArcFurnaceContainer(pContainerId, pPlayerInventory, this, mData);
    }

    @Override
    public void onDeconstruct()
    {
        super.onDeconstruct();

        SimpleContainer container = BlockEntityUtil.offload(mItemStackHandler);
        Containers.dropContents(level, worldPosition, container);
    }
}
