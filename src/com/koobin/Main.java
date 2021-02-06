package com.koobin;

import com.koobin.lambda.Func;
import com.koobin.lambda.RunSomething;

import java.util.function.*;


public class Main {
    public static void main(String[] args) {
        // 익명 내부 클래
        RunSomething runSomething = new RunSomething() {
            @Override
            public void doIt() {

            }
        };

        /**
         * 문법 적으로 외부에 변수를 접근 가능하지만
         * 변경하는 것은 막아져 있다. Final 변수처럼 사용해야한다.
         * 만약 순수 함수로 사용할려면 함수 밖에 있는 값을 변경, 사용하지 않아야 한다.
         */
        int baseNum = 10;
        RunSomething runSomething2 = () -> {
            System.out.println(baseNum);
        };

        // 자바가 기본적으로 제공하는 함수형 인터페이스

        // Function<T, R> T 타입을 받아서 R 타입을 리턴하는 함수 인터페이스
        //  클래스 상속
        Func func = new Func();
        // R apply(T t)
        System.out.println(func.apply(10));
        // 바로 생성
        Function<Integer, Integer> add10 = (i) -> i + 10;
        System.out.println(add10.apply(10));

        // 조합 compose <- 처리 순서
        Function<Integer, Integer> mul2 = (i) -> i * 2;
        System.out.println(add10.compose(mul2).apply(10));

        // 조합 andThen -> 처리 순서
        System.out.println(add10.andThen(mul2).apply(10));

        // UnaryOperator<T> Function<T, R>의 특수한 형태로, 입력값 하나를 받아서 동일한 타입을 리턴하는 함수 인터페이스
        UnaryOperator<Integer> add11 = (i) -> i + 11;
        System.out.println(add11.apply(10));

        // BiFunction<T, U, R> 두 개의 값(T, U)를 받아서 R 타입을 리턴하는 함수 인터페이스
        BiFunction<Integer, Integer, Integer> biFunction = (x, y) -> x + y;
        // R apply(T t, U u)
        System.out.println(biFunction.apply(10, 20));

        // BinaryOperator<T> BiFunction<T, U, R>의 특수한 형태로, 동일한 타입의 입렵값 두개를 받아 리턴하는 함수 인터페이스
        BinaryOperator<Integer> binaryOperator = (x, y) -> x + y;
        System.out.println(binaryOperator.apply(10, 20));

        //  Consumer<T> T  타입을 받아서 아무값도 리턴하지 않는 함수 인터페이스
        Consumer<Integer> consumer = (i) -> System.out.println(i);
        // void Accept(T t)
        consumer.accept(10);

        // Supplier<T> T 타입의 값을 제공하는 함수 인터페이스
        Supplier supplier = () -> 10;
        // T get()
        System.out.println(supplier.get());

        // Predicate<T> T 타입을 받아서 boolean을 리턴하는 함수 인터페이스
        Predicate<String> startsWithTest = (s) -> s.startsWith("test");
        // boolean test(T t)
        System.out.println(startsWithTest.test("test1123"));
        Predicate<Integer> isEven = (i) -> i % 2 == 0;
        System.out.println(isEven.test(3));
    }
}
