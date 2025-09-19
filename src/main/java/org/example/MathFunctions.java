package org.example;

import java.util.Arrays;

public class MathFunctions {

    private final Object logger;

    public MathFunctions(Object logger) {
        this.logger = logger;
    }
    public int MultiplyByTwo(int number)
    {
        int result = number * 2;

        try {
            logger.getClass().getMethod("log", String.class, int[].class)
                    .invoke(logger, "MultiplyByTwo", new int[]{number, result});
        }  catch (Exception ignored) {}
        return result;
    }
    public int[] generateMultiplicationTable(int number, int limit) {
        int[] result = new int[limit];
        for (int i = 0; i < limit; i++) {
            result[i] = number * (i + 1);
        }
        try {
            logger.getClass().getMethod("log", String.class, int[].class)
                    .invoke(logger, "GenerateMultiplicationTable", result);
        } catch (Exception ignored) {}
        return result;
    }
    public boolean IsPrime(int number)
    {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++)
        {
            if (number % i == 0) return false;
        }
        return true;
    }
    public double calculateAverage(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty.");
        }
        double avg = Arrays.stream(numbers).average().orElseThrow();
        try {
            logger.getClass().getMethod("log", String.class, int[].class)
                    .invoke(logger, "CalculateAverage", numbers);
        } catch (Exception ignored) {}
        return avg;
    }
//    public interface MathLogger {
//        void log(String operation, int[] inputs);
//    }
}
