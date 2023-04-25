package com.hidoni.transmog.data;

import com.hidoni.transmog.Constants;
import com.hidoni.transmog.data.client.ModBlockStateProvider;
import com.hidoni.transmog.data.client.ModBlockTagsProvider;
import com.hidoni.transmog.data.client.ModItemModelProvider;
import com.hidoni.transmog.data.client.ModLanguageProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    private DataGenerators() {

    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(true, new ModBlockStateProvider(generator, Constants.MOD_ID, event.getExistingFileHelper()));
        generator.addProvider(true, new ModItemModelProvider(generator, Constants.MOD_ID, event.getExistingFileHelper()));
        generator.addProvider(true, new ModLanguageProvider(generator, Constants.MOD_ID));
        generator.addProvider(true, new ModRecipeProvider(generator));
        generator.addProvider(true, new ModBlockTagsProvider(generator, Constants.MOD_ID, event.getExistingFileHelper()));
        generator.addProvider(true, new ModLootTableProvider(generator));
    }
}

