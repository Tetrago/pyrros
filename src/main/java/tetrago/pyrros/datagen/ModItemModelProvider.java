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
        simpleBlockItem(ModItems.ARC_FURNACE_CONTROLLER);
        simpleBlockItem(ModItems.ENERGY_PORT);
        simpleBlockItem(ModItems.ITEM_PORT);
        simpleBlockItem(ModItems.MACHINE_FRAME);
        simpleBlockItem(ModItems.ENCASED_MACHINE_FRAME);
        simpleBlockItem(ModItems.REINFORCED_MACHINE_FRAME);
        simpleBlockItem(ModItems.ROLLER);
        simpleBlockItem(ModItems.STEEL_BLOCK);
        simpleBlockItem(ModItems.TITANIUM_BLOCK);
        simpleBlockItem(ModItems.ALUMINUM_BLOCK);
        simpleBlockItem(ModItems.BROOKITE_ORE);
        simpleBlockItem(ModItems.DEEPSLATE_BROOKITE_ORE);
        simpleBlockItem(ModItems.CRYOLITE_ORE);
        simpleBlockItem(ModItems.DEEPSLATE_CRYOLITE_ORE);
        simpleBlockItem(ModItems.COPPER_COIL);
        simpleBlockItem(ModItems.GOLD_COIL);

        simpleItem(ModItems.STEEL_INGOT);
        simpleItem(ModItems.STEEL_NUGGET);
        simpleItem(ModItems.RAW_BROOKITE);
        simpleItem(ModItems.RAW_CRYOLITE);
        simpleItem(ModItems.TITANIUM_INGOT);
        simpleItem(ModItems.TITANIUM_NUGGET);
        simpleItem(ModItems.ALUMINUM_INGOT);
        simpleItem(ModItems.ALUMINUM_NUGGET);
        simpleItem(ModItems.SLAG);
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
