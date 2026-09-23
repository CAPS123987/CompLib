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
    @Getter
    @Setter
    @NonNull
    private List<Field> fields;
    @Getter
    @Setter
    @NonNull
    private List<Method> fieldSetters;
    @Getter
    @Setter
    @NonNull
    private JComponent instance;


}
