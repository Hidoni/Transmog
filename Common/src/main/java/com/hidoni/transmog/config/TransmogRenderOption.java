package com.hidoni.transmog.config;

import com.hidoni.transmog.i18n.TranslationKeys;
import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

public enum TransmogRenderOption implements StringRepresentable {
    OFF("off", TranslationKeys.TRANSMOG_CONFIG_RENDER_OPTION_OFF, false, false),
    IN_WORLD("in_world", TranslationKeys.TRANSMOG_CONFIG_RENDER_OPTION_IN_WORLD, true, false),
    EVERYWHERE("everywhere", TranslationKeys.TRANSMOG_CONFIG_RENDER_OPTION_EVERYWHERE, true, true);

    public static final Codec<TransmogRenderOption> CODEC = StringRepresentable.fromEnum(TransmogRenderOption::values);

    private final String serializedName;
    private final String translationKey;
    public final boolean renderInWorld;
    public final boolean renderInInventory;

    TransmogRenderOption(String serializedName, String translationKey, boolean renderInWorld, boolean renderInInventory) {
        this.serializedName = serializedName;
        this.translationKey = translationKey;
        this.renderInWorld = renderInWorld;
        this.renderInInventory = renderInInventory;
    }

    @Override
    public @NonNull String getSerializedName() {
        return this.serializedName;
    }

    public @NotNull String getKey() {
        return this.translationKey;
    }

    public @NotNull String getTooltipKey() {
        return this.translationKey + ".tooltip";
    }
}
