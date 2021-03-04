package com.koobin.etc;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class ParallelSort {
    public static void main(String[] args) {
        /**
         * 알고리즘 효율은 같지만 속도는 좀 더 빠르게 정렬을 할 수 있다.
         */
        int size = 1500;
        int[] numbers = new int[size];
        Random random = new Random();
        // 랜덤한 값을 채움
        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());

        long start = System.nanoTime();
        Arrays.sort(numbers); // 쓰레드 하나를 사용한다. 기본적으로 쿽정렬 log(n)
        System.out.println("serial sorting took " + (System.nanoTime() - start));

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());

        start = System.nanoTime();
        Arrays.parallelSort(numbers); //
        System.out.println("serial sorting took " + (System.nanoTime() - start));
    }
}
