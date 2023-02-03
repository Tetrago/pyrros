package tetrago.pyrros.client.screen.widget;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;

import java.util.List;

public abstract class Widget extends GuiComponent
{
    protected final Rect2i mRect;

    public Widget(Rect2i rect)
    {
        mRect = rect;
    }

    public boolean contains(int x, int y)
    {
        return mRect.contains(x, y);
    }

    public abstract List<Component> getTooltips();
    public abstract void draw(PoseStack poseStack, float partialTicks, int mouseX, int mouseY);
}
