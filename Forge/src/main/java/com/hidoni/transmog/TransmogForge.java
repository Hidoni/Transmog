package com.hidoni.transmog;

import com.hidoni.transmog.i18n.TranslationKeys;
import com.hidoni.transmog.item.VoidFragmentItem;
import com.hidoni.transmog.registry.ModItemGroups;
import com.hidoni.transmog.registry.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;

@Mod(Constants.MOD_ID)
public class TransmogForge {
    public TransmogForge() {
        registerCreativeModeTab();
        Transmog.init();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> TransmogForgeClient::init);
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    }

    public static void registerCreativeModeTab() {
        ModItemGroups.TRANSMOG_CREATIVE_TAB = new CreativeModeTab(Constants.MOD_ID + ".creative_tab") {
            @Override
            public @NotNull ItemStack makeIcon() {
                ItemStack itemStack = new ItemStack(ModItems.VOID_FRAGMENT.get());
                itemStack.getOrCreateTag().putBoolean(VoidFragmentItem.VOID_FRAGMENT_SHOW_FOIL_KEY, false);
                return itemStack;
            }

            @Override
            public @NotNull Component getDisplayName() {
                return Component.translatable(TranslationKeys.TRANSMOG_CREATIVE_MODE_TAB_NAME);
            }
        };
    }
}
