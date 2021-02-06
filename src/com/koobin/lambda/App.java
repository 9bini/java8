package com.koobin.lambda;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class App {
    public static void main(String[] args) {
        // 기존 람다 형태
        Function<Integer, String > intToString = (i) ->"number";

        // 메소드 레퍼런스 ::

        // 스태틱 메소드 참조
        UnaryOperator<String> hi = Greeting::hi;
        System.out.println(hi.apply("java"));

        // 특정 객체의 인스턴스 메소드 참조
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;
        System.out.println(hello.apply("java"));

        // 생성자 참조
        // Greeting::new 값만 보면 의도를 알기 힘들어 타임도 함께 봐야한다.
        Supplier<Greeting> greetingSupplier = Greeting::new;
        Greeting supplier = greetingSupplier.get();
        System.out.println(supplier.getName());

        Function<String, Greeting> greetingFunction = Greeting::new;
        Greeting function = greetingFunction.apply("java");
        System.out.println(function.getName());

        // 임의 객체의 인스턴스 메소드 참조
        String[] names = {"keesun", "whiteship", "toby"};
        Arrays.sort(names, String::compareToIgnoreCase);
        Arrays.stream(names).map(name -> "name = " + name).forEachOrdered(System.out::println);
    }
}
