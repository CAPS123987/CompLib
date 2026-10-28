package me.caps123987;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import me.caps123987.config.LComponentConfig;
import me.caps123987.database.DBManager;
import me.caps123987.design.DesignRules;
import me.caps123987.entities.User;
import me.caps123987.themes.CompLibDarkFlatLaf;
import me.caps123987.user.PasswordHasher;
import me.caps123987.window.CompLibWindow;
import me.caps123987.window.JWindowTests;
import me.caps123987.workers.FileWorker;
import me.caps123987.workers.JavaExecutor;
import me.caps123987.workers.LConfigProcessor;
import org.hibernate.Session;

import javax.swing.*;
import java.awt.*;

public class CompLib {
    public static final FileWorker FILE_WORKER = new FileWorker();

    public static final String SOURCE = """
                    import javax.swing.*;
                    import java.awt.FlowLayout;
                    import java.awt.Color;
                    import java.util.Map;
                    import java.util.HashMap;
                    import java.util.Random;
                    import me.caps123987.annotation.LConfig;
                    
                    public class CusBus extends JPanel{
                        Map<Integer,Integer> map = new HashMap<>();
                        
                        @LConfig
                        public Color c = Color.RED;
                        
                        @LConfig
                        public String kkt = "baf";
                        
                        public JLabel label = new JLabel();
                        public JButton button = new JButton("Change");
                        
                        public CusBus() {
                            this.setLayout(new FlowLayout());
                            label.setForeground(this.c);
                            label.setText("Caaau");
                            
                            this.add(label);
                            this.add(button);
                            
                            button.addActionListener(e -> {
                                Random random = new Random();
                                Color randomColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
                                this.setC(randomColor);
                            });
                        }
                        public void setKkt(String s) {
                            System.out.println(s);
                        }
                        public void setC(Color c) {
                            this.c = c;
                            label.setForeground(this.c);
                        }
                        public void t1(int i) {
                            map.put(i,i);
                        }
                        public void t2() {
                            System.out.println("t2");
                        }
                        public void t3() {
                            System.out.println("t3");
                        }
                        public void t4() {
                            System.out.println("t4");
                        }
                        public void t5() {
                            System.out.println("t5");
                        }
                    }
                    """;


    public static JavaExecutor EXECUTOR;
    public static LConfigProcessor CONFIG_PROCESSOR;
    public static DBManager DB_MANAGER;

    public static void main(String[] args) {
//        CompLibDarkFlatLaf.installProperty(null,"background","#323235");
//        FlatLaf.registerCustomDefaultsSource("me.caps123987.themes");

//        FlatDarkLaf.setup();
        FlatLaf.setGlobalExtraDefaults(DesignRules.toFlatLafExtraDefaults());
        FlatDarkLaf.setup();

        DesignRules.init();

        CompLibWindow compLibWindow = new CompLibWindow();
        compLibWindow.setVisible(true);

        DB_MANAGER = new DBManager();
        CONFIG_PROCESSOR = new LConfigProcessor();
        EXECUTOR = new JavaExecutor();

        DB_MANAGER.setupConnection();
        System.out.println("Hibernate setup successful!");

        Session session;
        try {
            session = DB_MANAGER.getSession();
            compLibWindow.updateConnection(true);
        } catch (Exception ignored) {
            compLibWindow.updateConnection(false);
            return;
        }


//        User user = UserFactory.createUser("admin", "admin", "admin");
//        Transaction transaction = session.beginTransaction();
//        session.persist(user);
//        transaction.commit();


        User adminAccount = session.createNamedSelectionQuery("User.findByUserName", User.class)
                .setParameter("name", "admin")
                .getSingleResult();

        System.out.println(adminAccount);

        System.out.println(PasswordHasher.validate("admin", adminAccount));
        System.out.println(PasswordHasher.validate("test", adminAccount));
        System.out.println(PasswordHasher.validate("yeaah", adminAccount));
        System.out.println(PasswordHasher.validate("Admin", adminAccount));



        if(true) return;


        try {
            System.out.println("Compiling...");
            long l = System.currentTimeMillis();

//            JComponent component = EXECUTOR.getJComponent(SOURCE);
//            FILE_WORKER.run();
//            component.getClass().getDeclaredMethod("t1",int.class).invoke(component,5);
//
//            LComponentConfig config = CONFIG_PROCESSOR.getComponentConfig(component);
//
//            System.out.println("Compiled in "+(System.currentTimeMillis()-l)/1000.0+"s");
//
//
//            JWindowTests window = new JWindowTests(component,config);
//            JLabel labelL = new JLabel();
//            labelL.setText("Label L");
//            labelL.setForeground(Color.WHITE);
////            window.add(labelL);
//
//            window.setVisible(true);
        } catch (Exception e) {
            FILE_WORKER.run();
            throw new RuntimeException(e);
        }


    }
}