# Kotlin版本差异

##  1.3

### 协程

见之前的分享

### 契约/协议(contract)

#### 背景

kotlin相较于Java更好用的一个重要原因是其拥有智能转换的特性——能够根据类型检测自动转换类型，以提供警告并减少模版代码。

#### 支持场景

- 通过声明函数调用的结果与所传参数值之间的关系来改进智能转换分析。

- 在存在高阶函数的情况下改进变量初始化的分析。

  **标准库中的契约**

  在阅读Kotlin源码的时候，会发现很多常用方法里都有contract的身影。如：

  ```
  let、run、with、also、apply、takeIf、takeIf、isNullOrEmpty....
  ```

  **自定义契约**

  场景：智能类型转换**通过方法判断**（判断逻辑抽取到独立的方法）时，由于没有直接上下文，这时候编译器就不能帮我们自动类型转换，会报错：Unresolved reference: methodName

  ```kotlin
  @ContractsDsl
  @ExperimentalContracts
  @InlineOnly
  @SinceKotlin("1.3")
  @Suppress("UNUSED_PARAMETER")
  public inline fun contract(builder: ContractBuilder.() -> Unit) { }
  ```
  
  ```kotlin
  @ContractsDsl
  @ExperimentalContracts
  @SinceKotlin("1.3")
  public interface ContractBuilder {
     
      @ContractsDsl public fun returns(): Returns
  
      @ContractsDsl public fun returns(value: Any?): Returns
     
      @ContractsDsl public fun returnsNotNull(): ReturnsNotNull
  
      @ContractsDsl public fun <R> callsInPlace(lambda: Function<R>, kind: InvocationKind = InvocationKind.UNKNOWN): CallsInPlace
  }
  ```
  
  **自定义契约用法**
  
  ```kotlin
  @ExperimentalContracts
  fun Animal?.isDog(): Boolean {
      // implies是中缀函数，表示当返回值为true时，告诉编译器Animal是Dog条件保证成立。
      contract { returns(true) implies (this@isDog is Dog) } // 此写法是一种约定形式，不会参与代码执行
      return this != null && this is Dog
  }
  
  /**
   * callsInPlace AT_LEAST_ONCE,告诉编译器，该函数至少会被调用一次
   */
  @ExperimentalContracts
  fun init(block: () -> Unit) {
      contract {
          callsInPlace(block, kotlin.contracts.InvocationKind.AT_LEAST_ONCE)
      }
      block()
  }
  ```
  
  
  
  **自定义契约分类**
  
  1. Returns Contracts：向编译器保证返回时某种条件必定成立（如变量是某种类型），方便编译器之后判断是否需要提醒错误；一般有下面几个形式：

  | Returns Contract                            | 说明                              |
  | ------------------------------------------- | --------------------------------- |
  | returns (true) implies [返回时成立的条件]   | 返回 true时 implies 条件保证成立  |
  | returns (false) implies [返回时成立的条件]  | 返回 false时 implies 条件保证成立 |
  | returns (null) implies [返回时成立的条件]   | 返回 null时 implies 条件保证成立  |
  | returns() implies [返回时成立的条件]        | 返回时 implies 条件保证成立       |
  | returnsNotNull() implies [返回时成立的条件] | 返回非空值时 implies 条件保证成立 |
  
  2. CallInPlace Contracts：通知编译器传入的函数会根据给定的枚举类型执行相应次数（如let、run等，表示传入的函数在调用一次后，不会再被执行），有以下枚举类型：
  
  | callsInPlace返回InvocationKind 枚举 | 说明                           |
  | ----------------------------------- | ------------------------------ |
  | `AT_MOST_ONCE`                      | 函数最多调用一次，可能不会调用 |
  | `EXACTLY_ONCE`                      | 函数只会调用一次               |
  | `AT_LEAST_ONCE`                     | 函数至少调用一次               |
  | `UNKNOW`                            | 函数可以不会调用或调用多次     |
  
  **自定义契约注意事项**
  
  1. 只能用于顶级函数。
  
  2. contract描述必须位于函数的开头，并且至少具有一种效果（Effect.kt），如。

  3. Returns(含ReturnsNotNull)、CallsInPlace。

  4. 编译器无条件相信contracts契约，开发者应该保证其正确性。

     

## 1.4.30&1.5

### 密封类和密封接口

#### 背景

*密封*类和接口提供类层次结构的受控继承。密封类的所有直接子类在编译时都是已知的。在定义密封类的模块和包之外不得出现任何其他子类。

#### 密封类

- **需要有限的类继承：**您有一个预定义的、有限的子类集，这些子类扩展了一个类，所有这些子类在编译时都是已知的。

- **需要类型安全的设计：**安全性和模式匹配对于您的项目至关重要。特别是对于状态管理或处理复杂的条件逻辑。

- **使用封闭的 API：**您需要为库提供健壮且可维护的公共 API，以确保第三方客户端按预期使用这些 API。密封类定义之外的地方无法继承它。

  ```kotlin
  sealed class Error(val message: String) {
      class NetworkError : Error("Network failure")
      class DatabaseError : Error("Database cannot be reached")
      class UnknownError : Error("An unknown error has occurred")
  }
  
  fun whenTest(error: Error) {
      when (error) {
          is Error.DatabaseError -> println("Database error")
          is Error.NetworkError -> println("Network error")
          is Error.UnknownError -> println("Unknown error")
      }
  }
  ```

 ##### 与枚举的区别

密封类的子类可以具有特殊属性的场景，枚举无法继承其他类(Enum class cannot inherit from classes)，故无法支持。密封类可以完整替代枚举且可以扩展。

```kotlin
enum class DayOfWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

sealed class UiState {
    object Loading : UiState()
    data class Success(val data: JSONObject) : UiState()
    data class Error(val code: Int,val message: String) : UiState()
}
```

#### 密封接口

```kotlin
sealed interface Polygon // 多边形
sealed interface Fillable

class Rectangle(): Polygon // 矩形
class Triangle(): Polygon // 三角形
class FilledRectangle: Polygon, Fillable // 实心矩形

// when() is exhaustive: no other polygon implementations can appear
// after the module is compiled
fun draw(polygon: Polygon) = when (polygon) {
    is Rectangle -> // ...
    is Triangle -> // ...
    // else is not needed - all possible implementations are covered
}
```

在密封类的基础上，密封接口可以实现更灵活的受限类层次结构，因为一个类可以直接继承多个密封接口。重在**组合**（多继承）。

##### 与普通接口区别

密封性。

#### 注意事项

- 密封类本身是抽象类，不能被直接实例化，可以有抽象成员属性。

- 密封类的构造函数只能具有两种可见修饰符：（protected默认）或 private。

- 为了更好的兼容各个版本，密封类的直接子类建议最好定义为内部类。（1.0：必须定义为内部类。1.1：密封类的子类可以声明在文件的 Top-Level。1.5：允许子类定义在不同的文件中，只要保证子类和父类在同一个 Gradle module 且是同一个包名下即可）

- 1.8.20起，使用**data object**修饰密封接口的子类可以自动生成equals、hashCode、toString方法

  ```kotlin
  sealed interface ReadResult
  data class Number(val number: Int) : ReadResult
  data class Text(val text: String) : ReadResult
  data object EndOfFile : ReadResult
  
  fun main() {
      println(Number(7)) // Number(number=7)
      println(EndOfFile) // EndOfFile
  }
  ```

  

### 内联类（1.5稳定）

内联类是仅包含值的基于值的类的子集。 类的数据被内联到该类使用的地方（类似于内联函数的内容被内联到该函数调用的地方）。您可以将它们用作特定类型值的包装器，而无需使用内存分配带来的额外开销。

#### 用法

要声明内联类，在类名之前使用 `value` 修饰符：

```kotlin
value class Password(private val s: String)
```

若要在 JVM 后端声明内联类，只需在类声明前使用 `value` 修饰符并添加 `@JvmInline` 注解：

```kotlin
// 适用于 JVM 后端
@JvmInline
value class Password(private val s: String)
```

内联类中不能有幕后属性(backing fields，也叫成员变量)：

> ### 

#### 使用场景

1. 提高可读性

2. 

3. 类型安全

   3.1 缩小扩展函数作用域

   ```
   ```

   

   3.2 用于校验参数值

   ```kotlin
   inline class Email(private val email: String) {
       init {
           require(email.contains("@")) { "Invalid email address" }
       }
   
       override fun toString(): String = email
   }
   
   fun main() {
       val validEmail = Email("user@example.com")
       println(validEmail)  // 输出: user@example.com
   
       // 下面这行会抛出异常：IllegalArgumentException: Invalid email address
       // val invalidEmail = Email("invalid-email")
   }
   ```

   

4. 

#### 与typealias 区别

#### 原理

普通类实例化时创建的对象被分配到堆内存中；内联类实例化时在最终生成的字节码中被直接替换成其 “包装”的 value, 被分配到栈中从而提高运行时的性能。

*代码：*

```kotlin
fun main() {
    val password = Password("12345678")
    checkPwd1(password)
    val inlinePassword = InlinePassword("87654321")
    checkPwd2(inlinePassword)
    println("encodedPassword  ${inlinePassword.encodedPassword}")
}

fun checkPwd1(password: Password) {
    println("checkPwd1 $password")
}

fun checkPwd2(password: InlinePassword) {
    println("checkPwd2 $password")
    println("encodedPassword ${password.encodedPassword}")
}
```

*反编译：*

```java
public static final void main() {
      Password password = new Password("12345678");
      checkPwd1(password);
      String inlinePassword = InlinePassword.constructor-impl("12345678");
      checkPwd2-3RAinPA(inlinePassword);
}

public static final void checkPwd1(@NotNull Password password) {
      Intrinsics.checkNotNullParameter(password, "password");
      String var1 = "checkPwd1 " + password;
      System.out.println(var1);
}

public static final void checkPwd2_3RAinPA/* $FF was: checkPwd2-3RAinPA*/(@NotNull String password) {
      Intrinsics.checkNotNullParameter(password, "password");
      String var1 = "checkPwd2 " + InlinePassword.toString-impl(password);
      System.out.println(var1);
}
```

```java
public final class InlinePassword {
	···
   public static String constructor_impl/* $FF was: constructor-impl*/(@NotNull String s) {
      Intrinsics.checkNotNullParameter(s, "s");
      return s;
   }
   ···
}
```

*输出：*

```
checkPwd1 org.example.Password@61bbe9ba
checkPwd2 InlinePassword(s=87654321)
encodedPassword 87654321
```



#### 注意事项

- 内联类是final的，无法被继承，但可以实现接口。

- 内联类的属性不能有幕后字段 。它们只能有简单的可计算属性（没有 lateinit /委托属性）。

  > 属性:
  >
  > 字段：
  >
  > 幕后属性：
  >
  > 幕后字段：