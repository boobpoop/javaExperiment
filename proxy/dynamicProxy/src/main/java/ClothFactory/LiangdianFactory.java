package ClothFactory;

public class LiangdianFactory implements ClothFactory {

    public float sell(int amount) {
        return 10000 * amount;
    }

    public float buy(int amount) {
        return 10000 * amount;
    }
}
