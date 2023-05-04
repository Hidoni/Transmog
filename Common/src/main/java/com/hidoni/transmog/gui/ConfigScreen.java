package com.hidoni.transmog.gui;

import com.hidoni.transmog.config.Config;
import com.hidoni.transmog.config.TooltipDetailLevel;
import com.hidoni.transmog.config.TransmogRenderOption;
import com.hidoni.transmog.i18n.TranslationKeys;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.Codec;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.screens.OptionsSubScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ConfigScreen extends OptionsSubScreen {
    private final Screen parentScreen;
    private OptionsList list;

    public ConfigScreen(Screen parentScreen) {
        super(parentScreen, Minecraft.getInstance().options, Component.translatable(TranslationKeys.TRANSMOG_CONFIG_TITLE));
        this.parentScreen = parentScreen;
    }

    @Override
    public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(poseStack);
        this.list.render(poseStack, mouseX, mouseY, partialTick);
        drawCenteredString(poseStack, this.font, this.title, this.width / 2, 8, ChatFormatting.WHITE.getColor());
        super.render(poseStack, mouseX, mouseY, partialTick);
        List<FormattedCharSequence> list = tooltipAt(this.list, mouseX, mouseY);
        this.renderTooltip(poseStack, list, mouseX, mouseY);
    }

    @Override
    protected void init() {
        Minecraft minecraft = Objects.requireNonNullElseGet(this.minecraft, Minecraft::getInstance);
        this.list = new OptionsList(minecraft, this.width, this.height, 32, this.height - 32, 25);
        this.addConfigOptionsToList();
        this.addWidget(this.list);
        this.addRenderableWidget(new Button(this.width / 2 - 154, this.height - 28, 150, 20, CommonComponents.GUI_DONE, (button -> {
            Config.writeConfigToFile();
            Objects.requireNonNullElseGet(this.minecraft, Minecraft::getInstance).setScreen(this.parentScreen);
        })));
        this.addRenderableWidget(new Button(this.width / 2 + 4, this.height - 28, 150, 20, CommonComponents.GUI_CANCEL, (button -> {
            this.removed();
            Objects.requireNonNullElseGet(this.minecraft, Minecraft::getInstance).setScreen(this.parentScreen);
        })));
    }

    private void addConfigOptionsToList() {
        this.list.addBig(new OptionInstance<>(
                        TranslationKeys.TRANSMOG_CONFIG_RENDER_OPTIONS,
                        // Have to use mc.font.split directly because OptionInstance.splitTooltip is protected.
                        (mc) -> (option) -> mc.font.split(Component.translatable(option.getTooltipKey()), 200),
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
                        (mc) -> (option) -> mc.font.split(Component.translatable(option.getTooltipKey()), 200),
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

    @Override
    public void removed() {
        Config.loadConfigFromFile();
    }
}
