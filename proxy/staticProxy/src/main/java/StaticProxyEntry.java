import clothFactory.ClothFactory;
import clothFactory.LiangdianFactory;
import clothProxy.ClothStore;

public class StaticProxyEntry {
    public static void main(String[] args) {
        ClothFactory factory = new LiangdianFactory();
        ClothStore proxy = new ClothStore(factory);
        float price = proxy.sell(10);
        System.out.println("商品价格： " + price);
    }
}
