package me.caps123987.components;

import lombok.Getter;
import lombok.Setter;
import me.caps123987.design.DesignRules;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class TagComponent extends JLabel implements RoundedComponent<DesignRules.Tag>{

    @Getter
    @Setter
    DesignRules.Tag design;
    @Getter
    @Setter
    private int roundness;
    @Getter
    @Setter
    private Color borderColor;
    @Getter
    @Setter
    private int lineWidth;

    public TagComponent(DesignRules.Tag design, String text) {
        setText(text);
        setHorizontalAlignment(SwingConstants.CENTER);

        setDesign(design);

        updateDesign();
    }

    public TagComponent(String text) {
        this(DesignRules.Tag.DEFAULT, text);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);


        g2.setColor(getBackground());


        //pozadi
        int lineWidth = getLineWidth();
        g2.fill(new RoundRectangle2D.Double(lineWidth, lineWidth, getWidth() - lineWidth*2, getHeight() - lineWidth*2, roundness + lineWidth, roundness + lineWidth));


        g2.setColor(getBorderColor());

        //outline
        g2.setStroke(new BasicStroke(lineWidth));
        g2.draw(new RoundRectangle2D.Double(lineWidth, lineWidth, getWidth() - (lineWidth * 2), getHeight() - (lineWidth * 2), roundness, roundness));

        super.paintComponent(g);

        g2.dispose();
    }
}
