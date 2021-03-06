# 代理模式

## 普通代理
普通代理模式用户不需要知道具体的代理者
### 代理结构
具备以下角色:
- 代理对象的抽象行为层
- 具体代理商对象持有真实对象的实例

## 强制代理
需要从真实对象访问代理商角色，不允许直接访问真实角色。

- 代理对象的抽象行为层中，提供了一个 **getProxy()** 的方法获取代理对象。
- 真实对象持有代理商对象的实例。
 
## 动态代理（AOP编程思想）
Java中常见的动态代理有：**JDK动态代理**、**cglib**。
此外还有ASM和bytebuddy等。
JDK动态代理：运行期动态的创建代理类，**只支持接口**；

- **JDK动态代理**
通过JDK提供的动态代理接口 **InvocationHandler**，
对被代理类的方法进行代理。

动态代理可以将所有调用重定向到调用处理器，
因此通常会调用处理器的构造器传递一个”实际”对象的引用，
从而将调用处理器在执行中介任务时，将请求转发。

动态代理在实现阶段不用关心代理谁，而是在运行阶段才指定代理谁。
之前的两种代理方式，需要自己写代理类的方式就是静态代理。

