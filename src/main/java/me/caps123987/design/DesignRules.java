package me.caps123987.design;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.awt.*;

public class DesignRules {
    public static final Color BACKGROUND = new Color(0x232324);
    public static final Color TEXT = Color.WHITE;
    public static final Color NEUTRAL = new Color(0x323235);
    public static final Color ADDITIVE = new Color(0x2161D8);
    public static final Color DESCRUCTIVE = new Color(0xDA2C2C);
    public static final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 14);


    @Data
    @Builder(toBuilder=true)
    public static class Button {
        public static DesignRules.Button NEUTRAL = new DesignRules.Button(
                DesignRules.NEUTRAL,
                TEXT,
                DesignRules.NEUTRAL.brighter(),
                DEFAULT_FONT,
                24,
                2,
                16,
                12
        );

        public static DesignRules.Button ADDITVE = new DesignRules.Button(
                ADDITIVE,
                TEXT,
                ADDITIVE.darker(),
                DEFAULT_FONT,
                24,
                2,
                20,
                12
        );

        public static DesignRules.Button DESCTRUCTIVE = new DesignRules.Button(
                DESCRUCTIVE,
                TEXT,
                DESCRUCTIVE.darker(),
                DEFAULT_FONT,
                24,
                2,
                20,
                12
        );

        @NonNull
        public Color backgroundColor;
        @NonNull
        public Color foregroundColor;
        @NonNull
        public Color borderColor;
        @NonNull
        public Font font;
        @NonNull
        public int radius;
        @NonNull
        public int lineWidth;
        @NonNull
        public int textPaddingX;
        @NonNull
        public int textPaddingY;
    }
}
