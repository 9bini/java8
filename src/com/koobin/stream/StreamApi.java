package com.koobin.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApi {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("9");
        names.add("bin");
        names.add("foo");
        names.add("bar");

        Stream<String> stringStream = names.stream().map(String::toUpperCase);
        Stream<String> stringStream2= names.stream().map(s -> {
            // 출력되지 않는다.
            // 터미널 오퍼레이터가 오지 않는 이상 실행되지 않는다.
            // 정의만 해놓은 상태이다. 실행을 하지 않느다. 종료형이 없다면 무의미한 코드다.
            // 중계 오퍼레이터 = 스트림 파이트라인을 정의해야한다.
            System.out.println("s = " + s);
            return s.toUpperCase();
        });
        System.out.println("===========");
        List<String> collect = names.stream().map(s -> {
            // 종료형이 오기 전까지 통작을 하지않는 느낌을 Lazy하다고 의미로 이해하면된다.

            System.out.println("s = " + s);
            return s.toUpperCase();
        }).collect(Collectors.toList());

        System.out.println("===========");
        collect.forEach(System.out::println);

        System.out.println("===========");
        names.forEach(System.out::println);
        for (String name: names){
            if (name.startsWith("9")){
                System.out.println("name.toUpperCase() = " + name.toUpperCase());
            }
        }
        List<String> collect1 = names.parallelStream().map(String::toUpperCase).collect(Collectors.toList());
        collect1.forEach(System.out::println);
        // 주의해야하는 것은 상황에 맞게 사용해야한다. 잘못 사용하게 되면 오히려 성능이 낮아 질 수 있다.
        // 대부분의 경우는
        // 데이터가 정말 방대하게 큰 경우, 데이터 소스, 처리하는 내용, 등등 따라서 사용해야하는데
        // 이런 상황에 맞게 사용하기 힘들기 때문에 실제한번 성능 체크를 해보고 하는 결정하는 것이 현명하다.
        List<String> collect2 = names.parallelStream().map((s -> {
            System.out.println("s = " + s + Thread.currentThread().getName());
            return s.toUpperCase();
                })
        ).collect(Collectors.toList());

    }
}
