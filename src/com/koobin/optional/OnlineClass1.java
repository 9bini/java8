package com.koobin.optional;

import java.util.Optional;

public class OnlineClass1 {

    private Integer id;

    private String title;

    private boolean closed;


    public Progress progress;
    // 리턴값으로만 쓰기를 권장한다.
    public Optional<Progress> getProgress() {

        // 1. 클라이언트쪽 고통은 덜하지만 checked exception을 던지기 시작하면 에러 처리 강제
        // 2. 자바는 에러가 발생하면하면 stacked race를 찍는다.
        // 에러가 발생하기전 call stack을 거처서 에러를 발생하게 지에대한 대한 정보를 담게된다. = 리소스 사용
        // 에러를 처리할 때 사용하면 괜찮지만 로직을 처리할 떄는 에러는 사용하는 것은 좋은 것은 아니다.
        // if (this.progress == null)
        // throw new IllegalStateException();

        // Optional.of(progress); - progress가 null이 아니다라고 가정을 한다.
        // 만약 progress가 null일 경우 nullPointException이 발생한다.

        // 만약 null을 이런 리턴할 생각이면 Optional.empty()를 실행해라
        return Optional.ofNullable(progress);
    }

    // 메소드 매개변수 타입, 맵의 키 타입, 인스턴스 필드 타입으로 쓰지말자
    // 문법 적으로는 문제는 없다.

    // 추가적으로 Map에 키타입을 Optional 사용 시 권장되지 않는 방법 - 컨트렉티브(트징)를 깨트리는 일이다.
    // Map에 특징 중 하나가 "키타입은 null이 아니다"이다.

    // Progress를 optional을 사용시
    // Optional을 만든 목적이 메소드 리턴타입으로 쓰라는 용도로 만들어진거라 그밖에 용도를 쓰신다면
    // Optional을 만든 취지에 어긋나게 사용하는 것
    //public Optional<Progress> progress;

    // public void setProgress(Optional<Progress> progress) {
    // 체크 기능을 추가 해도 위험하다.
    // 파라매터로 null을 전달할 수 있다. => nullPointException 발생되어 optional 사용한것이 의미가 없다.
    // + 복잡도가 올라갈 뿐
    // if (progress != null)
    // progress.ifPresent(p -> this.progress = p);

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public OnlineClass1(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
