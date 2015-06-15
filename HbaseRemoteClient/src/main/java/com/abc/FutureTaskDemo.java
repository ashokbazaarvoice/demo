package com.abc;

import java.util.concurrent.*;

/**
 * Created by ashok.agarwal on 5/4/15.
 */
public class FutureTaskDemo {

    public static void main(String[] args) {
        MyCallable callable1 = new MyCallable(1000);
        MyCallable callable2 = new MyCallable(2000);

        FutureTask<String> futureTask1 = new FutureTask<String>(callable1);
        FutureTask<String> futureTask2 = new FutureTask<String>(callable2);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(futureTask1);
        executor.execute(futureTask2);

        while (true) {
            try {
                if(futureTask1.isDone() && futureTask2.isDone()){
                    System.out.println("Done");
                    //shut down executor service
                    executor.shutdown();
                    return;
                }

                if(!futureTask1.isDone()){
                    //wait indefinitely for future task to complete
                    System.out.println("FutureTask1 output="+futureTask1.get());
                }

                System.out.println("Waiting for FutureTask2 to complete");
                String s = futureTask2.get(200L, TimeUnit.MILLISECONDS);
                if(s !=null){
                    System.out.println("FutureTask2 output="+s);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch(ExecutionException e){
                //do nothing
                e.printStackTrace();
            }catch(TimeoutException e){
                //do nothing
                e.printStackTrace();
            }
        }

    }

}
class MyCallable implements Callable<String> {

    private long waitTime;

    public MyCallable(int timeInMillis){
        System.out.println("Created callable instance");
        this.waitTime=timeInMillis;
    }
    @Override
    public String call() throws Exception {
        System.out.println("Called callable call method");
        Thread.sleep(waitTime);
        //return the thread name executing this callable task
        return Thread.currentThread().getName();
    }

}
