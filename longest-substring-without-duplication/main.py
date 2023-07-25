# https://www.algoexpert.io/questions/longest-substring-without-duplication


def longestSubstringWithoutDuplication(string: str) -> str:
    if len(string) < 2:
        return string

    lastSeen = {}
    longest = [0, 1]
    start = 0

    for i, char in enumerate(string):
        if char in lastSeen:
            start = max(start, lastSeen[char] + 1)
        if longest[1] - longest[0] < i - start + 1:
            longest = [start, i + 1]
        lastSeen[char] = i

    return string[longest[0] : longest[1]]


print(longestSubstringWithoutDuplication("clementisacap"))
