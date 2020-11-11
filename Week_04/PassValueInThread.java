package Week_04;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author songjiyang
 */
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
