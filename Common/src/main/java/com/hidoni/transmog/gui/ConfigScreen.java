package com.hidoni.transmog.gui;

import com.hidoni.transmog.config.Config;
import com.hidoni.transmog.config.TooltipDetailLevel;
import com.hidoni.transmog.config.TransmogRenderOption;
import com.hidoni.transmog.i18n.TranslationKeys;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.CycleOption;
import net.minecraft.client.Minecraft;
import net.minecraft.client.ProgressOption;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.screens.OptionsSubScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.FormattedCharSequence;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ConfigScreen extends OptionsSubScreen {
    private final Screen parentScreen;
    private OptionsList list;

    public ConfigScreen(Screen parentScreen) {
        super(parentScreen, Minecraft.getInstance().options, new TranslatableComponent(TranslationKeys.TRANSMOG_CONFIG_TITLE));
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
        CycleOption<TransmogRenderOption> transmogRenderOptionCycleOption = CycleOption.create(
                TranslationKeys.TRANSMOG_CONFIG_RENDER_OPTIONS,
                Arrays.asList(TransmogRenderOption.values()),
                option -> new TranslatableComponent(option.getKey()),
                (options) -> Config.renderOption,
                (options, option, value) -> Config.renderOption = value
        );
        CycleOption<TooltipDetailLevel> tooltipDetailLevelCycleOption = CycleOption.create(
                TranslationKeys.TRANSMOG_CONFIG_TOOLTIP_OPTIONS,
                Arrays.asList(TooltipDetailLevel.values()),
                option -> new TranslatableComponent(option.getKey()),
                (options) -> Config.tooltipDetailLevel,
                (options, option, value) -> Config.tooltipDetailLevel = value
        );
        ProgressOption pvpDurationProgressOption = new ProgressOption(
                TranslationKeys.TRANSMOG_CONFIG_DISABLE_DURING_PVP_DURATION_OPTION,
                0D,
                120D,
                1F,
                (options) -> (double) Config.pvpDisableDuration,
                (options, value) -> Config.pvpDisableDuration = (int)Math.round(value),
                (options, progressOption) -> new TranslatableComponent("options.generic_value", new TranslatableComponent(TranslationKeys.TRANSMOG_CONFIG_DISABLE_DURING_PVP_DURATION_OPTION), Config.pvpDisableDuration == 0 ? new TranslatableComponent(TranslationKeys.TRANSMOG_CONFIG_RENDER_OPTION_OFF) : new TranslatableComponent(TranslationKeys.TRANSMOG_CONFIG_DISABLE_DURING_PVP_DURATION_VALUE_LABEL, Config.pvpDisableDuration)),
                (mc) -> mc.font.split(new TranslatableComponent(TranslationKeys.TRANSMOG_CONFIG_DISABLE_DURING_PVP_DURATION_TOOLTIP), 200)
        );
        transmogRenderOptionCycleOption.setTooltip((mc) -> (option) -> mc.font.split(new TranslatableComponent(option.getTooltipKey()), 200));
        tooltipDetailLevelCycleOption.setTooltip((mc) -> (option) -> mc.font.split(new TranslatableComponent(option.getTooltipKey()), 200));
        this.list.addBig(transmogRenderOptionCycleOption);
        this.list.addBig(tooltipDetailLevelCycleOption);
        this.list.addBig(pvpDurationProgressOption);
    }

    @Override
    public void removed() {
        Config.loadConfigFromFile();
    }
}
