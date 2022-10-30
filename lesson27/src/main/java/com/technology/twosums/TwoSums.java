package com.technology.twosums;

import java.util.HashMap;
import java.util.Map;

public class TwoSums {

  public int[] calculate(final int[] incomingNumbers, final int desiredValue) {
    if (incomingNumbers == null) {
      return null;
    }

    final Map<Integer, Integer> existingNumbers = new HashMap<>();
    for (int i = 0; i < incomingNumbers.length; i++) {
      final int expectedNumber = desiredValue - incomingNumbers[i];

      if (existingNumbers.containsKey(expectedNumber)) {
        return new int[]{existingNumbers.get(expectedNumber), i};
      }

      existingNumbers.put(incomingNumbers[i], i);
    }
    // In case there is no solution, we'll just return null
    return null;
  }
}
