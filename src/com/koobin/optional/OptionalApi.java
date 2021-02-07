package com.koobin.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalApi {
    public static void main(String[] args) {

        List<OnlineClassOS> onlineClasses = new ArrayList<>();
        onlineClasses.add(new OnlineClassOS(1, "online boot", true));
        onlineClasses.add(new OnlineClassOS(5, "rest api development", false));
        Optional<OnlineClassOS> online = onlineClasses.stream()
                .filter(oco -> oco.getTitle().startsWith("springOptional"))
                .findFirst();
        boolean presentOnline = online.isPresent();
        System.out.println("presentOnline = " + presentOnline);

        List<OnlineClassOS> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClassOS(1, "springOptional boot", true));
        springClasses.add(new OnlineClassOS(5, "rest api development", false));

        Optional<OnlineClassOS> springOptional = springClasses.stream()
                .filter(oco -> oco.getTitle().startsWith("springOptional"))
                .findFirst();
        boolean presentSpring = springOptional.isPresent();
        System.out.println("presentSpring = " + presentSpring);

        // 값이 들어가 있으면 문제가 없지만
        // 들어 있지 않으면 NoSuchElementException 발생
            // 런타임 에러 계열이다.
        OnlineClassOS springClass = springOptional.get();
        System.out.println("onlineClassOptional.getTitle() = " + springClass.getTitle());

        // Optional에 들어있는 값 걸러내기
        Optional<OnlineClassOS> emptyOptional = springClasses.stream()
                .filter(oco -> oco.getTitle().startsWith("spring"))
                .findFirst();
        /*
        OnlineClassOS emptySpringClass = emptyOptional.get();
        System.out.println("emptySpringOptional.getTitle() = " + emptySpringClass.getTitle());
        */
        
        springOptional.ifPresent(spring -> System.out.println("spring.getTitle() = " + spring.getTitle()));

        // springOptional가 null이 아니더라도 createNewClass필수 적으로 실행하기 때문에
        // orElseGet을 사용해야
        OnlineClassOS onlineClassOSElse = springOptional.orElse(createNewClass());

        // Supplier 공급자를 메소드 레퍼런스 혹은 람다, {} 등으로 표현해도된다.
        OnlineClassOS onlineClassOSElseGet = springOptional.orElseGet(OptionalApi::createNewClass);

        // ptional에 값이 있으면 가졍고 없는 경우 에러를 던져라.
        OnlineClassOS onlineClassOSThrow = springOptional.orElseThrow(IllegalArgumentException::new);

        Optional<Integer> integer = springOptional.map(OnlineClassOS::getId);

        Optional<Optional<Progress>> map = springOptional.map(OnlineClassOS::getProgress);
        // 1대1 매핑, 결과도 하나일 때 사용
        Optional<Progress> flatMap = springOptional.flatMap(OnlineClassOS::getProgress);


    }

    private static OnlineClassOS createNewClass() {
        System.out.println("creating new online class");
        return new OnlineClassOS(10, "New Class", false);
    }
}
