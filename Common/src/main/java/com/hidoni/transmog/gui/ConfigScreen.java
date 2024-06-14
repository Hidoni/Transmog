package com.hidoni.transmog.gui;

import com.hidoni.transmog.config.Config;
import com.hidoni.transmog.config.TooltipDetailLevel;
import com.hidoni.transmog.config.TransmogRenderOption;
import com.hidoni.transmog.i18n.TranslationKeys;
import com.mojang.serialization.Codec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

import java.util.Arrays;
import java.util.Objects;

public class ConfigScreen extends OptionsSubScreen {
    public ConfigScreen(Screen parentScreen) {
        super(parentScreen, Minecraft.getInstance().options, Component.translatable(TranslationKeys.TRANSMOG_CONFIG_TITLE));
    }

    @Override
    protected void addFooter() {
        LinearLayout linearLayout = this.layout.addToFooter(LinearLayout.horizontal()).spacing(8);
        linearLayout.addChild(Button.builder(CommonComponents.GUI_CANCEL, (button) -> {
            Config.loadConfigFromFile();
            Objects.requireNonNull(this.minecraft).setScreen(this.lastScreen);
        }).width(200).build());
        linearLayout.addChild(Button.builder(CommonComponents.GUI_DONE, (button -> Objects.requireNonNull(this.minecraft).setScreen(this.lastScreen))).width(200).build());
    }

    @Override
    protected void addOptions() {
        if (this.list != null) {
            this.list.addBig(new OptionInstance<>(
                            TranslationKeys.TRANSMOG_CONFIG_RENDER_OPTIONS,
                            (option) -> Tooltip.create(Component.translatable(option.getTooltipKey())),
                            OptionInstance.forOptionEnum(),
                            new OptionInstance.Enum<>(Arrays.asList(TransmogRenderOption.values()),
                                    Codec.INT.xmap(
                                            TransmogRenderOption::fromId,
                                            TransmogRenderOption::getId)
                            ),
                            Config.renderOption,
                            (option) -> Config.renderOption = option
                    )
            );
            this.list.addBig(new OptionInstance<>(
                            TranslationKeys.TRANSMOG_CONFIG_TOOLTIP_OPTIONS,
                            (option) -> Tooltip.create(Component.translatable(option.getTooltipKey())),
                            OptionInstance.forOptionEnum(),
                            new OptionInstance.Enum<>(Arrays.asList(TooltipDetailLevel.values()),
                                    Codec.INT.xmap(
                                            TooltipDetailLevel::fromId,
                                            TooltipDetailLevel::getId)
                            ),
                            Config.tooltipDetailLevel,
                            (option) -> Config.tooltipDetailLevel = option
                    )
            );
            this.list.addBig(new OptionInstance<>(
                            TranslationKeys.TRANSMOG_CONFIG_DISABLE_DURING_PVP_DURATION_OPTION,
                            OptionInstance.cachedConstantTooltip(Component.translatable(TranslationKeys.TRANSMOG_CONFIG_DISABLE_DURING_PVP_DURATION_TOOLTIP)),
                            (component, integer) -> Component.translatable("options.generic_value", component, integer == 0 ? Component.translatable(TranslationKeys.TRANSMOG_CONFIG_RENDER_OPTION_OFF) : Component.translatable(TranslationKeys.TRANSMOG_CONFIG_DISABLE_DURING_PVP_DURATION_VALUE_LABEL, integer)),
                            new OptionInstance.IntRange(0, 120),
                            Config.pvpDisableDuration,
                            integer -> Config.pvpDisableDuration = integer
                    )
            );
        }
    }

    @Override
    public void removed() {
    }

    @Override
    public void onClose() {
        if (this.list != null) {
            Config.writeConfigToFile();
        }
        Objects.requireNonNull(this.minecraft).setScreen(this.lastScreen);
    }
}
