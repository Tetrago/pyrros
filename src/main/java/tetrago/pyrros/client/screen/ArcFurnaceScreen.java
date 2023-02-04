package tetrago.pyrros.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.client.screen.widget.EnergyWidget;
import tetrago.pyrros.common.container.ArcFurnaceContainer;

import java.util.Optional;

public class ArcFurnaceScreen extends AbstractContainerScreen<ArcFurnaceContainer>
{
    public static final String TITLE = Pyrros.modid("screen.{}.arc_furnace");
    public static final ResourceLocation BACKGROUND = Pyrros.loc("textures/gui/arc_furnace.png");

    private EnergyWidget mEnergyWidget;

    public ArcFurnaceScreen(ArcFurnaceContainer pMenu, Inventory pPlayerInventory, Component pTitle)
    {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init()
    {
        super.init();

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        mEnergyWidget = new EnergyWidget(x + 8, y + 21, 16, 44)
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
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick)
    {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pPoseStack, pMouseX, pMouseY);
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
        RenderSystem.setShaderTexture(0, BACKGROUND);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);

        mEnergyWidget.draw(pPoseStack, pPartialTick, pMouseX, pMouseY);

        if(menu.isCrafting())
        {
            blit(pPoseStack, x + 68, y + 34, 176, 0, menu.getScaledProgress(), 16);
        }
    }
}
