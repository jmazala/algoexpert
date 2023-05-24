// https://www.algoexpert.io/questions/valid-starting-city

import java.util.*;

class ValidStartingCity {

  public static int validStartingCity(int[] distances, int[] fuel, int mpg) {
    for (int startingCity = 0; startingCity < distances.length; startingCity++) {
      if (canLoopFromCity(startingCity, distances, fuel, mpg)) {
        return startingCity;
      }
    }

    return -1;
  }

  private static boolean canLoopFromCity(int startingCity, int[] distances, int[] fuel, int mpg) {
    int citiesVisited = 0;
    int citiesToVisit = distances.length - 1;
    int milesRemaining = 0;

    while (citiesVisited < citiesToVisit) {
      int pos = (startingCity + citiesVisited) % distances.length;
      milesRemaining += (fuel[pos] * mpg) - distances[pos];
      if (milesRemaining < 0) {
        return false;
      }
      citiesVisited++;
    }
    return true;
  }

  public int validStartingCity2(int[] distances, int[] fuel, int mpg) {
    int validStartingCity = 0;
    int minMilesRemaining = Integer.MAX_VALUE;
    int milesRemaining = 0;

    for (int i = 0; i < distances.length; i++) {
      if (milesRemaining < minMilesRemaining) {
        validStartingCity = i;
        minMilesRemaining = milesRemaining;
      }

      milesRemaining += (fuel[i] * mpg) - distances[i];
    }

    return validStartingCity;
  }

  public static void main(String[] args) {
    System.out
        .println(ValidStartingCity.validStartingCity(new int[] { 5, 25, 15, 10, 15 }, new int[] { 1, 2, 1, 0, 3 }, 10));
  }
}
