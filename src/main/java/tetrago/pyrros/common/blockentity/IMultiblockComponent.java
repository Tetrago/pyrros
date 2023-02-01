package tetrago.pyrros.common.blockentity;

import net.minecraft.core.BlockPos;

public interface IMultiblockComponent
{
    boolean isConstructed();
    BlockPos getMultiblockPos();
}
