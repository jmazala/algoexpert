# https://www.algoexpert.io/questions/two-edge-connected-graph


from typing import List


# METHOD 1 - DFS
#
# TIME: O(V + E) (modified DFS)
# SPACE: O(V)
#   O(V) for arrivalTimes
#   Recursive stack O(V)
def twoEdgeConnectedGraph(edges: List[List[int]]) -> bool:
    if len(edges) == 0:
        return True  # Empty graphs are 2 edge connected

    arrivalTimes = [-1] * len(edges)
    start = 0

    if dfs(start, -1, 0, arrivalTimes, edges) == -1:
        return False  # We found a bridge

    return -1 not in arrivalTimes


# Returns the minimum arrival time of any accessible ancestor
def dfs(
    current: int,
    parent: int,
    currentTime,
    arrivalTimes: List[int],
    edges: List[List[int]],
) -> int:
    arrivalTimes[current] = currentTime
    minArrivalTime = currentTime

    for neighbor in edges[current]:
        # see if one of the destination has already been visited
        if arrivalTimes[neighbor] == -1:
            minArrivalTime = min(
                minArrivalTime,
                dfs(neighbor, current, currentTime + 1, arrivalTimes, edges),
            )
        elif neighbor != parent:
            # This node has already been visited and thus its
            # minimum arrival time has already been calculated
            minArrivalTime = min(minArrivalTime, arrivalTimes[neighbor])

    # Ensure that the min arrival time of an ancestor is less than
    # the min arrival time of this node
    # If they're the same, we've found a bridge (except starting node)
    if minArrivalTime == currentTime and parent != -1:
        return -1

    return minArrivalTime


print(
    twoEdgeConnectedGraph([[1, 2, 3], [0, 2], [0, 1], [0, 4, 5], [3, 5], [3, 4]])
)  # False

print(twoEdgeConnectedGraph([[1], [0, 2, 3], [1, 3], [1, 2]]))  # False

print(
    twoEdgeConnectedGraph([[1, 2, 5], [0, 2], [0, 1, 3], [2, 4, 5], [3, 5], [0, 3, 4]])
)  # True
