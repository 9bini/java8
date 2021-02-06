package com.koobin.method;

// printNameUpperCase는 Bar, Foo 둘다 가지고 있기 때문에 충돌 발생
// 구현체에서 기본 매소드 재정의해야한다.
public class FooAndBar implements Bar, Foo{
    @Override
    public void printNameUpperCase() {
        System.out.println(name.toUpperCase());
    }


    String name = "FooAndBar";

    @Override
    public void printName() {
        System.out.println(name);
    }

    @Override
    public String getName() {
        return name;
    }
}
