package test;

import java.util.stream.Stream;

public class Tesing {

    public static void main(String[] args) {

        String ss = "123";
        char c = 'a' + 1;
        String t = "";
        System.out.println(c);
        System.out.println(Long.parseLong("12") + Integer.parseInt(String.valueOf(ss.charAt(1))));
        System.out.println(t.length());

        Stream.of("little", "red", "riding", "hood")
        .parallel()
        .map(s -> {
            System.out.println("map: " + s + " " + Thread.currentThread().getName());
            return s + "_";
        })
        .filter(s -> {
            System.out.println("filter: " + s + " " + Thread.currentThread().getName());
            return s.length() > 3;
        })
        .reduce((s1, s2) -> {
            System.out.println("reducer: " + s1 + " " + Thread.currentThread().getName());
            return s1.length() > s2.length() ? s1 : s2;

        });
    }


}