def maxSumIncreasingSubsequence(array):
	dp = [None] * len(array)
	subsequences = [None] * len(array)
	
	dp[0] = array[0]
	subsequences[0] = [array[0]]
	
	answerMax = array[0]
	answerSubsequence = [array[0]]
	
	for i in range(1, len(array)):
		current = array[i]
		maxForI = array[i]
		subsequences[i] = [current]
		
		for j in range(0, i):
			if array[j] < current:
				if dp[j] + current > maxForI:
					maxForI = dp[j] + current
					subsequences[i] = subsequences[j] + [current]
			
			if maxForI > answerMax:
				answerMax = maxForI
				answerSubsequence = subsequences[i]
		dp[i] = maxForI
				
	return [answerMax, answerSubsequence]