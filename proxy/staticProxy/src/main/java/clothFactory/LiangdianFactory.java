package clothFactory;

public class LiangdianFactory implements ClothFactory {

    public float sell(int amount) {
        return 10000 * amount;
    }
}
