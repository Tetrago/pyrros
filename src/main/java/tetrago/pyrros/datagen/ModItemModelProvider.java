package tetrago.pyrros.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import tetrago.pyrros.Pyrros;
import tetrago.pyrros.common.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider
{
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper)
    {
        super(generator, Pyrros.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        simpleBlockItem(ModItems.MACHINE_FRAME);
        simpleBlockItem(ModItems.ENCASED_MACHINE_FRAME);
        simpleBlockItem(ModItems.REINFORCED_MACHINE_FRAME);
    }

    private void simpleBlockItem(final RegistryObject<Item> item)
    {
        withExistingParent(item.getId().getPath(), modLoc("block/" + item.getId().getPath()));
    }

    private void simpleItem(final RegistryObject<Item> item)
    {
        singleTexture(item.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/" + item.getId().getPath()));
    }
}
