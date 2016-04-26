package com.obsidiskrivemaskine.GUI;

import com.obsidiskrivemaskine.Test;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.lang.reflect.Method;

/**
 * Created by esben on 14/04/16.
 */
public class Compiler {
    public Test outputClass = new Test();
    public void inputTask(String fileToCompile) {
        //String fileToCompile = "DynamicClass.java";

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        int compilationResult = compiler.run(null, null, null, fileToCompile);

        if (compilationResult == 0) {
            LoadClass();
        } else {
            outputClass.Hej("Compilation Failed");
        }
    }


    public void LoadClass(){
        try
        {
            Class[] argTypes = new Class[1];
            argTypes[0] = String[].class;

            Class <?> newClass = newClassLoader();
            Method mainMethod = newClass.getDeclaredMethod("main", argTypes);
            Object[] argListForInvokedMain = new Object[1];
            argListForInvokedMain[0] = new String[0];
            // Place whatever args you want to pass into other class's main here.
            mainMethod.invoke(null, argListForInvokedMain);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static Class <?> newClassLoader() {
        Class[] argTypes = new Class[1];
        argTypes[0] = String[].class;
        ClassLoader parentClassLoader = DynamicSuperClassLoader.class.getClassLoader();
        DynamicSuperClassLoader classLoader;
        classLoader = new DynamicSuperClassLoader(parentClassLoader);
        try {
            Class <?> clas = classLoader.loadClass("DynamicClass");
            return clas;
        }
        catch (Exception e){
            System.out.println("Class loading failed");
            e.printStackTrace();
            return null;
        }

    }
}
