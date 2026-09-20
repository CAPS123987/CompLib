package me.caps123987.design;

import lombok.Data;
import lombok.NonNull;

import java.awt.*;

public class DesignRules {
    public static final Color BACKGROUND = new Color(0x3F3F42);
    public static final Color LIFTED_BACKGROUND = BACKGROUND.brighter();
    public static final Color TEXT = Color.WHITE;
    public static final Color ADDITIVE = new Color(0x2161D8);
    public static final Color DESCRUCTIVE = new Color(0xDA2C2C);
    public static final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 14);


    @Data
    public static class Button {
        public static DesignRules.Button DEFAULT = new DesignRules.Button(
                LIFTED_BACKGROUND,
                TEXT,
                LIFTED_BACKGROUND.brighter(),
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
                16,
                12
        );

        public static DesignRules.Button DESCTRUCTIVE = new DesignRules.Button(
                DESCRUCTIVE,
                TEXT,
                DESCRUCTIVE.darker(),
                DEFAULT_FONT,
                24,
                2,
                16,
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
