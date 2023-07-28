# https://www.algoexpert.io/questions/valid-starting-city

from typing import List


# In a single pass, keep track of how much gas you have when you enter a city
# Whichever city has the least amount of gas must be starting city
def validStartingCity(distances: List[int], fuel: List[int], mpg: int) -> int:
    validCity = 0
    minMilesRemaining = float("inf")
    milesRemaining = 0

    for i in range(0, len(distances)):
        if milesRemaining < minMilesRemaining:
            validCity = i
            minMilesRemaining = milesRemaining

        milesRemaining += (fuel[i] * mpg) - distances[i]

    return validCity


print(validStartingCity([5, 25, 15, 10, 15], [1, 2, 1, 0, 3], 10))  # 4
