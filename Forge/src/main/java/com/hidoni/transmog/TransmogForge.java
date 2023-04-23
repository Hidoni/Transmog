package com.hidoni.transmog;

import com.hidoni.transmog.i18n.TranslationKeys;
import com.hidoni.transmog.item.VoidFragmentItem;
import com.hidoni.transmog.registry.ModBlocks;
import com.hidoni.transmog.registry.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class TransmogForge {
    public TransmogForge() {
        Transmog.init();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> TransmogForgeClient::init);
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(TransmogForge::registerCreativeModeTab);
    }

    public static void registerCreativeModeTab(CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(new ResourceLocation(Constants.MOD_ID, "creative_tab"), builder -> builder
                .icon(() -> {
                    ItemStack itemStack = new ItemStack(ModItems.VOID_FRAGMENT.get());
                    itemStack.getOrCreateTag().putBoolean(VoidFragmentItem.VOID_FRAGMENT_SHOW_FOIL_KEY, false);
                    return itemStack;
                })
                .title(Component.translatable(TranslationKeys.TRANSMOG_CREATIVE_MODE_TAB_NAME))
                .displayItems(((itemDisplayParameters, output) -> {
                    output.accept(ModItems.VOID_FRAGMENT.get());
                    output.accept(ModBlocks.TRANSMOGRIFICATION_TABLE.get());
                }))
        );
    }
}
