package com.hidoni.transmog.config;

import com.hidoni.transmog.i18n.TranslationKeys;
import net.minecraft.util.OptionEnum;
import org.jetbrains.annotations.NotNull;

public enum TransmogRenderOption implements OptionEnum {
    OFF(0, TranslationKeys.TRANSMOG_CONFIG_RENDER_OPTION_OFF, false, false),
    IN_WORLD(1, TranslationKeys.TRANSMOG_CONFIG_RENDER_OPTION_IN_WORLD, true, false),
    EVERYWHERE(2, TranslationKeys.TRANSMOG_CONFIG_RENDER_OPTION_EVERYWHERE, true, true);

    private final int id;
    private final String translationKey;
    public final boolean renderInWorld;
    public final boolean renderInInventory;

    TransmogRenderOption(int id, String translationKey, boolean renderInWorld, boolean renderInInventory) {
        this.id = id;
        this.translationKey = translationKey;
        this.renderInWorld = renderInWorld;
        this.renderInInventory = renderInInventory;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public static TransmogRenderOption fromId(int id) {
        return switch (id) {
            case 0 -> OFF;
            case 2 -> EVERYWHERE;
            default -> IN_WORLD;
        };
    }

    @Override
    public @NotNull String getKey() {
        return this.translationKey;
    }

    public @NotNull String getTooltipKey() {
        return this.translationKey + ".tooltip";
    }
}
