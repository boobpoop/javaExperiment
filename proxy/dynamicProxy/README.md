## 动态代理

JDK动态代理三大类：InvocationHandler、Method、Proxy。

### Method类回顾

示例：

```
Method method = ClothFactory.class.getMethod("sell", int.class);
```

参数:

1. String 表示方法名
2. Class<?>... 表示参数类型列表，即该方法有若干形参，从左到右为各个形参的类型。

返回值：

匹配指定方法名和参数类型的Method对象。


```
Object res = method.invoke(factory, 2);
```

参数：

1. object 目标对象，用于指定目标方法。
2. Object... 形参列表，用于向目标方法传递参数。调用invoke时，会检测目标对象中是否有Method对象中指定的方法，传递的参数是否与Method参数列表匹配。

返回值：

Object 表示目标对象方法的返回值。

### InvocationHandler接口

InvocationHandler是接口调用处理器，其子类用于对目标类方法进行增强。

在InvocationHandler中，目标对象是Object类，不知道目标对象的具体类型，因此不能直接调用目标对象的方法，必须调用Method对象的invoke方法的方法调用目标方法。


```
public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
```

参数：

1. Object proxy 表示JDK创建的代理对象，无需赋值。
2. Method method：目标类中的方法，jdk提供Method对象。
3. Object[] args：目标方法的参数。

返回值：

- Object 表示增强后的目标方法的返回值。


    注意:在使用InvocationHandler时，无需使用proxy对象，只需要操作method和args即可。

### Proxy

Proxy根据目标类接口和InvocationHandler增强方法在程序运行时生成字节码，然后进行加载，初始化。

```
public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h)
```

参数：

1. ClassLoader loader 类加载器，负载向内存中加载对象。
2. Class<?>[] interfaces：目标对象实现的接口
3. InvocationHandler h：代理类要完成的功能。

返回值：

- Object 代理实例对象。

参考：https://www.jianshu.com/p/d0ee1ca57f14


## Cglib扩展

JDK动态代理是Java自带的动态代理组件。
CGlib是第三方组件。

JDK通过实现类的接口创建代理对象。但是Cglib更灵活，它通过继承实现类，重写实现类的方法就可创建代理对象，不需要实现类的接口。

    注意：Cglib要求代理方法不能是final，不然无法重写目标类方法。