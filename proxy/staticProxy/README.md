## 静态代理

代码结构：
```
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── StaticProxyEntry.java
│   │   │   ├── clothFactory
│   │   │   │   ├── ClothFactory.java
│   │   │   │   └── LiangdianFactory.java
│   │   │   └── clothProxy
│   │   │       └── ClothStore.java
│   │   └── resources
│   └── test
│       └── java
```


ClothStore是代理类

ClothFactory是目标类实现的接口

LiangdianFactory是实现接口的目标类

## 静态代理总结

### 优点

静态代理实现简单，容易理解。

### 缺点

当存在多个目标类接口时，需要给每个接口实现代理类。这时不可避免有大量代理类提供相同的增强功能，因此维护的代价太大。参考：https://cloud.tencent.com/developer/article/1464103
