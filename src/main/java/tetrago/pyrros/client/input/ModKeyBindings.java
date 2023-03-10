package tetrago.pyrros.client.input;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.settings.KeyConflictContext;
import tetrago.pyrros.Pyrros;

public class ModKeyBindings
{
    public static final String CATEGORY = Pyrros.modid("key.categories.{}");

    public static final String UNIVERSE_MAP = "key.universe_map";

    public static KeyMapping sUniverseMap;

    public static void register()
    {
        ClientRegistry.registerKeyBinding(sUniverseMap = make(InputConstants.KEY_M));
    }

    private static KeyMapping make(int key)
    {
        return make(key, KeyConflictContext.IN_GAME);
    }

    private static KeyMapping make(int key, KeyConflictContext ctx)
    {
        return new KeyMapping(UNIVERSE_MAP, ctx, InputConstants.Type.KEYSYM, key, CATEGORY);
    }
}
