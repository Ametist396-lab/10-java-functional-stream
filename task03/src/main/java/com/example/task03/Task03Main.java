package com.example.task03;

import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class Task03Main {

    public static void main(String[] args) {
        findMinMax(
                Stream.of(2, 9, 5, 4, 8, 1, 3),
                Integer::compareTo,
                (min, max) -> System.out.println("min: " + min + " / max: " + max)
        );
        // min: 1 / max: 9
    }

    public static <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {

        // Собираем в массив из двух элементов, чтобы обойти effectively final
        Object[] result = stream
                .map(t -> (T) t)
                .collect(
                        () -> new Object[]{null, null},          // supplier
                        (acc, t) -> {                            // accumulator
                            if (acc[0] == null || order.compare((T) acc[0], t) > 0) acc[0] = t;
                            if (acc[1] == null || order.compare((T) acc[1], t) < 0) acc[1] = t;
                        },
                        (a, b) -> {                              // combiner
                            if (b[0] != null && (a[0] == null || order.compare((T) a[0], (T) b[0]) > 0)) a[0] = b[0];
                            if (b[1] != null && (a[1] == null || order.compare((T) a[1], (T) b[1]) < 0)) a[1] = b[1];
                        }
                );

        @SuppressWarnings("unchecked")
        T min = (T) result[0];
        @SuppressWarnings("unchecked")
        T max = (T) result[1];
        minMaxConsumer.accept(min, max);
    }
}