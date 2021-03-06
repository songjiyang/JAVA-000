学习笔记




#### 思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这个方法的返回值后，退出主线程

总的来说就是使用各种方式的资源共享，通过内存（堆内存，或者redis）， 磁盘（文件和数据库), 网络(socket) 共享那个需要传递的值, 然后协调线程之间的顺序，能准确让对应的线程拿到另外线程的值


1. static变量共享，通过堆内存上的Class对象来共享， 需要通过CountDownLatch来协调主线程和子线程
2. instance变量，通过堆内存上特定的对象来共享，需要通过CountDownLatch来协调主线程和子线程
3. FutureTask, 通过堆内存上FutureTask实例来共享值，FutureTask内部来协调主线程和子线程, run开启子线程，get阻塞主线程并等待子线程
4. executorService.submit，通过FutureTask来共享值，FutureTask内部来协调主线程和子线程, executorService提供了线程池功能
5. static变量共享, 主线程自旋来等待值
6. static变量共享, LockSupport来协调线程
```
public class PassValueInThread {

    public static String message;
    public String instanceMessage;


    public static void main(String[] args) {

//        staticVarAndCountDownLatch();
//        System.out.println(message);

//        PassValueInThread passValueInThread = instanceVarAndCountDownLatch();
//        System.out.println(passValueInThread.instanceMessage);


//        System.out.println(futureTask());

//        System.out.println(singleThreadExecutor());

//        spinWaiting();
//        System.out.println(message);

        lockSupport();
        System.out.println(message);
    }


    public static void staticVarAndCountDownLatch() {
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    message = getMessage();
                    countDownLatch.countDown();
                }
            }).start();
            System.out.printf("当前线程数量: %s%n", Thread.activeCount());
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static PassValueInThread instanceVarAndCountDownLatch() {
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            PassValueInThread passValueInThread = new PassValueInThread();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    passValueInThread.instanceMessage = getMessage();
                    countDownLatch.countDown();
                }
            }).start();
            System.out.printf("当前线程数量: %s%n", Thread.activeCount());
            countDownLatch.await();
            return passValueInThread;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String futureTask() {
        try {
            Callable<String> callable = PassValueInThread::getMessage;
            FutureTask<String> futureTask = new FutureTask<>(callable);
            System.out.printf("当前线程数量: %s%n", Thread.activeCount());
            futureTask.run();
            return futureTask.get();

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String singleThreadExecutor() {
        try {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            System.out.printf("当前线程数量: %s%n", Thread.activeCount());
            String s = executorService.submit(PassValueInThread::getMessage).get();
            executorService.shutdown();
            return s;

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void spinWaiting() {
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    message = getMessage();
                }
            }).start();
            while (message == null) {
                Thread.sleep(2);
            }
            System.out.printf("当前线程数量: %s%n", Thread.activeCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void lockSupport() {
        Thread mainThread = Thread.currentThread();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (PassValueInThread.class) {
                        message = getMessage();
                        LockSupport.unpark(mainThread);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        System.out.printf("当前线程数量: %s%n", Thread.activeCount());
        LockSupport.park();
    }


    public static String getMessage() {
        return "got it";
    }

}
```


#### 把多线程和并发相关知识带你梳理一遍，画一个脑图，截图上传到 Github 上

![avatar](多线程和并发.png)