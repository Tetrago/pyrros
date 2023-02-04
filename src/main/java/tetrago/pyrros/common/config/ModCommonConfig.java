package tetrago.pyrros.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ModCommonConfig
{
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> BROOKITE_ORE_ENABLED;
    public static final ForgeConfigSpec.ConfigValue<Integer> BROOKITE_ORE_VEINS_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> BROOKITE_ORE_VEIN_SIZE;
    public static final ForgeConfigSpec.ConfigValue<Boolean> CRYOLITE_ORE_ENABLED;
    public static final ForgeConfigSpec.ConfigValue<Integer> CRYOLITE_ORE_VEINS_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> CRYOLITE_ORE_VEIN_SIZE;

    static
    {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("generation");

        BROOKITE_ORE_ENABLED = builder.define("brookite_ore_enabled", true);
        BROOKITE_ORE_VEINS_PER_CHUNK = builder.define("brookite_ore_count", 7);
        BROOKITE_ORE_VEIN_SIZE = builder.defineInRange("brookite_ore_size", 9, 4, 20);
        CRYOLITE_ORE_ENABLED = builder.define("cryolite_ore_enabled", true);
        CRYOLITE_ORE_VEINS_PER_CHUNK = builder.define("cryolite_ore_count", 7);
        CRYOLITE_ORE_VEIN_SIZE = builder.defineInRange("cryolite_ore_size", 9, 4, 20);

        builder.pop();
        SPEC = builder.build();
    }
}
