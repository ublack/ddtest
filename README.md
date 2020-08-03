# ddtest

题目要求是设计一个简单的 Java 开源库，实现断线器的功能：当某一段代码发生错误率达到阈值的情况下，进入打开状态，自动执行

fallback 逻辑，一定的时间后自动重新合上执行默认逻辑。



提示大致可以有这样几个类，也可以不限于此，按照自己的理解来设计：


  * CircuitBreaker 断线器的策略和状态，其中策略需要包含：

       o 阈值，两个参数，例如 10，50 表示最近50次执行里有10次失败

       o 延迟，一个时间参数，表示打开后多久自动合上

       o fallback，当进入打开状态时的默认逻辑   * CircuitBreakerRunner

是用来执行这个断线器的类，可以只包含一个静态方法

     run()，接收两个参数：

       o circuitbreaker 的实例

       o callable 被保护的代码块

只要满足需求，上面的 API 完全可以自己设计
