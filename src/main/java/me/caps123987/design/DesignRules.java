package me.caps123987.design;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class DesignRules {
    public static final Color BACKGROUND = new Color(0x232324);
    public static final Color TEXT = Color.WHITE;
    public static final Color NEUTRAL = new Color(0x323235);
    public static final Color ADDITIVE = new Color(0x2161D8);
    public static final Color DESCRUCTIVE = new Color(0xDA2C2C);
//    public static final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 14);
    public static final int DEFAULT_GAP = 5;
    public static Font DEFAULT_FONT;
    public static Font H1_FONT;

    public static void init() {
        DEFAULT_FONT = UIManager.getFont("semibold.font");
        H1_FONT = UIManager.getFont("h1.font");
    }
    public static Map<String, String> toFlatLafExtraDefaults() {
        Map<String, String> defaults = new LinkedHashMap<>();
        defaults.put("@background", toHex(BACKGROUND));
        defaults.put("@foreground", toHex(TEXT));
        defaults.put("TextComponent.arc", "10");
        return defaults;
    }

    private static String toHex(Color color) {
        return String.format("#%06X", color.getRGB() & 0xFFFFFF);
    }

    @Data
    @Builder(toBuilder=true)
    public static class Button {
        public static DesignRules.Button NEUTRAL = new DesignRules.Button(
                DesignRules.NEUTRAL,
                TEXT,
                DesignRules.NEUTRAL.brighter(),
                DEFAULT_FONT,
                20,
                2,
                12,
                6
        );

        public static DesignRules.Button ADDITVE = new DesignRules.Button(
                ADDITIVE,
                TEXT,
                ADDITIVE.darker(),
                DEFAULT_FONT,
                10,
                2,
                12,
                6
        );

        public static DesignRules.Button DESCTRUCTIVE = new DesignRules.Button(
                DESCRUCTIVE,
                TEXT,
                DESCRUCTIVE.darker(),
                DEFAULT_FONT,
                20,
                2,
                12,
                6
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
