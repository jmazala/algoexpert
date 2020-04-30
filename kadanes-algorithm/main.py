#O(1) space
def kadanesAlgorithm(array):
  current = array[0]
  answer = array[0]
  
  for i in range(1, len(array)):
    num = array[i]
    current = max(num, current + num)
    answer = max(current, answer)
  
  return answer

#O(n) space
# def kadanesAlgorithm(array):
#   if (len(array) == 0):
#     return 0
  
#   dp = [None] * len(array)
#   dp[0] = array[0]
#   answer = array[0]
  
#   for i in range(1, len(array)):
#     dp[i] = max(array[i] + dp[i-1], array[i])
#     answer = max(answer, dp[i])
  
#   return answer

print(kadanesAlgorithm([3, 5, -9, 1, 3, -2, 3, 4, 7, 2, -9, 6, 3, 1, -5, 4])) #19
print(kadanesAlgorithm([-1, -2, -3, -4, -5, -6, -7, -8, -9, -10])) #-1