package com.koobin.lambda;

import java.util.function.*;

public class Func<y> implements Function<Integer, Integer> {
    final int num = 1000;
     int num2 = 1000;
    @Override
    public Integer apply(Integer integer) {
        // 로컬 변수 캡처
        // final이거나 effective final 인 경우에만 참조할 수 있다.

            // final
        System.out.println(num);
            // effective final - 모습은 일반 변수와 동일하지만 값을 변경하는 곳이 없다면 final 취급한다.
        // final 키워드 사용하지 않은 변수를 익명 클래스 구현체 또는 람다에서 참조할 수 있다.
        System.out.println(num2);
        return integer + 10;
    }
        // 그렇지 않을 경우 concurrency 문제가 생길 수 있어서 컴파일가 방지한다.
        // num2++; // 값을 변경하는 곳이 에러가 발생하는 지 예상 못했다.

    // 람다 표련식 (인자 리스트) -> {바디}
    // 화상표 오른쪽에 함수 본문을 정의한다.
    UnaryOperator<Integer> plus10 = (i) -> {
        // 여러 줄인 경우에 { }를 사용해서 묶는다.
        System.out.println(i);
        return i + 10;
    };
    // 바디가 한 줄인 경우에 생략 가능, return도 생략 가능.
    UnaryOperator<Integer> plus11 = (i) -> i + 11;

    // 인자가 없을 때: ()
    // 인터페이스에서 인자를 받지 않아야 사용가능해서 다른 함수에 사용하기에는 제한 적이네
    Supplier<Integer> get11 = () -> 11;

    //인자가 한개일 때: (one) or one
    UnaryOperator<Integer> plus12 = i -> i + 11;

    // 인자가 여러개 일 때: (one, two)
    BinaryOperator<Integer> plus13 = (x,y) -> x + y + 13;

    // 인자의 타입은 생략 가능, 컴파일러가 추론(infer)하지만 명시할 수도 있다. (Integer one, Integer two)
    // 단순 명시만 가능하네
    // 주의 인자가 여러개 일 경우에는 여러개 인자 전부 추론을 명시 해야된다.
    BinaryOperator<Integer> plus14 = (Integer x, Integer y) -> x + y + 13;

    private void run(){
        int baseNumber = 10;

        // 로컬 클래스
        class LocalClass{
            void printBaseNumber(){
                // 쉐도윙 - 상위 스콥에 있는 변수명을 하위 스콥에서도 사용을 것
                int baseNumber = 11;
                System.out.println(baseNumber);
            }
        }
        // 익명 클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer baseNumber) {
                System.out.println(baseNumber);
            }
        };

        // 람다
        IntConsumer printIntConsumer = (i) -> {
            // int baseNumber = 11; //에러 발생
            System.out.println(i + baseNumber);
        };
        // 람다에서만 에러가 발생하는 이유 (쉐도윙 하지 않는 이유):
        // 로컬 클래스, 익명 클래스는 새로 스콥을 만들지만, 람다는 람다를 감싸고 있는 스콥과 같다.

    }
}
