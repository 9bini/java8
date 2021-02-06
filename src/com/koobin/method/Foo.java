package com.koobin.method;

public interface Foo {

    // 일반적인 인터페이스의 메소드 선언
    void printName();

    String getName();
    //  구현한 인스턴스들에게 공통적으로 해줬으면 하는 기능이 있을 때 사용된다.
    /*
     void printNameUpperCase(); 단순히 인터페이스에 메소드를 추가할 경우
     구현체은 해당 메소드를 구현하지 않아 컴파일 애러 발생한다.
     그래서 java8에서는 이것을 해결할 수 있는 기본 메소드 (Default Methods)를 제공한다.
     */
    // 인터페이스에 메소드 선언이 아니라 구현체를 제공하는 방법
    // 해당 인터페이스를 구현한 클래스를 깨트리지 않고 새 기능을 추가할 수 있다.
    // 기본 메소드는 구현체가 모르게 추가된 기능으로 그만큼 리스크가 있다.
    //컴파일 에러는 아니지만 구현체에 따라 런타임 에러가 발생할 수 있다. ex)nullpointexception

    /**
     * 반드시 문서화 할 것. (@implSpec 자바독 태그 사용)
     * @implSpec 이 구현체는 getname()으로 가져온 문장열을 대문자로 바꿔 출력한다.
     */
    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase());
    }

    // Object가 제공하는 기능 (equals, hasCode)는 기본 메소드로 제공할 수 없다.
    // 컴파일에러 발생한다.
    // 구현체가 재정의해야 한다.
    /*
    default String toString(){
        return null;
    }
     */

    // 스태틱 메소드
    // 해당 타입 관련 헬퍼 또는 유틸리티 메소드를 제공할 때 인터페이스에 스태틱 메소드를 제공할 수 있다.
    static void printMyName(){
        System.out.println("9bin");
    }

}
