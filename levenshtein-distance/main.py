# min number of operations on the first string to obtain the second string
# you can either insert, delete, substitute

#STORING ONLY LAST 2 ROWS
#O(nm) time, O(min(n, m)) space
def levenshteinDistance(str1, str2):
	#find the smallest of the 2 strings
	small = str1 if len(str1) < len(str2) else str2
	big = str1 if len(str1) >= len(str2) else str2
	
	#0th row should be [0, 1, 2, 3 ...]
	#we want to store only the last 2 rows of data
	#so we should minimize space by minimizing columns
	evenEdits = [x for x in range(len(small) + 1)]
	oddEdits = [None for x in range(len(small) + 1)]
	
	#big + 1 rows
	for i in range(1, len(big) + 1):
		if i % 2 == 1:
			currentEdits = oddEdits
			previousEdits = evenEdits
		else:
			currentEdits = evenEdits
			previousEdits = oddEdits
		currentEdits[0] = i
		for j in range(1, len(small) + 1):
			if big[i-1] == small[j-1]:
				currentEdits[j] = previousEdits[j-1]
			else:
				currentEdits[j] = 1 + min(previousEdits[j-1], currentEdits[j-1], previousEdits[j])
	
	return evenEdits[-1] if len(big) % 2 == 0 else oddEdits[-1]


#STORING ENTIRE MATRIX
# def levenshteinDistance(str1, str2):
#   # each row i compares substring of str1[:i]
#   # each col j compares substring of str2[:j]
#   # first row will be 0..j
#   # first col will be 0..i
#   dp = [[x for x in range(len(str2) + 1)] for _ in range(len(str1) + 1)]

#   for i in range(0, len(str1) + 1):
#     dp[i][0] = i

#   # dp[i-1][j-1] represents an edit
#   # dp[i-1][j] or dp[i][j-1] represents adding a char to either string
#   for i in range(1, len(str1) + 1):
#     for j in range(1, len(str2) + 1):
#       if str1[i - 1] == str2[j - 1]:
#         dp[i][j] = dp[i-1][j-1]
#       else:
#         dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1

#   return dp[-1][-1]

print(levenshteinDistance("abc", "yabd")) # 2 . insert 'y', substitute 'c' for 'd'
print(levenshteinDistance("biting", "mitten")) # 4 b to m, i to t, n to e, g to n