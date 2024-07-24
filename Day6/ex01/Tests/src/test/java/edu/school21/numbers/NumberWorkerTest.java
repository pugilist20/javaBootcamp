package edu.school21.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest {
    private NumberWorker worker;

    @BeforeEach
    void setUp() {
        worker = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 5, 17})
    void isPrimeForPrimes(int num) {
        Assertions.assertTrue(worker.isPrime(num));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 125})
    void isPrimeForNotPrimes(int num) {
        Assertions.assertFalse(worker.isPrime(num));
    }

    @ParameterizedTest
    @ValueSource(ints = {-25, 0, 1})
    void isPrimeForIncorrectNumbers(int num) {
        Assertions.assertThrows(IllegalNumberException.class, () -> worker.isPrime(num));
    }

    @ParameterizedTest
    @CsvFileSource(resources="/data.csv")
    void digitsSum(int num, int expectedNum){
        Assertions.assertEquals(expectedNum, worker.digitsSum(num));
    }

}
