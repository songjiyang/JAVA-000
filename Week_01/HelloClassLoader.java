package Week_01;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author songjiyang
 */
public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> helloClass = new HelloClassLoader().findClass("Hello");

        Object hello = helloClass.newInstance();
        Method helloMethod = helloClass.getMethod("hello");

        helloMethod.invoke(hello);
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {

            String classPath = URLDecoder.decode(HelloClassLoader.class.getResource("").getPath(), "UTF-8");
            byte[] helloClassByte = Files.readAllBytes(Paths.get(classPath, "Hello.xlass"));
            for (int i = 0; i < helloClassByte.length; i++) {
                helloClassByte[i] = (byte) ~helloClassByte[i];
            }
            return defineClass(name, helloClassByte, 0, helloClassByte.length);
        } catch (IOException e) {
            e.printStackTrace();
            return super.findClass(name);
        }

    }
}
