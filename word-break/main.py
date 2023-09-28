# https://leetcode.com/problems/word-break/

import re
from collections import defaultdict, deque
from functools import cache
from typing import List


class TrieNode:
    def __init__(self):
        self.children = defaultdict(TrieNode)
        self.is_word = False

    def add_words(self, words):
        for word in words:
            self.add_word(word)

    def add_word(self, word):
        temp = self
        for c in word:
            temp = temp.children[c]

        temp.is_word = True


class Solution:
    # METHOD 1 - String copy (TLE)
    # Optimize slightly by filtering words and sorting by length descending to try
    # and always get the longest match first
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        wordDict = [word for word in wordDict if word in s]
        self.wordDict = sorted(wordDict, key=len, reverse=True)
        return self.helper(s)

    def helper(self, s):
        if len(s) == 0:
            return True

        for word in self.wordDict:
            if s.startswith(word):
                if self.helper(s[len(word) :]):
                    return True

        return False

    # METHOD 2: BFS
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        wordSet = set(wordDict)
        queue = deque([0])
        seen = set()

        while queue:
            start = queue.popleft()

            if start == len(s):
                return True

            for end in range(start + 1, len(s) + 1):
                if end in seen:
                    continue

                if s[start:end] in wordSet:
                    queue.append(end)
                    seen.add(end)

        return False

    # # METHOD 3: DP (backwards)
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        @cache
        def dp(i):
            if i < 0:
                return True

            for word in wordDict:
                # Working backwards, if we've matched a word, keep going
                # @cache parameter provides memoization
                if s[i - len(word) + 1 : i + 1] == word and dp(i - len(word)):
                    return True

            return False

        return dp(len(s) - 1)

    # # METHOD 4: DP (forewards)
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        dp = [False] * len(s)

        for i in range(len(s)):
            for word in wordDict:
                if i < len(word) - 1:
                    continue

                if i == len(word) - 1 or dp[i - len(word)]:
                    if s[i - len(word) + 1 : i + 1] == word:
                        dp[i] = True
                        break

        return dp[-1]

    # METHOD 5: TRIE
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        root = self.buildTrie(wordDict)
        dp = [False] * len(s)

        for i in range(len(s)):
            if i != 0 and not dp[i - 1]:
                continue

            curr = root
            for j in range(i, len(s)):
                c = s[j]
                if c not in curr.children:
                    break

                curr = curr.children[c]
                if curr.is_word:
                    dp[j] = True

        return dp[-1]

    def buildTrie(self, wordDict):
        root = TrieNode()
        root.add_words(wordDict)
        return root


s = Solution()
print(s.wordBreak("leetcode", ["leet", "code"]))  # True
print(s.wordBreak(s="applepenapple", wordDict=["apple", "pen"]))  # True
print(
    s.wordBreak(s="catsandog", wordDict=["cats", "dog", "sand", "and", "cat"])
)  # False
