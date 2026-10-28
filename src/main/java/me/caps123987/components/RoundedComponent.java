package me.caps123987.components;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.caps123987.design.DesignRules;

import javax.swing.*;
import java.awt.*;

public interface RoundedComponent<J extends DesignRules.Rounded> {
    default void updateDesign(J design) {
        JComponent component = (JComponent) this;
        component.setBackground(design.getBackgroundColor());
        component.setForeground(design.getForegroundColor());
        component.setFont(design.getFont());

        setBorderColor(design.getBorderColor());
        setRoundness(design.getRadius());
        setLineWidth(design.getLineWidth());

        Dimension minimumSize = component.getMinimumSize();
        component.setPreferredSize(new Dimension(minimumSize.width + design.getTextPaddingX() * 2,
                minimumSize.height + design.getTextPaddingY() * 2));
    }

    void setLineWidth(int lineWidth);

    void setRoundness(int radius);

    void setBorderColor(@NonNull Color borderColor);

    default void updateDesign() {
        updateDesign(getDesign());
    }
    J getDesign();
}
