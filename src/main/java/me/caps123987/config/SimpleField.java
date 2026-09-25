package me.caps123987.config;

import lombok.*;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@AllArgsConstructor
@RequiredArgsConstructor
public class SimpleField {
    @Getter
    @Setter
    @NonNull
    private Field field;
    @Getter
    @Setter
    @NonNull
    private Method setter;
    @Getter
    @Setter
    private JComponent instance;

    public Object getValue() {
        return getValue(instance);
    }

    public Object getValue(JComponent instance) {
        try {
            return field.get(instance);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    public void setValue(Object value) {
        setValue(instance, value);
    }

    public void setValue(JComponent instance, Object value) {
        try {
            setter.invoke(instance,value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return field.getName();
    }

    public Class<?> getType() {
        return field.getType();
    }

    @Override
    public String toString() {
        return "SimpleField{" +
                "field=" + field +
                ", setter=" + setter +
                ", instance " + (instance==null?"not present":"present")+
                '}';
    }
}
