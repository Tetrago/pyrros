package tetrago.pyrros.client.screen.widget;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;

import java.util.List;

public abstract class EnergyWidget extends Widget
{
    public EnergyWidget(int x, int y, int w, int h)
    {
        super(new Rect2i(x, y, w, h));
    }

    @Override
    public List<Component> getTooltips()
    {
        return List.of(new TextComponent(String.format("%.2f kFE/ %.2f kFE", getEnergyStored() / 1000.0f, getMaxEnergyStored() / 1000.0f)));
    }

    @Override
    public void draw(PoseStack poseStack, float partialTicks, int mouseX, int mouseY)
    {
        fillGradient(poseStack,
                mRect.getX(),
                mRect.getY() + (int)((1 - (float)getEnergyStored() / getMaxEnergyStored()) * mRect.getHeight()),
                mRect.getX() + mRect.getWidth(),
                mRect.getY() + mRect.getHeight(),
                0xff9cfd56, 0xff00dd20);
    }

    protected abstract int getEnergyStored();
    protected abstract int getMaxEnergyStored();
}
