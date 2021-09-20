package clothProxy;

import clothFactory.ClothFactory;

public class ClothStore implements ClothFactory {

    ClothFactory factory;

    public ClothStore(ClothFactory factory) {
        this.factory = factory;
    }

    public float sell(int amount) {
        float price = factory.sell(amount) + 100 * amount;
        ironing();
        cut();
        return price;
    }

    public void ironing(){
        System.out.println("提供熨烫服务");
    }

    public void cut(){
        System.out.println("提供裁剪裤子服务");
    }
}
