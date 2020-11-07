package com.proxy.proxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class ProxyUtil {

    /**
     * content --- str
     * .java 手动 或者 io
     * .class
     *
     * 利用反射 实例化
     * @return
     */
    public static Object newInstance(Object targetObj) {
        Class target = targetObj.getClass().getInterfaces()[0];
        Object proxy = null;
        Method[] methods = target.getMethods();
        String infName = target.getSimpleName();
        String content = "";
        String packageConte = "package com.google;\n";
        String importConent = "import " + target.getName() + ";\n";
        String classFirstLineContent = "public class $Proxy implements " + infName + "{\n";
        String propertyContent = "\tprivate " + infName + " target;\n";
        String constructContent = "\tpublic $Proxy(" + infName + " target){\n" +
                                        "\t\tthis.target = target;\n" +
                                  "\t}\n";
        String methodContent = "";
        for (Method method : methods) {
            String returnType = method.getReturnType().getSimpleName();
            String methodName = method.getName();
            Class[] args = method.getParameterTypes();
            String argsContent = "";
            String paramContent = "";
            int index = 0;
            for (Class arg : args) {
                argsContent += arg.getSimpleName() + " p" + index + ", ";
                paramContent += "p" + index + ", ";
                index++;
            }
            if (argsContent.length() > 0) {
                argsContent = argsContent.substring(0, argsContent.length() - 2);
                paramContent = paramContent.substring(0, paramContent.length() - 2);
            }
            String returnContent = "";
            if (!returnType.equals("void")) {
                returnContent = "return ";
            }
            methodContent += "\tpublic " + returnType + " " + methodName + "(" + argsContent + "){\n" +
                                "\t\tSystem.out.println(\"log\");\n" +
                                "\t\t" + returnContent + "target." + methodName + "(" + paramContent + ");\n" +
                                "\t}\n";


        }
        content = packageConte + importConent + classFirstLineContent + propertyContent + constructContent + methodContent + "}";
        File file = new File("D:\\com\\google\\$Proxy.java");

        try {
            FileWriter fileWriter = new FileWriter(file);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter.write(content);
            fileWriter.flush();
            fileWriter.close();
//            fileOutputStream = new FileOutputStream(file);
//            fileOutputStream.write(content.getBytes());


            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
            Iterable units = fileManager.getJavaFileObjects(file);

            JavaCompiler.CompilationTask t = compiler.getTask(null, fileManager,null, null, null, units);
            t.call();
            fileManager.close();

            //Class.forName()
            //因为类是包含包名的 所以应该直接扫描D盘
            URL[] urls = new URL[]{new URL("file:D:\\\\")};
            URLClassLoader urlClassLoader = new URLClassLoader(urls);
            Class clazz = urlClassLoader.loadClass("com.google.$Proxy");
            Constructor constructor = clazz.getConstructor(target);
            proxy = constructor.newInstance(targetObj);
//            clazz.newInstance();//这是默认的构造方法 有显式构造方法 就要用上面的方法去实例化
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {

        } finally {

        }


        return proxy;
    }
}
