package me.caps123987;

import me.caps123987.config.JComponentConfig;
import me.caps123987.window.JWindow;
import me.caps123987.workers.FileWorker;
import me.caps123987.workers.JavaExecutor;
import me.caps123987.workers.LConfigProcessor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompLib {
    public static final FileWorker FILE_WORKER = new FileWorker();

    public static final String SOURCE = """
                    import javax.swing.*;
                    import java.awt.Color;
                    import java.util.Map;
                    import java.util.HashMap;
                    import java.util.Random;
                    import me.caps123987.annotation.LConfig;
                    
                    public class CusBus extends JLabel{
                        Map<Integer,Integer> map = new HashMap<>();
                        
                        @LConfig
                        public Color c = Color.RED;
                        
                        public CusBus() {
                            this.setForeground(this.c);
                            
                            this.setText("Caaau");
                            
                    //      setBounds(100,100,100,100);

                    //        Random r = new Random();
                    //        System.out.println("CusBus created");
                    //        
                    //        for (int i = 0; i < 99999; i++) {
                    //            map.put(r.nextInt(0,Integer.MAX_VALUE),r.nextInt(0,Integer.MAX_VALUE));
                    //        }
                        }
                        public void setC(Color c) {
                            this.c = c;
                            this.setForeground(this.c);
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
    public static void main(String[] args) throws InterruptedException {
        List<JComponent> components = new ArrayList<>();


        JavaExecutor executor = new JavaExecutor();
        LConfigProcessor processor = new LConfigProcessor();
        try {
            System.out.println("Compiling...");
            long l = System.currentTimeMillis();

            JComponent component = executor.getJComponent(SOURCE);
            components.add(component);
            FILE_WORKER.run();
            component.getClass().getDeclaredMethod("t1",int.class).invoke(component,5);

            JComponentConfig config = processor.getComponentConfig(component);

            System.out.println("Compiled in "+(System.currentTimeMillis()-l)/1000.0+"s");


            JWindow window = new JWindow(component,config);
            JLabel labelL = new JLabel();
            labelL.setText("Label L");
            labelL.setForeground(Color.WHITE);
//            window.add(labelL);

            window.setVisible(true);
        } catch (Exception e) {
            FILE_WORKER.run();
            throw new RuntimeException(e);
        }


    }
}