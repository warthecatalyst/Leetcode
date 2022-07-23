package project1;

import java.lang.reflect.Field;

public class main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class test = Class.forName("pkg1.Test1");
        Field field = test.getField("maxValue");
        field.setAccessible(true);
        int val = field.getInt(null);
        System.out.println(val);
    }
}
