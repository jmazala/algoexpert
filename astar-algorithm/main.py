# https://www.algoexpert.io/questions/a*-algorithm

import heapq

DIRECTIONS = [[1, 0], [-1, 0], [0, 1], [0, -1]]
DELIMITER = ","
OBSTACLE = 1
FREE = 0


class Node:
    def getID(self):
        return DELIMITER.join([str(self.i), str(self.j)])

    def __init__(self, i, j, val):
        self.i = i
        self.j = j
        self.val = val
        self.cameFrom = None

        # g score
        self.distanceFromStart = float("inf")

        # f score
        self.estimatedDistanceToEnd = float("inf")

    def __lt__(self, other):
        return self.getHScore() < other.getHScore()

    def getFScore(self):
        return self.distanceFromStart

    def getGScore(self):
        return self.estimatedDistanceToEnd

    def getHScore(self):
        return self.getFScore() + self.getGScore()


def calculateManhattanDistance(nodeA, nodeB):
    return abs(nodeA.i - nodeB.i) + abs(nodeA.j - nodeB.j)


def isObstacle(i, j, graph):
    return graph[i][j] == OBSTACLE


def reconstructPath(node):
    path = []

    while node is not None:
        path.insert(0, node)
        node = node.cameFrom

    return path


def initializeNodes(graph):
    nodes = []

    for i, row in enumerate(graph):
        nodes.append([])

        for j, value in enumerate(row):
            nodes[i].append(Node(i, j, value))

    return nodes


# TIME: O(mn * log(mn))
#   O(m*n) for all nodes
#   O(log(m*n)) for heap insert
#   O(1) for looping through neighbors
#   O(log(m*n)) to overwrite f,g,h heap scores when necessary
# SPACE:
# O(m*n) for minHeap
def aStarAlgorithm(startRow, startCol, endRow, endCol, graph):
    m = len(graph)
    n = len(graph[0])
    nodes = initializeNodes(graph)
    start = nodes[startRow][startCol]
    end = nodes[endRow][endCol]

    start.distanceFromStart = 0
    start.estimatedDistanceToEnd = calculateManhattanDistance(start, end)
    end.estimatedDistanceToEnd = 0

    # Determine next node through F score (heap)
    minHeap = [start]

    while len(minHeap) > 0:
        curNode = heapq.heappop(minHeap)

        if curNode.estimatedDistanceToEnd == 0:
            break

        curI = curNode.i
        curJ = curNode.j
        curDistanceFromStart = curNode.distanceFromStart

        for [dI, dJ] in DIRECTIONS:
            [nextI, nextJ] = [curI + dI, curJ + dJ]
            if (
                nextI < 0
                or nextJ < 0
                or nextI == m
                or nextJ == n
                or isObstacle(nextI, nextJ, graph)
            ):
                continue

            nextNode = nodes[nextI][nextJ]

            # Already visited this node in a more efficient way
            if nextNode.distanceFromStart <= curDistanceFromStart + 1:
                continue

            nextNode.cameFrom = curNode
            nextNode.distanceFromStart = curDistanceFromStart + 1

            nextNode.estimatedDistanceToEnd = (
                curDistanceFromStart + 1 + calculateManhattanDistance(nextNode, end)
            )

            if nextNode not in minHeap:
                heapq.heappush(minHeap, nextNode)
            else:
                heapq.heapify(minHeap)

    return (
        [[node.i, node.j] for node in reconstructPath(end)]
        if end.cameFrom is not None
        else []
    )


print(
    aStarAlgorithm(
        0,
        1,
        4,
        3,
        [
            [0, 0, 0, 0, 0],
            [0, 1, 1, 1, 0],
            [0, 0, 0, 0, 0],
            [1, 0, 1, 1, 1],
            [0, 0, 0, 0, 0],
        ],
    )
)
