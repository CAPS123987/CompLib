package me.caps123987.window;

import me.caps123987.components.RoundBtn;
import me.caps123987.design.DesignRules;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class JWindow extends JFrame {
    JPanel backgroundPanel;
    int picovina = 55;

    public JWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setTitle("Absolutni GUI");

        System.out.println(picovina);
        try {
            this.getClass().getDeclaredField("picovina").set(this,9);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        System.out.println(picovina);

        backgroundPanel = new JPanel();

        backgroundPanel.setBackground(DesignRules.BACKGROUND);

        backgroundPanel.setLayout(new FlowLayout());

        RoundBtn btn = new RoundBtn(DesignRules.Button.NEUTRAL, "Hello mother guy");

        btn.addActionListener(e -> {

            Color selectedColor = JColorChooser.showDialog(
                    btn, "Color bitte", btn.getBackground(), false
            );
            // If the user didn't cancel, change the button color
            if (selectedColor != null) {
                btn.setBackground(selectedColor);
                btn.setBorderColor(selectedColor.brighter());
            }
        });

        RoundBtn btn2 = new RoundBtn(DesignRules.Button.ADDITVE, "Přihlásit");

        RoundBtn btn3 = new RoundBtn(DesignRules.Button.DESCTRUCTIVE, "Odhlásit");

        RoundBtn btn4 = new RoundBtn(DesignRules.Button.NEUTRAL.toBuilder().textPaddingX(50).build(), "Sem edited neutrál :)");

        btn4.addActionListener(e -> {
            btn4.setText("A ted edited Additive");
            btn4.setButtonDesign(DesignRules.Button.ADDITVE.toBuilder()
                    .borderColor(new Color(new Random().nextInt(0,255),
                            new Random().nextInt(0,255),
                    new Random().nextInt(0,255)))
                    .textPaddingX(50).build());
            btn4.updateButtonDesign();

        });

        backgroundPanel.add(btn);
        backgroundPanel.add(btn2);
        backgroundPanel.add(btn3);
        backgroundPanel.add(btn4);

        add(backgroundPanel);
    }
}
