def findClosestValueInBst(tree, target):
  answer = float("inf")
    
  while (tree is not None):
    difference = abs(tree.value - target)
    if (difference == 0):
      return tree.value

    if (difference < abs(target - answer)):
      answer = tree.value

    if (tree.value < target):
      tree = tree.right
    else:
      tree = tree.left
  
  return answer