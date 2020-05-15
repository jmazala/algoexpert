def knapsackProblem(items, capacity):
  # stored array is 0 to n items, 0 to c capacity
  values = [[0 for _ in range(capacity + 1)] for _ in range(len(items) + 1)]

  for i in range(1, len(items) + 1):
    for c in range(1, capacity + 1):
      value = items[i-1][0]
      weight = items[i-1][1]
      if (weight > c):
        values[i][c] = values[i - 1][c]
        continue
      
      remainingWeight = c - weight
      values[i][c] = max(values[i-1][c], value + values[i-1][remainingWeight])

  return [values[-1][-1], getItemsUsed(items, values)]

def getItemsUsed(items, values):
  #start in bottom right corner
  i = len(values) - 1
  j = len(values[0]) - 1
  itemsUsed = []

  while (i >= 0 and j >= 0):
    #if the value is different than the one above it, it means we took that item
    if (values[i][j] != values[i-1][j]):
      usedItemWeight = items[i-1][1]
      itemsUsed.append(i-1)
      j -= usedItemWeight
    
    i -= 1
  
  return itemsUsed

if __name__ == '__main__':
  print(knapsackProblem([[1, 2], [4, 3], [5, 6], [6, 7]], 10))