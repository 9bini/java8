package com.koobin.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class completable_future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Executor없이 만들수 있다.

        //방식 1
        CompletableFuture<String> future1 = new CompletableFuture<>();
        future1.complete("koo");

        // 방식 2
        CompletableFuture<String> future2 = CompletableFuture.completedFuture("Koo");

        // %비동기로 작업 실행하기, *콜백 제공하기
        // get 혹은 join을 해야 동작(작업)을 한다.
        // % 리턴값이 없는 경우
        CompletableFuture<Void> noReturnFuture = CompletableFuture.runAsync(() -> {
            System.out.println("noReturnFuture" + Thread.currentThread().getName());
        }).thenRun(() -> { //* thenRun
            System.out.println("thenRun: " + Thread.currentThread().getName());
        });

        // %. 리턴값이 있는 경우
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync: " + Thread.currentThread().getName());
            return "Hello";
        }).thenApply((s) -> { // *thenApply 추가 시 콜백을 변환
            // 기본 Future에서는 get 호출하기 전에 정의하는 것이 불가능했다.
            System.out.println("thenApply: " + Thread.currentThread().getName());
            return s.toUpperCase();
        });

        System.out.println("supplyAsync.get() = " + supplyAsync.get());

        CompletableFuture<Void> thenAccept = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync: " + Thread.currentThread().getName());
            return "Hello";
        }).thenAccept(s -> {// * thenAccept
            System.out.println("thenAccept: " + Thread.currentThread().getName());
            System.out.println(s.toUpperCase());
        });
        System.out.println("thenAccept.get() = " + thenAccept.get());

        // 조합하기
        // 1. thenCompose()
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello" + Thread.currentThread().getName());
            return "Hello";
        });
        CompletableFuture<String> future = hello.thenCompose(completable_future::getWorld);
        System.out.println(future.get());

        // 2. thenCombine()
        CompletableFuture<String> combine = hello.thenCombine(getWorld(""), (h, w) -> h + " " + w);
        System.out.println(combine.get());

        // 3. allOf()
        List<CompletableFuture<String>> asList = Arrays.asList(hello, getWorld("allOf"));
        CompletableFuture[] array = asList.toArray(new CompletableFuture[asList.size()]);

        CompletableFuture<List<String>> result = CompletableFuture.allOf(array)
                .thenApply(v -> asList.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()));

        result.get().forEach(System.out::println);
        // 4. anyOf()

        CompletableFuture<Void> anyof = CompletableFuture.anyOf(hello, getWorld("anyof"))
                .thenAccept(System.out::println);
        anyof.get();
        boolean throwError = true;
        CompletableFuture<String> exeptionally = CompletableFuture.supplyAsync(() -> {
            if (throwError){
                throw new IllegalArgumentException();
            }
            System.out.println("supplyAsync: " + Thread.currentThread().getName());
            return "Hello";
        }).exceptionally(ex ->{
            System.out.println(ex);
            return "Error!";
        });

        System.out.println("exeptionally = " + exeptionally);

        CompletableFuture<String> handle = CompletableFuture.supplyAsync(() -> {
            if (throwError){
                throw new IllegalArgumentException();
            }
            System.out.println("supplyAsync: " + Thread.currentThread().getName());
            return "Hello";
        }).handle((re, ex)->{
            if (ex != null){
                System.out.println(ex);
                return "Error!";
            }
            return re;
        });
    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World" + Thread.currentThread().getName());
            return message + "World";
        });
    }
}
