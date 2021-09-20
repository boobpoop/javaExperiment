import ClothFactory.ClothFactory;
import ClothFactory.LiangdianFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DynamicProxyEntry {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ClothFactory factory = new LiangdianFactory();
        Method method = ClothFactory.class.getMethod("sell", int.class);
        Object res = method.invoke(factory, 2);
        System.out.println(res);
    }
}
