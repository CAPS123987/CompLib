package me.caps123987.components;

import lombok.Getter;
import lombok.Setter;
import me.caps123987.design.DesignRules;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedPanel extends JPanel implements RoundedComponent<DesignRules.Panel> {
    @Getter
    @Setter
    DesignRules.Panel design;
    @Getter
    @Setter
    private int roundness;
    @Getter
    @Setter
    private Color borderColor;
    @Getter
    @Setter
    private int lineWidth;

    public RoundedPanel(DesignRules.Panel design, LayoutManager layoutManager) {
        super(layoutManager);

        setDesign(design);

        setOpaque(false);

        updateDesign();
    }

    public RoundedPanel(DesignRules.Panel panelDesign) {
        setDesign(panelDesign);

        setOpaque(false);

        updateDesign();
    }

    public RoundedPanel(LayoutManager layoutManager) {
        setLayout(layoutManager);

        setDesign(DesignRules.Panel.DEFAULT);

        setOpaque(false);

        updateDesign();
    }

    public RoundedPanel() {
        setDesign(DesignRules.Panel.DEFAULT);

        setOpaque(false);

        updateDesign();
    }

    public void updateDesign(DesignRules.Panel buttonDesign) {
        RoundedComponent.super.updateDesign(buttonDesign);
        setPreferredSize(null);
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

//        super.paintComponent(g);

        g2.dispose();
    }
}
