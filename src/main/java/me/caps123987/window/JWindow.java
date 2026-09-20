package me.caps123987.window;

import me.caps123987.components.RoundBtn;
import me.caps123987.design.DesignRules;

import javax.swing.*;
import java.awt.*;

public class JWindow extends JFrame {
    JPanel backgroundPanel;

    public JWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setTitle("Absolutni GUI");

        backgroundPanel = new JPanel();

        backgroundPanel.setBackground(DesignRules.BACKGROUND);

        backgroundPanel.setLayout(new FlowLayout());

        RoundBtn btn = new RoundBtn(DesignRules.Button.DEFAULT, "Hello mother guy");

        RoundBtn btn2 = new RoundBtn(DesignRules.Button.ADDITVE, "Přihlásit");

        RoundBtn btn3 = new RoundBtn(DesignRules.Button.DESCTRUCTIVE, "Odhlásit");

        backgroundPanel.add(btn);
        backgroundPanel.add(btn2);
        backgroundPanel.add(btn3);

        add(backgroundPanel);
    }
}
