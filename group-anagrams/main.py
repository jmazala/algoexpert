# https://www.algoexpert.io/questions/group-anagrams

from typing import List


def groupAnagrams(words: List[str]) -> List[List[str]]:
    groups = {}

    for word in words:
        sortedWord = "".join(sorted(word))

        if sortedWord not in groups:
            groups[sortedWord] = []

        groups[sortedWord].append(word)

    return list(groups.values())


print(groupAnagrams(["yo", "act", "flop", "tac", "foo", "cat", "oy", "olfp"]))
