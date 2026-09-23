package me.caps123987.config;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JComponentConfig {
    @Getter
    @Setter
    private List<Field> fields;
    @Getter
    @Setter
    private JComponent instance;

    public JComponentConfig(List<Field> fields, JComponent instance) {
        this.fields = fields;
        this.instance = instance;
    }


}
