package tetrago.pyrros.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

public abstract class ContainerScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T>
{
    public ContainerScreen(T pMenu, Inventory pPlayerInventory, Component pTitle)
    {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick)
    {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pPoseStack, pMouseX, pMouseY);
    }

    protected int getX()
    {
        return (width - imageWidth) / 2;
    }

    protected int getY()
    {
        return (height - imageHeight) / 2;
    }

    protected void renderBackground(PoseStack poseStack, ResourceLocation background)
    {
        RenderSystem.setShaderTexture(0, background);

        blit(poseStack, getX(), getY(), 0, 0, imageWidth, imageHeight);
    }
}
