package me.caps123987.window;

import com.formdev.flatlaf.FlatClientProperties;
import me.caps123987.CompLib;
import me.caps123987.components.LComponentPanel;
import me.caps123987.components.RoundedButton;
import me.caps123987.components.RoundedPanel;
import me.caps123987.components.SearchResultComponent;
import me.caps123987.design.DesignRules;
import me.caps123987.entities.LComponent;
import me.caps123987.workers.JavaExecutor;
import org.hibernate.Session;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class CompLibWindow extends JFrame {

    private final JPanel toolBarPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, DesignRules.DEFAULT_GAP, DesignRules.DEFAULT_GAP));
    private final JButton addComponentBtn = new RoundedButton(DesignRules.Button.ADDITVE,"Add Component");
    private final JButton signInBtn = new RoundedButton(DesignRules.Button.ADDITVE,"Sign In");
    private JLabel loadingLabel = new JLabel("Connecting to database...");

    private final JPanel searchHolderPanel = new JPanel(new BorderLayout());
    private final JTextField searchField = new JTextField();
    private final JButton searchButton = new RoundedButton(DesignRules.Button.ADDITVE,"Search");;

    private final JPanel searchOutputHolderPanel = new JPanel();

    private final JPanel mainPanel = new RoundedPanel(new BorderLayout());


    public LComponent selectedComponent;

    public CompLibWindow() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setTitle("CompLib v0.0.1");

        initMainLayout();

        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loadingLabel.setFont(DesignRules.H1_FONT);
//        add(loadingLabel, BorderLayout.CENTER);

        mainPanel.add(loadingLabel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
    }

    public void initMainLayout() {
        setupToolBarPanel();
        add(getToolBarPanel(), BorderLayout.NORTH);

        setupSearchHolderPanel();
        add(getSearchHolderPanel(), BorderLayout.WEST);
    }

    private void setupToolBarPanel() {
        toolBarPanel.add(addComponentBtn);
        toolBarPanel.add(signInBtn);
    }
    private JPanel getToolBarPanel() {
        return toolBarPanel;
    }
    public void setupSearchHolderPanel() {
        JPanel searchFieldHolder = new JPanel(new FlowLayout(FlowLayout.LEFT, DesignRules.DEFAULT_GAP, DesignRules.DEFAULT_GAP));

        searchField.setPreferredSize(new Dimension(150, 25));
        searchField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Search");

        searchFieldHolder.add(searchField);
        searchFieldHolder.add(searchButton);
        
        searchOutputHolderPanel.setLayout(new BoxLayout(searchOutputHolderPanel, BoxLayout.Y_AXIS));

        searchHolderPanel.add(searchFieldHolder, BorderLayout.NORTH);
        searchHolderPanel.add(searchOutputHolderPanel, BorderLayout.CENTER);
    }
    private JPanel getSearchHolderPanel() {
//        JPanel searchHolderBorderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, DesignRules.DEFAULT_GAP, DesignRules.DEFAULT_GAP));
//        searchHolderBorderPanel.add(searchHolderPanel);
        return searchHolderPanel;
    }

    public void updateConnection(boolean success) {
        mainPanel.remove(loadingLabel);

        if(success) {
            Session session = CompLib.DB_MANAGER.getSession();

            session.createQuery("FROM LComponent ", LComponent.class)
                    .getResultList()
                    .forEach(component -> {
                        searchOutputHolderPanel.add(new SearchResultComponent(component, this));
                    });
        } else {
            searchOutputHolderPanel.add(new JLabel("Connection was not successful. Please check your database connection settings."));
            searchOutputHolderPanel.add(new JLabel("Search results will be displayed here."));
            searchOutputHolderPanel.add(new JLabel("Search results will be displayed here."));
            searchOutputHolderPanel.add(new JLabel("Search results will be displayed here."));
        }

        revalidate();
        repaint();
    }

    public void renderComponent(LComponent selectedComponent) {
        this.selectedComponent = selectedComponent;

        if (mainPanel.getComponentCount() > 0) {
            mainPanel.removeAll();
        }

        LComponentPanel componentPanel = new LComponentPanel(selectedComponent);
        mainPanel.add(componentPanel, BorderLayout.CENTER);

        System.out.println("Rendering component: " + selectedComponent.getName());
        revalidate();
        repaint();
    }
}
