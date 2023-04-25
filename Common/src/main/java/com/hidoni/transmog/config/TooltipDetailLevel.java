package com.hidoni.transmog.config;

import com.hidoni.transmog.i18n.TranslationKeys;
import net.minecraft.util.OptionEnum;
import org.jetbrains.annotations.NotNull;

public enum TooltipDetailLevel implements OptionEnum {
    NONE(0, TranslationKeys.TRANSMOG_CONFIG_TOOLTIP_OPTION_NONE),
    MINIMAL(1, TranslationKeys.TRANSMOG_CONFIG_TOOLTIP_OPTION_MINIMAL),
    FULL(2, TranslationKeys.TRANSMOG_CONFIG_TOOLTIP_OPTION_FULL);
    private final int id;
    private final String translationKey;

    TooltipDetailLevel(int id, String translationKey) {
        this.id = id;
        this.translationKey = translationKey;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public static TooltipDetailLevel fromId(int id) {
        return switch (id) {
            case 0 -> NONE;
            case 1 -> MINIMAL;
            default -> FULL;
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
