package tetrago.pyrros.integration.waila;

import mcp.mobius.waila.api.*;
import mcp.mobius.waila.api.event.WailaRayTraceEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.common.MinecraftForge;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.common.block.MultiblockBlock;
import tetrago.pyrros.common.blockentity.MultiblockComponentBlockEntity;

@WailaPlugin
public class ModWailaPlugin implements IWailaPlugin
{
    public static final String TOOLTIP_CONSTRUCTED = Pyrros.modid("text.{}.tooltip_constructed");

    private static IWailaClientRegistration sClient;

    public ModWailaPlugin()
    {
        MinecraftForge.EVENT_BUS.addListener(this::overrideMultiblockComponentBlockEntity);
    }

    public void overrideMultiblockComponentBlockEntity(WailaRayTraceEvent event)
    {
        Accessor<?> accessor = event.getAccessor();
        if(!(accessor instanceof BlockAccessor blockAccessor)) return;

        if(!(blockAccessor.getBlockEntity() instanceof MultiblockComponentBlockEntity)) return;

        CompoundTag data = blockAccessor.getServerData();
        if(!data.contains("multiblock_component.position")) return;

        BlockPos position = NbtUtils.readBlockPos(data.getCompound("multiblock_component.position"));
        event.setAccessor(sClient.createBlockAccessor(accessor.getLevel().getBlockState(position).getBlock()
                        .defaultBlockState().setValue(MultiblockBlock.CONSTRUCTED, true),
                null, accessor.getLevel(), accessor.getPlayer(),
                null, blockAccessor.getHitResult(), accessor.isServerConnected()));
    }

    @Override
    public void register(IWailaCommonRegistration registration)
    {
        registration.registerBlockDataProvider((compoundTag, serverPlayer, level, blockEntity, b) -> {
            MultiblockComponentBlockEntity component = (MultiblockComponentBlockEntity)blockEntity;

            if(!component.isConstructed()) return;
            compoundTag.put("multiblock_component.position", NbtUtils.writeBlockPos(component.getMultiblockPos()));
        }, MultiblockComponentBlockEntity.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration)
    {
        sClient = registration;

        registration.registerComponentProvider((iTooltip, blockAccessor, iPluginConfig) -> {
            if(!blockAccessor.getBlockState().hasProperty(MultiblockBlock.CONSTRUCTED) || !blockAccessor.getBlockState().getValue(MultiblockBlock.CONSTRUCTED)) return;
            iTooltip.add(new TranslatableComponent(TOOLTIP_CONSTRUCTED));
        }, TooltipPosition.HEAD, MultiblockBlock.class);
    }
}
