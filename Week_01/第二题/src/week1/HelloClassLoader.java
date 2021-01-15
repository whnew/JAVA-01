package week1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class HelloClassLoader extends ClassLoader{
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        HelloClassLoader helloClassLoader = new HelloClassLoader();
        Class c = helloClassLoader.findClass("Hello");
        Object obj = c.newInstance();
        c.getMethod("hello").invoke(obj);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File("src/week1/" + name + ".xlass");
        int length = (int) file.length();
        byte[] data = new byte[length];
        try {
            new FileInputStream(file).read(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (255 - data[i]);
        }
        return defineClass(name, data, 0, data.length);
    }
}
