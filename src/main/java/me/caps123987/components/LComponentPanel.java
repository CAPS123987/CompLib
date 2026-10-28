package me.caps123987.components;

import me.caps123987.CompLib;
import me.caps123987.entities.LComponent;
import me.caps123987.workers.JavaExecutor;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class LComponentPanel extends JPanel {

    public LComponentPanel(LComponent selectedComponent) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new JLabel("Component Name: " + selectedComponent.getName()));
        JTextArea descriptionLabel = new JTextArea(selectedComponent.getDescription());

        descriptionLabel.setEditable(false);
        descriptionLabel.setOpaque(false);
        descriptionLabel.setFocusable(false);
        descriptionLabel.setBorder(null);

        descriptionLabel.setLineWrap(true);
        descriptionLabel.setWrapStyleWord(true);

        add(descriptionLabel);

        JPanel tagPanel = new JPanel(new FlowLayout());

        tagPanel.add(new JLabel("Tags: "));
        selectedComponent.getTags().forEach(tag -> {
            tagPanel.add(new TagComponent(tag.getUniquetag().getName()));
        });

        add(tagPanel);

        JPanel componentHolder = new RoundedPanel(new BorderLayout());

        componentHolder.add(new JLabel("Component Preview Loading..."), BorderLayout.NORTH);

        add(componentHolder);

        try {
            long time = System.nanoTime();
            CompLib.EXECUTOR.getJComponent(selectedComponent.getCode(), (component) -> {;
                System.out.println("Component reloaded");
                componentHolder.removeAll();
                componentHolder.add(component, BorderLayout.NORTH);
                revalidate();
                repaint();
            });
            time = System.nanoTime()-time;
            System.out.println("Component loaded in " + time/1000000 + "ms");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (JavaExecutor.JavaExecutorNoCompilerError e) {
            throw new RuntimeException(e);
        } catch (JavaExecutor.JavaExecutorClassSyntaxError e) {
            throw new RuntimeException(e);
        } catch (JavaExecutor.JavaExecutorCompilationError e) {
            throw new RuntimeException(e);
        }

    }
}
