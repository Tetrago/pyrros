package tetrago.pyrros.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ModCommonConfig
{
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> BROOKITE_ORE_ENABLED;
    public static final ForgeConfigSpec.ConfigValue<Integer> BRROKITE_ORE_VEINS_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> BRROKITE_ORE_VEIN_SIZE;

    static
    {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("generation");

        BROOKITE_ORE_ENABLED = builder.define("brookite_ore_enabled", true);
        BRROKITE_ORE_VEINS_PER_CHUNK = builder.define("brookite_ore_count", 7);
        BRROKITE_ORE_VEIN_SIZE = builder.defineInRange("brookite_ore_size", 9, 4, 20);

        builder.pop();
        SPEC = builder.build();
    }
}
