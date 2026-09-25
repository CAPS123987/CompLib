package me.caps123987.workers;

import me.caps123987.config.JComponentConfig;
import me.caps123987.config.SimpleField;

import javax.swing.*;
import java.lang.reflect.AccessFlag;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LConfigProcessor {
    public JComponentConfig getComponentConfig(JComponent component)
            throws LConfigProcessorFieldAccessError, LConfigProcessorFieldSetterNotFoundError {
        Class<?> clazz = component.getClass();

        Map<String, SimpleField> simpleFieldMap = new HashMap<>();

        for (Field f : clazz.getDeclaredFields()) {
            if(f.isAnnotationPresent(me.caps123987.annotation.LConfig.class)) {
                if(f.accessFlags().contains(AccessFlag.PRIVATE)) {
                    throw new LConfigProcessorFieldAccessError("Field " + f.getName() + " in " + clazz.getName()+" class has PRIVATE modifier");
                }

                if(f.accessFlags().contains(AccessFlag.PROTECTED)) {
                    throw new LConfigProcessorFieldAccessError("Field " + f.getName() + " in " + clazz.getName()+" class has PROTECTED modifier");
                }

                if(f.accessFlags().contains(AccessFlag.FINAL)) {
                    throw new LConfigProcessorFieldAccessError("Field " + f.getName() + " in " + clazz.getName()+" class has FINAL modifier");
                }


                //find setter
                Method setterMethod;
                try {
                    setterMethod = clazz.getMethod("set"+f.getName().substring(0,1).toUpperCase()+f.getName().substring(1), f.getType());
                } catch (NoSuchMethodException e) {
                    throw new LConfigProcessorFieldSetterNotFoundError("Setter method for field " + f.getName() + " in " + clazz.getName()+" class not found");
                }

                SimpleField simpleField = new SimpleField(f,setterMethod,component);
                simpleFieldMap.put(simpleField.getName(),simpleField);
            }
        }

        System.out.println(simpleFieldMap);

        return new JComponentConfig(simpleFieldMap, component);
    }

    public static class LConfigProcessorException extends Exception {
        public LConfigProcessorException(String message) {
            super(message);
        }
    }

    public static class LConfigProcessorFieldAccessError extends Exception {
        public LConfigProcessorFieldAccessError(String message) {
            super(message);
        }
    }

    public static class LConfigProcessorFieldSetterNotFoundError extends Exception {
        public LConfigProcessorFieldSetterNotFoundError(String message) {
            super(message);
        }
    }
}
