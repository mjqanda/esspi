package com.example.esspi;

import java.util.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainTest {

    public static void main(String[] args) {
        List<Integer> samplenum = Arrays.asList(1, 2, 3, 4);
        List<Integer> samplenum2 = Arrays.asList(3, 4, 5, 6);

        List<Integer> mergedNums = new ArrayList<>();

        mergedNums.addAll(samplenum);
        mergedNums.addAll(samplenum2);

        mergedNums.forEach(n -> log.info("mergednum ->" + n));

    }

}
