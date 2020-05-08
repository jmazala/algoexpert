#2 pass solution
def waterArea(heights):
  maxHeights = [0] * len(heights)
  leftMax = 0

  # calculate left max for each point
  for i in range(len(heights)):
    maxHeights[i] = leftMax
    leftMax = max(leftMax, heights[i])

  rightMax = 0
  # calculate min of left/right max for each point
  # also calculate water above this point
  for i in reversed(range(len(heights))):
    minHeight = min(rightMax, maxHeights[i])
    height = heights[i]
    
    if (height < minHeight):
      maxHeights[i] = minHeight - height
    else:
      maxHeights[i] = 0
  
    rightMax = max(rightMax, height)

  return sum(maxHeights)

# 3 pass solution
# def waterArea(heights):
#     leftRightMaxHeights = [
#         dict(left=float("-inf"), right=float("-inf")) for _ in range(len(heights))]
#     leftMax = float("-inf")
#     rightMax = float("-inf")

#     # calculate left max for each point
#     for i in range(1, len(heights)):
#         leftMax = max(leftMax, heights[i-1])
#         leftRightMaxHeights[i]["left"] = leftMax

#     # calculate right max for each point
#     for i in range(len(heights) - 2, -1, -1):
#         rightMax = max(rightMax, heights[i+1])
#         leftRightMaxHeights[i]["right"] = rightMax

#     answer = 0
#     # calculate the water level above each point
#     for i in range(0, len(heights)):
#         height = heights[i]
#         left = leftRightMaxHeights[i]["left"]
#         right = leftRightMaxHeights[i]["right"]

#         if left <= 0 or right <= 0:
#             continue

#         waterAtThisPoint = min(left, right) - height
#         answer += max(0, waterAtThisPoint)

#     return answer

print(waterArea([0, 8, 0, 0, 5, 0, 0, 10, 0, 0, 1, 1, 0, 3])) #48