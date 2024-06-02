package com.junethewoods.melonylib.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public abstract class MLItemModelProvider extends ItemModelProvider {
    public MLItemModelProvider(DataGenerator generator, String modId, ExistingFileHelper existingFileHelper) {
        super(generator, modId, existingFileHelper);
    }

    private ItemModelBuilder standard(ModelFile model, String name) {
        return getBuilder(name).parent(model).texture("layer0", "item/" + name);
    }

    private ItemModelBuilder block(ModelFile model, String name) {
        return getBuilder(name).parent(model).texture("layer0", "block/" + name);
    }

    private ItemModelBuilder makeBlockItem(String name) {
        return withExistingParent(name, modLoc("block/" + name));
    }

    private ItemModelBuilder makeBlockItem(String name, String extras) {
        return withExistingParent(name, modLoc("block/" + name + extras));
    }

    private ItemModelBuilder newArmorSet(String name, boolean isBreastplate) {
        ModelFile generated = getExistingFile(mcLoc("item/generated"));
        this.standard(generated, name + "_helmet");
        if (isBreastplate) {
            this.standard(generated, name + "_breastplate");
        } else {
            this.standard(generated, name + "_chestplate");
        }
        this.standard(generated, name + "_leggings");
        return this.standard(generated, name + "_boots");
    }

    private ItemModelBuilder newToolSet(String name, boolean hasGreatsword) {
        ModelFile handheld = getExistingFile(mcLoc("item/handheld"));
        ModelFile handheld32 = getExistingFile(modLoc("item/handheld_thirty_two"));
        this.standard(handheld, name + "_sword");
        this.standard(handheld, name + "_pickaxe");
        this.standard(handheld, name + "_shovel");
        this.standard(handheld, name + "_axe");
        if (hasGreatsword) {
            this.standard(handheld32, name + "_greatsword");
        }
        this.standard(handheld, name + "_hoe");
        return getBuilder("milked_" + name + "_sword").parent(handheld).texture("layer0", "item/" + name + "_sword").texture("layer1",
                "item/milked_sword_base");
    }

    private ItemModelBuilder newBow(String bowName) {
        getBuilder(bowName + "_pulling_0").parent(getExistingFile(modLoc("item/" + bowName))).texture("layer0", "item/" + bowName + "_pulling_0");
        getBuilder(bowName + "_pulling_1").parent(getExistingFile(modLoc("item/" + bowName))).texture("layer0", "item/" + bowName + "_pulling_1");
        return getBuilder(bowName + "_pulling_2").parent(getExistingFile(modLoc("item/" + bowName))).texture("layer0", "item/" + bowName + "_pulling_2");
    }
}
