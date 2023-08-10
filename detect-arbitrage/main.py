# https://www.algoexpert.io/questions/detect-arbitrage

import math
from typing import List


# METHOD 1 - DFS With Cycle Detection
# Traverse the graph starting with each currency, and search all possibilities for an arbitrage opportunity
# Stop DFS if we've found one
# This does a lot of repeated work as we detect the same sub-cycle multiples many times
def detectArbitrage(exchangeRates: List[List[float]]) -> bool:
    currencies = list(range(len(exchangeRates)))
    for start in currencies:
        if hasArbitrage(start, start, currencies, exchangeRates, 1, set(), True):
            return True

    return False


def hasArbitrage(
    start, current, currencies, exchangeRates, amount, seen: set, firstTime
):
    if not firstTime and current == start:
        if amount > 1:
            return True

        return False

    if current in seen:
        return False

    seen.add(current)

    for i in range(len(currencies)):
        if i == current or i != start and i in seen:
            continue

        if hasArbitrage(
            start,
            i,
            currencies,
            exchangeRates,
            amount * exchangeRates[current][i],
            seen,
            False,
        ):
            return True

        if i != start:
            seen.remove(i)

    return False


# METHOD 2 - Use Bellman-Ford algorithm to detect any negative-weight cycles
# TIME: O(n^3)
#   Run Bellman-Ford (n-1) + n = 2n times
#     Each step takes O(n^2) time because we look at every edge
# SPACE: O(n) to store distances for each node
#   This requires modifying the original graph to negative log
def detectArbitrage(exchangeRates: List[List[float]]) -> bool:
    # To make exchange rates edge weights, we must make them addable.
    # Do so with log properties log(a*b) = log(a) + log(b)
    logExchangeRates = convertExchangeRatesToLog(exchangeRates)
    return foundNegativeWeightCycle(logExchangeRates, 0)


# Run Bellman-Ford algorithm (on negative logs) n-1 times
# This should relax the entire graph, because in a graph with weighted edges
# No cycle can take more than n-1 edges without repeating nodes
# In a directed, weighted graph with n nodes, there will be (n(n-1))/2 edges
# Hence O(n^2) to visit every edge
# We can stop if we ran the algorithm and no edge was relaxed
# Because that means we already found the minimum cost to each node from start
# After relaxing every edge (in this graph there are max # of edges)
# We try to relax one more time.  If edges get relaxed, that means
# that a negative edge cycle exists in this graph, which translates to
# an arbitrage opportunity
def foundNegativeWeightCycle(graph: List[List[float]], start: int) -> bool:
    distancesFromStart = [float("inf")] * len(graph)
    distancesFromStart[start] = 0

    for _ in range(len(graph) - 1):
        # if no update occurred, there is no negative cycle
        if not relaxEdgesAndUpdateDistances(graph, distancesFromStart):
            return False

    return relaxEdgesAndUpdateDistances(graph, distancesFromStart)


def relaxEdgesAndUpdateDistances(graph: List[List[float]], distances: List[float]):
    updated = False

    for source, edges in enumerate(graph):
        for destination, weight in enumerate(edges):
            newDistanceToDestination = distances[source] + weight

            if newDistanceToDestination < distances[destination]:
                updated = True
                distances[destination] = newDistanceToDestination

    return updated


def convertExchangeRatesToLog(exchangeRates: List[List[float]]):
    converted = [[0] * len(exchangeRates) for _ in range(len(exchangeRates))]

    for i, row in enumerate(exchangeRates):
        for j, rate in enumerate(row):
            converted[i][j] = -math.log10(rate)

    return converted


print(
    detectArbitrage([[1, 0.8631, 0.5903], [1.1586, 1, 0.6849], [1.6939, 1.46, 1]])
)  # True

print(
    detectArbitrage(
        [[1, 0.5, 0.25, 2], [2, 1, 0.5, 4], [4, 2, 1, 8], [0.5, 0.25, 0.0125, 1]]
    )
)  #
