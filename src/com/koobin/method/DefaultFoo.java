package com.koobin.method;

public class DefaultFoo implements Foo{
    // 구현체에서 기본 메소드 (Default Methods)를 재정의할 수 있다.
    @Override
    public void printNameUpperCase() {
        System.out.println(name.toUpperCase());
    }



    private String name;

    public DefaultFoo(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void printName() {
        System.out.println(name);
    }
}
