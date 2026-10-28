package me.caps123987.components;

import me.caps123987.design.DesignRules;
import me.caps123987.entities.LComponent;

import javax.swing.*;
import java.awt.*;

public class SearchResultComponent extends RoundedPanel {
    LComponent lComponent;

    public SearchResultComponent(LComponent lComponent) {
        super(DesignRules.Panel.NEUTRAL, new BorderLayout(DesignRules.DEFAULT_GAP, DesignRules.DEFAULT_GAP));

//        setLayout(new BorderLayout(DesignRules.DEFAULT_GAP, DesignRules.DEFAULT_GAP));
        this.lComponent = lComponent;

        setBorder(BorderFactory.createEmptyBorder(
                DesignRules.DEFAULT_GAP*2,
                DesignRules.DEFAULT_GAP*2,
                DesignRules.DEFAULT_GAP*2,
                DesignRules.DEFAULT_GAP*2));

        JLabel nameLabel = new JLabel(lComponent.getName());
        nameLabel.setFont(DesignRules.H2_FONT);
        add(nameLabel, BorderLayout.NORTH);


        JTextArea descriptionLabel = new JTextArea(lComponent.getDescription());

        descriptionLabel.setEditable(false);
        descriptionLabel.setOpaque(false);
        descriptionLabel.setFocusable(false);
        descriptionLabel.setBorder(null);

        descriptionLabel.setLineWrap(true);
        descriptionLabel.setWrapStyleWord(true);


        add(descriptionLabel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout(DesignRules.DEFAULT_GAP, DesignRules.DEFAULT_GAP));
        JPanel tagsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));


        lComponent.getTags().forEach(tag -> {
            tagsPanel.add(new TagComponent(DesignRules.Tag.NEUTRAL, tag.getUniquetag().getName()));
        });

        JButton seeComponentButtom = new RoundedButton(DesignRules.Button.NEUTRAL, "See Component");

        bottomPanel.add(tagsPanel, BorderLayout.NORTH);
        bottomPanel.add(seeComponentButtom, BorderLayout.SOUTH);


        add(bottomPanel, BorderLayout.SOUTH);
//        int prirozenaVyska = getPreferredSize().height;
//        setMaximumSize(new Dimension(Integer.MAX_VALUE, prirozenaVyska));
    }

    @Override
    public Dimension getMaximumSize() {
        // Šířka se natáhne podle rodiče, výška se vždy drží preferované velikosti obsahu
        return new Dimension(Integer.MAX_VALUE, getPreferredSize().height);
    }
}
