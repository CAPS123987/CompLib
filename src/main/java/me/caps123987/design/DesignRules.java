package me.caps123987.design;


import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

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
    public static final Color WARNING = new Color(0xDA6F2C);
//    public static final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 14);
    public static final int DEFAULT_GAP = 5;
    public static Font DEFAULT_FONT;
    public static Font H1_FONT;
    public static Font H2_FONT;

    public static void init() {
        DEFAULT_FONT = UIManager.getFont("semibold.font");
        H1_FONT = UIManager.getFont("h1.font");
        H2_FONT = UIManager.getFont("h2.font");
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


    @SuperBuilder(toBuilder=true)
    public static class Button extends DesignRules.Rounded{
        public static DesignRules.Button NEUTRAL = Button.builder()
                .backgroundColor(DesignRules.NEUTRAL)
                .foregroundColor(DesignRules.TEXT)
                .borderColor(DesignRules.NEUTRAL.brighter())
                .font(DEFAULT_FONT)
                .radius(10)
                .lineWidth(2)
                .textPaddingX(12)
                .textPaddingY(6)
                .build();

        public static DesignRules.Button ADDITVE = Button.builder()
                .backgroundColor(DesignRules.ADDITIVE)
                .foregroundColor(DesignRules.TEXT)
                .borderColor(DesignRules.ADDITIVE.darker())
                .font(DEFAULT_FONT)
                .radius(10)
                .lineWidth(2)
                .textPaddingX(12)
                .textPaddingY(6)
                .build();

        public static DesignRules.Button DESCTRUCTIVE = Button.builder()
                .backgroundColor(DesignRules.DESCRUCTIVE)
                .foregroundColor(DesignRules.TEXT)
                .borderColor(DesignRules.DESCRUCTIVE.darker())
                .font(DEFAULT_FONT)
                .radius(10)
                .lineWidth(2)
                .textPaddingX(12)
                .textPaddingY(6)
                .build();
    }


    @SuperBuilder(toBuilder=true)
    public static class Tag extends DesignRules.Rounded{
        public static DesignRules.Tag NEUTRAL = Tag.builder()
                .backgroundColor(DesignRules.WARNING.darker())
                .foregroundColor(TEXT)
                .borderColor(DesignRules.WARNING.brighter())
                .font(DEFAULT_FONT)
                .radius(24)
                .lineWidth(1)
                .textPaddingX(12)
                .textPaddingY(4)
                .build();
        public static DesignRules.Tag DEFAULT = NEUTRAL;
    }


    @SuperBuilder(toBuilder=true)
    public static class Panel extends DesignRules.Rounded{
        public static DesignRules.Panel NEUTRAL = DesignRules.Panel.builder()
                .backgroundColor(DesignRules.NEUTRAL.darker())
                .foregroundColor(TEXT)
                .borderColor(DesignRules.NEUTRAL.brighter())
                .font(DEFAULT_FONT)
                .radius(24)
                .lineWidth(1)
                .textPaddingX(12)
                .textPaddingY(4)
                .build();
        public static DesignRules.Panel DEFAULT = NEUTRAL;
    }


    @Data
    @SuperBuilder(toBuilder = true)
    public static abstract class Rounded {
        @NonNull
        public Color backgroundColor;
        @NonNull
        public Color foregroundColor;
        @NonNull
        public Color borderColor;
        @NonNull
        public Font font;


        public int radius;
        public int lineWidth;
        public int textPaddingX;
        public int textPaddingY;
    }
}
