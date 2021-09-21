package handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ClothHandler implements InvocationHandler {

    Object target;

    public ClothHandler(Object obj){
        this.target = obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        preEnhance();
        Object res = method.invoke(target, args);
        postEnhance();
        return res;
    }

    private void preEnhance() {
        System.out.println("购买前提供服务");
    }

    private void postEnhance() {
        System.out.println("购买后提供服务");
    }
}
