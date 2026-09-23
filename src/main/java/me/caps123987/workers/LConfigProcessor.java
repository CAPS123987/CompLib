package me.caps123987.workers;

import me.caps123987.config.JComponentConfig;

import javax.swing.*;
import java.lang.reflect.AccessFlag;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class LConfigProcessor {
    public JComponentConfig getComponentConfig(JComponent component)
            throws LConfigProcessorVariableAccessError {
        Class<?> clazz = component.getClass();

        List<Field> fields = new ArrayList<>();

        for (Field f : clazz.getDeclaredFields()) {
            if(f.isAnnotationPresent(me.caps123987.annotation.LConfig.class)) {
                if(f.accessFlags().contains(AccessFlag.PRIVATE)) {
                    throw new LConfigProcessorVariableAccessError("Field " + f.getName() + " in " + clazz.getName()+" class has PRIVATE modifier");
                }

                if(f.accessFlags().contains(AccessFlag.PROTECTED)) {
                    throw new LConfigProcessorVariableAccessError("Field " + f.getName() + " in " + clazz.getName()+" class has PROTECTED modifier");
                }

                if(f.accessFlags().contains(AccessFlag.FINAL)) {
                    throw new LConfigProcessorVariableAccessError("Field " + f.getName() + " in " + clazz.getName()+" class has FINAL modifier");
                }

                fields.add(f);
            }
        }

        System.out.println(fields);

        return new JComponentConfig(fields, component);
    }

    public static class LConfigProcessorException extends Exception {
        public LConfigProcessorException(String message) {
            super(message);
        }
    }

    public static class LConfigProcessorVariableAccessError extends Exception {
        public LConfigProcessorVariableAccessError(String message) {
            super(message);
        }
    }
}
