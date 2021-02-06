package com.koobin.method;

import java.util.*;
import java.util.stream.Collectors;

public class Api {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();

        names.add("9");
        names.add("bin");
        names.add("foo");
        names.add("bar");

        Api api = new Api();
        api.iterable(names);
        api.collection(names);
        api.comparator(names);





    }
    void iterable(List<String> names){
        forEach(names);
        spliterator(names);
    }
    void forEach(List<String> names){
        System.out.println("forEach");
        names.forEach(s -> {
            System.out.println(s);
        });

        names.forEach(System.out::println);

        for(String name: names){
            System.out.println(name);
        }
    }
    void spliterator(List<String> names){
        // 패럴렐, 벙렬 처리할 때 사용
        System.out.println("Spliterator");
        Spliterator<String> spliterator = names.spliterator();
        //  tryAdvance(Consumer) Consumer: 리터값이 없다.
        while(spliterator.tryAdvance(System.out::println));
        Spliterator<String> spliterator1 = names.spliterator();
        Spliterator<String> spliterator2 = spliterator1.trySplit();
        while(spliterator1.tryAdvance(System.out::println));
        System.out.println("============================");
        while(spliterator2.tryAdvance(System.out::println));
    }

    void collection(List<String> names){
        stream(names);
        removeIf(names);

    }
    void stream(List<String> names){
        System.out.println("stream");
        Set<String> collect = names.stream().map(String::toUpperCase)
                .filter(s -> s.startsWith("9"))
                .collect(Collectors.toSet());
        collect.forEach(System.out::println);
    }
    void removeIf(List<String> names){
        System.out.println("removeIf");
        names.removeIf(s -> s.startsWith("9"));
        names.forEach(System.out::println);
    }

    void comparator(List<String> names){
        reversed(names);
    }
    void reversed(List<String> names){
        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        names.sort(compareToIgnoreCase.reversed());
        names.forEach(System.out::println);
    }
}
