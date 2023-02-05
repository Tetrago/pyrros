package tetrago.pyrros.client.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.client.screen.widget.EnergyWidget;
import tetrago.pyrros.common.container.RollingMillContainer;

import java.util.Optional;

public class RollingMillScreen extends ContainerScreen<RollingMillContainer>
{
    public static final String TITLE = Pyrros.modid("screen.{}.rolling_mill");
    public static final ResourceLocation BACKGROUND = Pyrros.loc("textures/gui/rolling_mill.png");

    private EnergyWidget mEnergyWidget;

    public RollingMillScreen(RollingMillContainer pMenu, Inventory pPlayerInventory, Component pTitle)
    {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init()
    {
        super.init();

        mEnergyWidget = new EnergyWidget(getX() + 8, getY() + 21, 16, 44)
        {
            @Override
            protected int getEnergyStored()
            {
                return menu.getEnergyStored();
            }

            @Override
            protected int getMaxEnergyStored()
            {
                return menu.getMaxEnergyStored();
            }
        };
    }

    @Override
    protected void renderTooltip(PoseStack pPoseStack, int pX, int pY)
    {
        super.renderTooltip(pPoseStack, pX, pY);

        if(mEnergyWidget.contains(pX, pY))
        {
            renderTooltip(pPoseStack, mEnergyWidget.getTooltips(), Optional.empty(), pX, pY);
        }
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY)
    {
        renderBackground(pPoseStack, BACKGROUND);

        mEnergyWidget.draw(pPoseStack, pPartialTick, pMouseX, pMouseY);
    }
}
