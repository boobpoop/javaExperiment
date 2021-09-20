package ClothFactory;

public interface ClothFactory {

    /**
     *
     * @param amount 销售数量
     * @return 商品总价
     */
    float sell(int amount);

    /**
     *
     * @param amount 进货数量
     * @return 商品总价
     */
    float buy(int amount);
}
