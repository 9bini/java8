package com.koobin.stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApi {
    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        System.out.println("spring 으로 시작하는 수업");
        // 파이프라인을 정의한다.
        // 특정한 타입이 파이프라인에 지나간다.
        springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .forEach(oc-> System.out.println(oc.getId()));
        System.out.println("----------------");

        System.out.println("close 되지 않은 수업");
        springClasses.stream()
                .filter(Predicate.not(OnlineClass::isClosed))
                //.filter(oc -> oc.isClosed())
                .forEach(oc-> System.out.println(oc.getId()));
        System.out.println("----------------");

        System.out.println("수업 이름만 모아서 스트림 만들기");
        springClasses.stream()
                //.map(oc-> oc.getTitle())
                .map(OnlineClass::getTitle)
                .forEach(System.out::println);
        System.out.println("----------------");

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java 8 to 11", false));


        List<List<OnlineClass>> binEvents = new ArrayList<>();
        binEvents.add(springClasses);
        binEvents.add(javaClasses);


        System.out.println("두 수업 목록에 들어있는 모든 아이디 출력");
        binEvents.stream()
                //.flatMap(list -> list.stream())
                .flatMap(Collection::stream)
                .forEach(oc-> System.out.println(oc.getId()));
        System.out.println("----------------");

        System.out.println("10 부터 1씩 증가하는 무제한 스트림 주에서 앞에 10개 빼고 최대 10개 까지만");
        Stream.iterate(10,i->i+1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);
        System.out.println("----------------");

        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        boolean test = javaClasses.stream()
                .anyMatch(oc -> oc.getTitle().contains("Test"));
        System.out.println("test = " + test);
        System.out.println("----------------");

        System.out.println("스프링 수업 중에 제목이 spring이 들어간 제목만 모아서 List로 만들기");
        List<String> collect = springClasses.stream()
                // map 반환되는 타입을 고려 해야한다.
                //.map(oc->oc.getTitle())
                //.filter(title -> title.contains("spring"))
                .filter(oc -> oc.getTitle().contains("spring"))
                .map(oc->oc.getTitle())
                .collect(Collectors.toList());

        collect.forEach(System.out::println);
    }

}
