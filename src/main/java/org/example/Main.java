package org.example;

import java.util.*;

public class Main {
    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();
    public static final int ROUTESCOUNT = 1000;

    public static void main(String[] args) {
        for (int i = 0; i < ROUTESCOUNT; i++) {
            new Thread(() -> {
                String tempRoute = generateRoute("RLRFR", 100);
                int k = (int) countR(tempRoute);
                synchronized (sizeToFreq) {
                    sizeToFreq.put(k, sizeToFreq.getOrDefault(k, 0) + 1);
                }
            }).start();
        }

        Map.Entry<Integer, Integer> maxEntry = Collections.max(sizeToFreq.entrySet(), Map.Entry.comparingByValue());
        System.out.println("Самое частое количество повторений " +
                maxEntry.getKey() +
                " (встретилось " + maxEntry.getValue() + " раз)");
        System.out.println("Другие размеры:");
        for (Map.Entry<Integer, Integer> entry:sizeToFreq.entrySet()) {
            System.out.println("- " + entry.getKey() + " (" + entry.getValue() + " раз)");
        }
    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }

    public static long countR(String route) {
        return route.chars()
                .filter(c -> c == 'R')
                .count();
    }
}