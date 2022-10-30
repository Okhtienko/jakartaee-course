package com.technology.twosums;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class TwoSumsTest {

  private final TwoSums twoSums = new TwoSums();

  @ParameterizedTest
  @MethodSource("arrayAndIntProvider")
  void shouldReturnIndicesOfTheTwoNumbers(final int[] incomingNumbers, final int desiredValue, final int[] expected) {
    int[] actual = twoSums.calculate(incomingNumbers, desiredValue);
    assertArrayEquals(expected, actual);
  }

  private static Stream<Arguments> arrayAndIntProvider() {
    return Stream.of(
        arguments(new int[]{2, 7, 11, 15}, 9, new int[]{0, 1}),
        arguments(new int[]{3, 2, 4}, 6, new int[]{1, 2}),
        arguments(new int[]{3, 3}, 6, new int[]{0, 1}),
        arguments(null, 6, null)
    );
  }
}
