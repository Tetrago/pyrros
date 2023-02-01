package tetrago.pyrros.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.common.block.ModBlocks;

public class ModLanguageProvider extends LanguageProvider
{
    public ModLanguageProvider(DataGenerator gen, String locale)
    {
        super(gen, Pyrros.MODID, locale);
    }

    @Override
    protected void addTranslations()
    {
        add(Pyrros.modid("itemGroup.{}"), "Pyrros");

        add(ModBlocks.MACHINE_FRAME.get(), "Machine Frame");
    }
}
