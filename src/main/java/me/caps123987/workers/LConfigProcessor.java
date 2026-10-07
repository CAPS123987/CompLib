package me.caps123987.workers;

import me.caps123987.config.JComponentConfig;
import me.caps123987.config.SimpleField;

import javax.swing.*;
import java.lang.reflect.AccessFlag;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class LConfigProcessor {
    public JComponentConfig getComponentConfig(JComponent component)
            throws LConfigProcessorFieldAccessException, LConfigProcessorFieldSetterNotFoundException {
        Class<?> clazz = component.getClass();

        Map<String, SimpleField> simpleFieldMap = new HashMap<>();

        for (Field f : clazz.getDeclaredFields()) {
            if(f.isAnnotationPresent(me.caps123987.annotation.LConfig.class)) {

                if(f.accessFlags().contains(AccessFlag.PRIVATE)) {
                    throw new LConfigProcessorFieldAccessException("Field " + f.getName() + " in " + clazz.getName()+" class has PRIVATE modifier");
                }

                if(f.accessFlags().contains(AccessFlag.PROTECTED)) {
                    throw new LConfigProcessorFieldAccessException("Field " + f.getName() + " in " + clazz.getName()+" class has PROTECTED modifier");
                }

                if(f.accessFlags().contains(AccessFlag.FINAL)) {
                    throw new LConfigProcessorFieldAccessException("Field " + f.getName() + " in " + clazz.getName()+" class has FINAL modifier");
                }


                //find setter
                SimpleField simpleField = getSimpleField(component, f, clazz);
                simpleFieldMap.put(simpleField.getName(),simpleField);
            }
        }

        System.out.println(simpleFieldMap);

        return new JComponentConfig(simpleFieldMap, component);
    }

    private static SimpleField getSimpleField(JComponent component, Field f, Class<?> clazz) throws LConfigProcessorFieldSetterNotFoundException {
        Method setterMethod;

        try {
            String setterName = "set"+ f.getName().substring(0,1).toUpperCase()+ f.getName().substring(1);
            setterMethod = clazz.getMethod(setterName, f.getType());
        } catch (NoSuchMethodException e) {
            throw new LConfigProcessorFieldSetterNotFoundException("Setter method for field " + f.getName() + " in " + clazz.getName()+" class not found");
        }

        return new SimpleField(f,setterMethod, component);
    }

    public static class LConfigProcessorException extends Exception {
        public LConfigProcessorException(String message) {
            super(message);
        }
    }

    public static class LConfigProcessorFieldAccessException extends LConfigProcessorException {
        public LConfigProcessorFieldAccessException(String message) {
            super(message);
        }
    }

    public static class LConfigProcessorFieldSetterNotFoundException extends LConfigProcessorException {
        public LConfigProcessorFieldSetterNotFoundException(String message) {
            super(message);
        }
    }
}
