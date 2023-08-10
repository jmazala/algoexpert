# https://www.algoexpert.io/questions/knuth-morris-pratt-algorithm


# TIME: O(n + m)
#   O(m) to make patterns
#     Any time j moves forward, i moves forward
#     When j moves back, i doesn't move back
#     So it's bounded by O(2m) = O(m) because however much we've advanced, we can only go back that length
#   O(n) to detect pattern
#     i index only moves forward
#     j can only be moved back m spaces hence upper bound is O(2m)
# SPACE: O(m)
#   O(m) for patterns
#   O(1) for pointers
def knuthMorrisPrattAlgorithm(string: str, substring: str) -> bool:
    # patterns[i] = index of a previously matching pattern to revert to in case of matching failure
    patterns = buildPatternArray(substring)
    return findMatch(string, substring, patterns)


# O(m)
def buildPatternArray(substring: str) -> [int]:
    patterns = [-1] * len(substring)
    i = 1
    j = 0

    while i < len(substring):
        # if these two chars match, there's a repeatable pattern
        if substring[i] == substring[j]:
            patterns[i] = j
            i += 1
            j += 1

        # We're not at the start, jump back j to the last pattern we recognized
        elif j > 0:
            j = patterns[j - 1] + 1
        else:
            i += 1

    return patterns


# O(n + m)
def findMatch(string: str, substring: str, patterns: [int]) -> bool:
    # compare substring[i] to substring[j]
    i = 0
    j = 0

    while i < len(string):
        # Optimization to stop early if we've run out of characters
        if i + len(substring) - j > len(string):
            break

        if string[i] == substring[j]:
            if j == len(substring) - 1:
                return True

            i += 1
            j += 1
        elif j == 0:
            i += 1
        else:
            # up until now, str[:i] = substring[:j]
            # go back until we've recognized a pattern and go 1 character more to restart search
            j = patterns[j - 1] + 1

    return False


print(knuthMorrisPrattAlgorithm("aefoaefcdaefcdaed", "aefcdaed"))  # True
