package me.caps123987.workers;

import me.caps123987.CompLib;

import javax.swing.*;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

public class JavaExecutor {
    private static final int PUBLIC_CLASS_NAME_START_LENGTH = "public class ".length();

    public void getJComponent(String source, Consumer<JComponent> callback)
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

//        System.out.println("Class name: " + className);

        Thread compilerThread = new Thread(()->{

            try {

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

                ClassLoader loader = new URLClassLoader(new URL[]{dir.toUri().toURL()});
                Class<?> type = loader.loadClass(className);
                JComponent component = (JComponent) type.getConstructor().newInstance();

                callback.accept(component);

                CompLib.FILE_WORKER.removeFile(dir);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (JavaExecutorNoCompilerError e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JavaExecutorCompilationError e) {
                throw new RuntimeException(e);
            }


        });

        compilerThread.start();
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
