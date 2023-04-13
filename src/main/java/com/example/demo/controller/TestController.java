package com.example.demo.controller;

import io.swagger.models.auth.In;

import java.util.ArrayList;
import java.util.List;

public class TestController {

    public static void main(String[] args) {
        short sss;
        String s1 = "abc";
        String s2 = "abc";
        System.out.println(s1 == s2); //true
        System.out.println(s1.equals(s2));//true
        System.out.println("===============");
        String s3 = new String("abc");
        String s4 = new String("abc");
        System.out.println(s3 == s4);//false
        System.out.println(s3.equals(s4));//true
        System.out.println("===============");
        String s5 = s3.intern();
        System.out.println(s3 == s5);//false
        System.out.println(s3.intern() == s5);//true
        System.out.println(s3.intern().equals(s5));//true
        System.out.println("===============");
        System.out.println(s1 == s3);//false
        System.out.println(s1 == s3.intern());//true
        System.out.println(s1 == s5);//true

        System.out.println("------");
        System.out.println(getType(1));
        System.out.println(getType(1.1));

        new ArrayList<>();

        Object[] test = new Integer[16];
        System.out.println("test.length");
        System.out.println(test.length);
        test[0] = 1;
        test[1] = 1;
        test[2] = 1;
        System.out.println(test.length);

        List<Integer> testList = new ArrayList<>();
        System.out.println("===========");
        System.out.println(testList.size());
        System.out.println(testList.toArray().length);
        testList.add(1);
        testList.add(1);
        testList.add(1);
        System.out.println(testList.size());
        System.out.println(testList.toArray().length);

    }

    private static String getType(Object o) {
        return o.getClass().getName();
    }
}
