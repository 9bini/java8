package com.koobin.thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CallableAndFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };


        Future<String> future = executorService.submit(hello);
        System.out.println(future.isDone());
        System.out.println("started!");

        future.get(); // 볼록킹 콜이다
        future.cancel(false);

        System.out.println(future.isDone());
        System.out.println("End");

        executorService.shutdown();


        ExecutorService service = Executors.newFixedThreadPool(4);
        Callable<String> java = () -> {
            Thread.sleep(2000L);
            return "java";
        };
        Callable<String> koo = () -> {
            Thread.sleep(1000L);
            return "koo";
        };

        List<Future<String>> futures = service.invokeAll(Arrays.asList(hello, java, koo));
        for (Future<String> stringFuture : futures)
            System.out.println(stringFuture.get());
        System.out.println("futures");

        String s = service.invokeAny(Arrays.asList(hello, java, koo));
        System.out.println("s = " + s);
        System.out.println("invokeAny");

        service.shutdown();
    }
}
