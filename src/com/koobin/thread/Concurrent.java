package com.koobin.thread;

public class Concurrent {
    public static void main(String[] args){
        // 지금 동작하는 쓰레드 main 쓰레드이다
        System.out.println("hello: " + Thread.currentThread().getName());
        // 동작 순서는 보장 할 수 없다.

        // 메인 쓰레드에서 다른 쓰레드를 만들는 방법
        // 1. 쓰레드 상속
        MyThread myThread = new MyThread();
        myThread.start();
        // 2.쓰레드 생성자에게 Runnable 주는 것
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread" + Thread.currentThread().getName());
            }
        });
        thread.start();

        // 쓰레드의 주요 기능 최소한 3가지를 알아야한다.
        // 1. sleep 사용시 다른 쓰레드에게 다른 리소스를 전달한다.
            // 자는 동안 아무런 동작을 하지않기 때문
        Thread threadLambda = new Thread(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Lambda: " + Thread.currentThread().getName());
        });

        // 2. interrupt 쓰레드를 깨우는 용도
            // threadLambda(Thread) 안에 Thread.sleep() 사용해서 알아서 threadLambda(Thread)
            // 를 인식하는 건가?
            // 깨우는 용도지 종료 시키는 용도가 아니다.
        Thread threadMethodRef = new Thread(Concurrent::run);
        threadMethodRef.start();
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadMethodRef.interrupt();

        // 3. join 다른(threadMethodRef) 쓰레드를 기다리는 기능
        System.out.println("hello: " + Thread.currentThread().getName());
        try {
            threadMethodRef.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread + " is finished");
        // 쓰레드 끼리 계속 왔다갔다 하기 때문에 복잡해진다.? 난잡해진다.

    }
    private static void run() {
        while (true) {
            System.out.println("MethodRef: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                System.out.println("exit!");
                return;
            }
        }
    }

    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("Hello: " + Thread.currentThread().getName());
        }
    }
}
