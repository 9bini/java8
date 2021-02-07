package com.koobin.optional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class OptionalIntro {
    public static void main(String[] args) {
        List<OnlineClass1> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass1(1, "spring boot", true));
        springClasses.add(new OnlineClass1(2, "spring data jpa", true));
        springClasses.add(new OnlineClass1(3, "spring mvc", false));
        springClasses.add(new OnlineClass1(4, "spring core", false));
        springClasses.add(new OnlineClass1(5, "rest api development", false));

        OnlineClass1 spring_boot = new OnlineClass1(1, "spring boot", true);

        /*
        // 기본적으로 아래와 같이 null 체크
        // 문제점은 null 체크를 깜박할 수 있다.
        // null 리턴하는 것 자체가 문제
        Duration studyDuration = spring_boot.getProgress().getStudyDuration();
        // Progress가 null이라 에러 발생
        System.out.println(studyDuration);
         */

        /*
        Progress progress = spring_boot.getProgress();
        if (progress != null){
            System.out.println(progress.getStudyDuration());
        }
         */

        Optional.of(10); // 박싱 언박싱이 일어난다고하는데 api를 확인하자.
        // public static <T> T requireNonNull(T obj) {
        //        if (obj == null)
        //            throw new NullPointerException();
        //        return obj;
        //    }
        OptionalInt.of(10);
        // private OptionalInt(int value) {
        //        this.isPresent = true;
        //        this.value = value;
        //    }
        // 최종적으로 호출되는 것을 보면 이런것이 박싱인가 싶은데
        // 확실한 것은 OptionalInt이 Optional에 비해 가볍다라는 것



    }
}
