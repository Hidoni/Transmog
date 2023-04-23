package com.hidoni.transmog.gui;

import com.hidoni.transmog.config.Config;
import com.hidoni.transmog.config.TransmogRenderOption;
import com.hidoni.transmog.i18n.TranslationKeys;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.Codec;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.OptionsSubScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
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
    }

    @Override
    protected void init() {
        Minecraft minecraft = Objects.requireNonNullElseGet(this.minecraft, Minecraft::getInstance);
        this.list = new OptionsList(minecraft, this.width, this.height, 32, this.height - 32, 25);
        this.addConfigOptionsToList();
        this.addWidget(this.list);
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, (button) -> {
            Config.writeConfigToFile();
            Objects.requireNonNullElseGet(this.minecraft, Minecraft::getInstance).setScreen(this.parentScreen);
        }).bounds(this.width / 2 - 154, this.height - 28, 150, 20).build());
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_CANCEL, (button) -> {
            this.removed();
            Objects.requireNonNullElseGet(this.minecraft, Minecraft::getInstance).setScreen(this.parentScreen);
        }).bounds(this.width / 2 + 4, this.height - 28, 150, 20).build());
    }

    private void addConfigOptionsToList() {
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
    }

    @Override
    public void removed() {
        Config.loadConfigFromFile();
    }
}
