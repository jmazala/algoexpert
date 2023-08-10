# https://www.algoexpert.io/questions/airport-connections

from typing import List


class Node:
    def __init__(self, name):
        self.name = name
        self.neighbors = []
        self.isReachable = True
        self.unreachableConnections = []


def airportConnections(
    airports: List[str], routes: List[List[str]], startingAirport: str
):
    graph = createGraph(airports, routes)
    unreachableNodes = getUnreachableNodes(graph, airports, startingAirport)
    markUnreachableConnections(graph, unreachableNodes)
    return getMinNewConnections(graph, unreachableNodes)


def getMinNewConnections(graph: dict, unreachableNodes: List["Node"]) -> int:
    unreachableNodes.sort(key=lambda x: len(x.unreachableConnections), reverse=True)

    numNewConnections = 0
    for node in unreachableNodes:
        if node.isReachable:
            continue

        numNewConnections += 1

        for neighbor in node.unreachableConnections:
            graph[neighbor].isReachable = True

    return numNewConnections


def markUnreachableConnections(graph: dict, unreachableNodes: List["Node"]):
    for node in unreachableNodes:
        airport = node.name
        unreachableConnections = []
        dfsAndAddUnreachableConnection(graph, airport, unreachableConnections, set())
        node.unreachableConnections = unreachableConnections


def dfsAndAddUnreachableConnection(graph, airport, unreachableConnections, visited):
    if airport in visited or graph[airport].isReachable:
        return

    unreachableConnections.append(airport)
    visited.add(airport)

    for neighbor in graph[airport].neighbors:
        dfsAndAddUnreachableConnection(graph, neighbor, unreachableConnections, visited)


def getUnreachableNodes(
    graph: dict, airports: List[str], startingAirport: str
) -> List["Node"]:
    visited = set()
    dfs(graph, startingAirport, visited)

    unreachable = []

    for airport in airports:
        if airport in visited:
            continue

        node = graph[airport]
        node.isReachable = False
        unreachable.append(node)

    return unreachable


def dfs(graph: dict, airport: str, visited: set) -> None:
    if airport in visited:
        return

    visited.add(airport)

    for neighbor in graph[airport].neighbors:
        dfs(graph, neighbor, visited)


def createGraph(airports: List[str], routes: List[List[str]]) -> dict:
    graph = {}
    for airport in airports:
        graph[airport] = Node(airport)

    for [src, dst] in routes:
        graph[src].neighbors.append(dst)

    return graph


airports = [
    "BGI",
    "CDG",
    "DEL",
    "DOH",
    "DSM",
    "EWR",
    "EYW",
    "HND",
    "ICN",
    "JFK",
    "LGA",
    "LHR",
    "ORD",
    "SAN",
    "SFO",
    "SIN",
    "TLV",
    "BUD",
]
routes = [
    ["DSM", "ORD"],
    ["ORD", "BGI"],
    ["BGI", "LGA"],
    ["SIN", "CDG"],
    ["CDG", "SIN"],
    ["CDG", "BUD"],
    ["DEL", "DOH"],
    ["DEL", "CDG"],
    ["TLV", "DEL"],
    ["EWR", "HND"],
    ["HND", "ICN"],
    ["HND", "JFK"],
    ["ICN", "JFK"],
    ["JFK", "LGA"],
    ["EYW", "LHR"],
    ["LHR", "SFO"],
    ["SFO", "SAN"],
    ["SFO", "DSM"],
    ["SAN", "EYW"],
]

startingAirport = "LGA"

print(airportConnections(airports, routes, startingAirport))  # 3
