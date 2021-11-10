# CountDownLatch
主要有`await()`/`await(timeMs,TimeUnit)`/`countDown(`) 三个方法

>// 初始化拦截数为4的Latch <br>
>`final CountDownLatch countDownLatch = new CountDownLatch(4);` 

当调用countDownLatch.await()方法时，会block。<br>
until the specified number of releases is counted.
也就是block,直到counted的次数释放完。

当countDownLatch.countDown() 4次的时候 它就已经无拦截作用了。