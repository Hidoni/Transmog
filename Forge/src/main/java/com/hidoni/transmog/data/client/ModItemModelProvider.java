package com.hidoni.transmog.data.client;

import com.hidoni.transmog.registry.ModBlocks;
import com.hidoni.transmog.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        String transmogBlockPath = ModBlocks.TRANSMOGRIFICATION_TABLE.getResourceLocation().getPath();
        withExistingParent(transmogBlockPath, modLoc("block/" + transmogBlockPath));
        ModelFile generated = getExistingFile(mcLoc("item/generated"));
        getBuilder(ModItems.VOID_FRAGMENT.getResourceLocation().toString()).parent(generated).texture("layer0", modLoc("item/void_fragment"));
    }
}
