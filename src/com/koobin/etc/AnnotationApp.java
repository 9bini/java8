package com.koobin.etc;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

@OverlapChicken("양념")
@OverlapChicken("마")
public class AnnotationApp {
    public static void main(@Chicken String[] args) throws @Chicken RuntimeException{
        List<@Chicken String> names = Arrays.asList("tgkoo");
        OverlapChicken[] annotationsByType = AnnotationApp.class.getAnnotationsByType(OverlapChicken.class);
        Arrays.stream(annotationsByType).forEach(c ->{
            System.out.println("c.value() = " + c.value());
        });
        ChickenContainer ch = AnnotationApp.class.getAnnotation(ChickenContainer.class);
        Arrays.stream(ch.value()).forEach(c ->{
            System.out.println("c.value() = " + c.value());
        });

    }

    static class FeelsLikeChicken<@Chicken T>{
        public static <@Chicken C> void print( C c){
            System.out.println(c);
        }
    };
}
