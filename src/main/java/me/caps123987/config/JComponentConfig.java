package me.caps123987.config;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
public class JComponentConfig {
    @NonNull
    @Getter
    @Setter
    private Map<String, SimpleField> simpleFieldMap;

    @Getter
    @Setter
    @NonNull
    private JComponent instance;

    public SimpleField getSimpleField(String name) {
        return simpleFieldMap.get(name);
    }

    public List<SimpleField> getAllSimpleFields() {
        return simpleFieldMap.values().stream().toList();
    }
}
