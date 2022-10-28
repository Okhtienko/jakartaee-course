package com.technology.twosums;

import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class TwoSumsTest {

  private final TwoSums twoSums = new TwoSums();

  @ParameterizedTest
  @MethodSource("arrayAndIntProvider")
  void shouldReturnIndicesOfTheTwoNumbers(final int[] incomingNumbers, final int desiredValue) {
    int[] actual = twoSums.calculate(incomingNumbers, desiredValue);
    assertNotNull(actual);
  }

  private static Stream<Arguments> arrayAndIntProvider() {
    return Stream.of(
        arguments(new int[]{2, 7, 11, 15}, 9),
        arguments(new int[]{3, 2, 4}, 6),
        arguments(new int[]{3, 3}, 6)
    );
  }

  @ParameterizedTest
  @MethodSource("nullAndIntProvider")
  void shouldReturnNullWhenIncomingNumbersNull(final int[] incomingNumbers, final int desiredValue) {
    int[] actual = twoSums.calculate(incomingNumbers, desiredValue);
    assertNull(actual);
  }

  private static Stream<Arguments> nullAndIntProvider() {
    return Stream.of(
        arguments(null, 9),
        arguments(null, 6),
        arguments(null, 6)
    );
  }
}
