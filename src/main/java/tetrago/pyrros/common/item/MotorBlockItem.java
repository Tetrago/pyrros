package tetrago.pyrros.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.common.block.MotorBlock;

import java.util.List;

public class MotorBlockItem extends BlockItem
{
    public static final String TOOLTIP_MOTOR_STRENGTH = Pyrros.modid("tooltip.{}.motor_strength");

    public MotorBlockItem(Block pBlock, Properties pProperties)
    {
        super(pBlock, pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag)
    {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);

        MotorBlock block = (MotorBlock)getBlock();
        pTooltip.add(new TranslatableComponent(TOOLTIP_MOTOR_STRENGTH).append(": " + block.getMotorStrength()).withStyle(ChatFormatting.GRAY));
    }
}
