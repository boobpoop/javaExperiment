import ClothFactory.ClothFactory;
import ClothFactory.LiangdianFactory;
import handler.ClothHandler;

import java.lang.reflect.Proxy;

public class DynamicProxyEntry {
    public static void main(String[] args) {
        ClothFactory factory = new LiangdianFactory();
        ClothHandler clothHandler = new ClothHandler(factory);
        ClothFactory proxy = (ClothFactory) Proxy.newProxyInstance(factory.getClass().getClassLoader(), factory.getClass().getInterfaces(), clothHandler);
        float sell = proxy.sell(1);
        System.out.println(sell);
        float buy = proxy.buy(2);
        System.out.println(buy);
    }
}
