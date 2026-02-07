package com.hidoni.transmog.config;

import com.hidoni.transmog.i18n.TranslationKeys;
import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

public enum TooltipDetailLevel implements StringRepresentable {
    NONE("none", TranslationKeys.TRANSMOG_CONFIG_TOOLTIP_OPTION_NONE),
    MINIMAL("minimal", TranslationKeys.TRANSMOG_CONFIG_TOOLTIP_OPTION_MINIMAL),
    FULL("full", TranslationKeys.TRANSMOG_CONFIG_TOOLTIP_OPTION_FULL);
    private final String serializedName;
    private final String translationKey;

    public static final Codec<TooltipDetailLevel> CODEC = StringRepresentable.fromEnum(TooltipDetailLevel::values);

    TooltipDetailLevel(String serializedName, String translationKey) {
        this.serializedName = serializedName;
        this.translationKey = translationKey;
    }

    @Override
    public @NonNull String getSerializedName() {
        return this.serializedName;
    }

    public static TooltipDetailLevel fromId(int id) {
        return switch (id) {
            case 0 -> NONE;
            case 1 -> MINIMAL;
            default -> FULL;
        };
    }

    public @NotNull String getKey() {
        return this.translationKey;
    }

    public @NotNull String getTooltipKey() {
        return this.translationKey + ".tooltip";
    }
}
