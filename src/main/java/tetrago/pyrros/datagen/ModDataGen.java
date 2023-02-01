package tetrago.pyrros.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import tetrago.pyrros.Pyrros;

@Mod.EventBusSubscriber(modid = Pyrros.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGen
{
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent gatherDataEvent)
    {
        DataGenerator gen = gatherDataEvent.getGenerator();

        if(gatherDataEvent.includeClient())
        {
            gen.addProvider(new ModBlockStateProvider(gen, gatherDataEvent.getExistingFileHelper()));
            gen.addProvider(new ModItemModelProvider(gen, gatherDataEvent.getExistingFileHelper()));
            gen.addProvider(new ModLanguageProvider(gen, "en_US"));
        }

        if(gatherDataEvent.includeServer())
        {
            ModBlockTagsProvider blockTagsProvider = new ModBlockTagsProvider(gen, gatherDataEvent.getExistingFileHelper());
            gen.addProvider(blockTagsProvider);
            gen.addProvider(new ModItemTagsProvider(gen, blockTagsProvider, gatherDataEvent.getExistingFileHelper()));
            gen.addProvider(new ModLootTableProvider(gen));
            gen.addProvider(new ModRecipeProvider(gen));
        }
    }
}
