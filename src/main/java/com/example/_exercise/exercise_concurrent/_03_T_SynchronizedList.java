package com.example._exercise.exercise_concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _03_T_SynchronizedList {
    public static void main(String[] args) {
        //未加锁
        List<String> list = new ArrayList<>();

        //已加锁
        List<String> synchronizedList = Collections.synchronizedList(list);
    }
}
