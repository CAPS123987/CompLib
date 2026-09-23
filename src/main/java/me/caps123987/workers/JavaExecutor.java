package me.caps123987.workers;

import me.caps123987.CompLib;

import javax.swing.*;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;

public class JavaExecutor {
    private static final int PUBLIC_CLASS_NAME_START_LENGTH = "public class ".length();

    public JComponent getJComponent(String source)
            throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException, JavaExecutorNoCompilerError,  JavaExecutorClassSyntaxError, JavaExecutorCompilationError {
        //vytvořit file odpovídající třídě
        int classStart = source.indexOf("public class ");
        if(classStart == -1) {
            throw new JavaExecutorClassSyntaxError("No public class found in source, syntax must be exactly: 'public class ClassName'");
        }
        classStart = PUBLIC_CLASS_NAME_START_LENGTH + classStart;

        int classNameEndSpace = source.substring(classStart).indexOf(" ");
        int classNameEndBracket = source.substring(classStart).indexOf("{");

        int classNameEnd = Math.min(classNameEndSpace, classNameEndBracket);


        String className = source.substring(classStart, classNameEnd+classStart);

        System.out.println("Class name: " + className);

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        if(compiler == null) {
            throw new JavaExecutorNoCompilerError("No Java compiler available. Make sure to run this with a JDK, not a JRE.");
        }

        Path dir = Files.createTempDirectory("CompLibJava");
        Path file = dir.resolve(className+".java");
        Files.writeString(file, source);

        int result = compiler
                .run(null, null, null, file.toString());
        if (result != 0) {
            CompLib.FILE_WORKER.removeFile(dir);

            throw new JavaExecutorCompilationError("The source did not compile");
        }

        // 3. load the class from that folder and create one
        ClassLoader loader = new URLClassLoader(new URL[] { dir.toUri().toURL() });
        Class<?> type = loader.loadClass(className);
        JComponent button = (JComponent) type.getConstructor().newInstance();

        CompLib.FILE_WORKER.removeFile(dir);

        return button;
    }
    public static class JavaExecutorException extends Exception {
        public JavaExecutorException(String message) {
            super(message);
        }
    }

    public static class JavaExecutorClassSyntaxError extends JavaExecutorException {
        public JavaExecutorClassSyntaxError(String message) {
            super(message);
        }
    }

    public static class JavaExecutorNoCompilerError extends JavaExecutorException {
        public JavaExecutorNoCompilerError(String message) {
            super(message);
        }
    }

    public static class JavaExecutorCompilationError extends JavaExecutorException {
        public JavaExecutorCompilationError(String message) {
            super(message);
        }
    }
}
