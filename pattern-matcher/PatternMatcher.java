// https://www.algoexpert.io/questions/pattern-matcher

import java.util.*;

class PatternMatcher {
  public static String[] patternMatcher(String pattern, String str) {
    boolean flipped = false;
    if (pattern.charAt(0) != 'x') {
      pattern = flip(pattern);
      flipped = true;
    }

    String[] answer = new String[2];
    int xCount = 0;
    int yCount = 0;
    int firstYPos = Integer.MAX_VALUE;
    boolean found = false;

    for (int i = 0; i < pattern.length(); i++) {
      char c = pattern.charAt(i);
      if (c == 'y') {
        yCount++;
        firstYPos = Math.min(firstYPos, i);
      } else {
        xCount++;
      }
    }

    if (yCount == 0) {
      found = helperOnlyX(xCount, pattern, str, answer);
    } else {
      for (int xLength = 1; xLength < str.length(); xLength++) {
        int totalYLength = str.length() - (xLength * xCount);
        if (totalYLength % yCount != 0) {
          continue;
        }

        int yLength = totalYLength / yCount;
        if (yLength > 0 && helper(pattern, str, xLength, yLength, firstYPos, answer)) {
          found = true;
          break;
        }
      }
    }

    if (!found) {
      return new String[] {};
    }

    if (flipped) {
      String temp = answer[0];
      answer[0] = answer[1];
      answer[1] = temp;
    }

    return answer;
  }

  private static boolean helperOnlyX(int xCount, String pattern, String str, String[] answer) {
    if (str.length() % xCount != 0) {
      return false;
    }

    int xLength = str.length() / xCount;
    String x = str.substring(0, xLength);
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < xCount; i++) {
      builder.append(x);
    }

    if (builder.toString().equals(str)) {
      answer[0] = x;
      answer[1] = "";
      return true;
    }

    return false;
  }

  private static boolean helper(String pattern, String str, int xLength, int yLength, int firstYPos,
      String[] answer) {
    String x = str.substring(0, xLength);
    String y = str.substring(xLength * firstYPos, xLength * firstYPos + yLength);

    StringBuilder builder = new StringBuilder();
    for (char c : pattern.toCharArray()) {
      if (c == 'x') {
        builder.append(x);
      } else {
        builder.append(y);
      }
    }

    if (builder.toString().equals(str)) {
      answer[0] = x;
      answer[1] = y;
      return true;
    }

    return false;
  }

  private static String flip(String pattern) {
    StringBuilder builder = new StringBuilder();
    for (Character c : pattern.toCharArray()) {
      if (c == 'x') {
        builder.append('y');
      } else {
        builder.append('x');
      }
    }

    return builder.toString();
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(patternMatcher("xyyy", "banana"))); // ["", ""]
    System.out.println(Arrays.toString(patternMatcher("xyx", "thisshouldobviouslybewrong"))); // ["", ""]
    System.out.println(Arrays.toString(patternMatcher("xxy", "dogdogcat"))); // ["dog", "cat"]
    System.out.println(Arrays.toString(patternMatcher("x", "banana"))); // ["banana", ""]
    System.out.println(Arrays.toString(patternMatcher("y", "banana"))); // ["", "banana"]
    System.out.println(Arrays.toString(patternMatcher("xyx", "dogcatdog"))); // ["dog", "cat"]
    System.out.println(Arrays.toString(patternMatcher("yxy", "dogcatdog"))); // ["cat", "dog"]
    System.out.println(Arrays.toString(patternMatcher("xxyxxy", "gogopowerrangergogopowerranger"))); // ["go",
                                                                                                     // "powerranger"]
  }
}
