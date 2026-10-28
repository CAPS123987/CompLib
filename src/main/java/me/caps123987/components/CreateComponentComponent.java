package me.caps123987.components;

import me.caps123987.CompLib;
import me.caps123987.design.DesignRules;
import me.caps123987.entities.LComponent;
import me.caps123987.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CreateComponentComponent extends JPanel {
    JTextField ownerField = new JTextField("",1);
    JTextField nameField = new JTextField("",1);
    JTextArea codeField = new JTextArea(6, 30);
    JTextArea descriptionField = new JTextArea(6, 30);
    public CreateComponentComponent() {
        setLayout(new BorderLayout());

        JPanel fields = new JPanel(new GridLayout(4,2,2,2));

        RoundedButton submitBtn = new RoundedButton(DesignRules.Button.NEUTRAL, "Submit");

        JScrollPane scrollPaneCode = new JScrollPane(codeField);
//        scrollPaneCode.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JScrollPane scrollPaneDescription = new JScrollPane(descriptionField);
//        scrollPaneDescription.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        fields.add(new JLabel("Component Name:"));
        fields.add(nameField);

        fields.add(new JLabel("Component Code:"));
        fields.add(scrollPaneCode);

        fields.add(new JLabel("Component Description:"));
        fields.add(scrollPaneDescription);

        fields.add(new JLabel("Owner Id:"));
        fields.add(ownerField);

        add(fields,BorderLayout.CENTER);
        JPanel submitPanel = new JPanel(new FlowLayout());
        submitPanel.add(submitBtn);
        add(submitPanel,BorderLayout.SOUTH);

        submitBtn.addActionListener(this::submit);
    }

    public void submit(ActionEvent e) {
        Session session = CompLib.DB_MANAGER.getSession();

        Transaction transaction = session.beginTransaction();

        LComponent component = new LComponent();
        component.setName(nameField.getText());
        component.setCode(codeField.getText());
        component.setDescription(descriptionField.getText());
        component.setOwner(session.find(User.class, Integer.parseInt(ownerField.getText())));
        component.setImage(new byte[0]);

        session.persist(component);

        transaction.commit();

    }
}
