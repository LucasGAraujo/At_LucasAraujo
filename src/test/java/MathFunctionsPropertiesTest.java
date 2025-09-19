
import net.jqwik.api.*;
import net.jqwik.api.constraints.*;
import org.example.MathFunctions;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MathFunctionsPropertiesTest {
    private final MathFunctions mathFunctions = new MathFunctions(null);

    @Property
    void multiplyByTwo_alwaysReturnsEven(@ForAll int number) {
        int result = mathFunctions.MultiplyByTwo(number);
        assertThat(result % 2).isEqualTo(0);
    }

    @Provide
    Arbitrary<Integer> positiveNumbers() {
        return Arbitraries.integers().between(1, 1000);
    }

    @Property
    void generateMultiplicationTable_allMultiplesOfNumber(@ForAll("positiveNumbers") int number,
                                                          @ForAll @IntRange(min = 1, max = 20) int limit) {
        int[] table = mathFunctions.generateMultiplicationTable(number, limit);
        for (int value : table) {
            assertThat(value % number).isEqualTo(0);
        }
    }

    @Provide
    Arbitrary<Integer> primeNumbers() {
        return Arbitraries.integers().between(2, 1000)
                .filter(mathFunctions::IsPrime);
    }

    @Property
    void isPrime_onlyDivisibleBy1AndItself(@ForAll("primeNumbers") int prime) {
        for (int i = 2; i < prime; i++) {
            assertThat(prime % i).isNotEqualTo(0);
        }
    }

    @Provide
    Arbitrary<int[]> intArrays() {
        return Arbitraries.integers().between(-1000, 1000)
                .array(int[].class).ofMinSize(1).ofMaxSize(50);
    }

    @Property
    void calculateAverage_betweenMinAndMax(@ForAll("intArrays") int[] numbers) {
        double average = mathFunctions.calculateAverage(numbers);
        int min = Arrays.stream(numbers).min().orElseThrow();
        int max = Arrays.stream(numbers).max().orElseThrow();
        assertThat(average).isGreaterThanOrEqualTo(min)
                .isLessThanOrEqualTo(max);
    }
}
