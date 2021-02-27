package com.koobin.thread;

import java.util.concurrent.*;

public class App {
    public static void main(String[] args){
        Executor executor;
        ExecutorService executorService;
        ScheduledExecutorService scheduledExecutorService;

        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(getRunnable("Thread"));
        // 주의할 것 실행이 되지만 executorService 만들어서 어떤 작업을 실핼시키면 다음 작업이 올때까지 계속해서 대기한다.
        // 그래서 프로세스가 죽지않는다. 계속 켜져있다.
        // 명시적을 shutdown을 해야한다.

        // graceful shutdown
        // 현재 진행중인 작업은 끝마치고 끝내는 기능
        executorService.shutdown();
        //executorService.shutdownNow();
        executorService = Executors.newFixedThreadPool(2);
        executorService.submit(getRunnable("Hello"));
        executorService.submit(getRunnable("9gin"));
        executorService.submit(getRunnable("the"));
        executorService.submit(getRunnable("java"));
        executorService.submit(getRunnable("thread"));
        executorService.shutdown();

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.schedule(getRunnable("service = Hello: "), 5, TimeUnit.SECONDS);

        service.shutdown();

        ScheduledExecutorService service2 = Executors.newSingleThreadScheduledExecutor();
        service2.scheduleAtFixedRate(getRunnable("service = fixed: "), 1, 2, TimeUnit.SECONDS);

    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + " " + Thread.currentThread().getName());
    }
}
