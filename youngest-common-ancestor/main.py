# This is an input class. Do not edit.
class AncestralTree:
  def __init__(self, name):
    self.name = name
    self.ancestor = None

#topAncestor is the oldest person.  its ancestor is None
#this is really a binary tree problem. except we start from the bottom
#root is topAncestor
#we don't have children info, only parent info
def getYoungestCommonAncestor(topAncestor, descendantOne, descendantTwo):
  #we could traverse the tree to see who is lower
  oneDepth = 0
  twoDepth = 0
  temp = descendantOne
  
  while (temp.name != topAncestor.name):
    temp = temp.ancestor
    oneDepth += 1
  
  temp = descendantTwo
  
  while (temp.name != topAncestor.name):
    temp = temp.ancestor
    twoDepth += 1
  
  #make up for the depth distance, move the lower one up the tree to equal depth
  for i in range(max(oneDepth, twoDepth) - min(oneDepth, twoDepth)):
    if (oneDepth > twoDepth):
      descendantOne = descendantOne.ancestor
    else:
      descendantTwo = descendantTwo.ancestor
  
  #keep going up the tree until they're equal
  while (descendantOne.name != descendantTwo.name):
    descendantOne = descendantOne.ancestor
    descendantTwo = descendantTwo.ancestor
  
  return descendantOne


if __name__ == '__main__':
  a = AncestralTree('A')
  b = AncestralTree('B')
  c = AncestralTree('C')
  d = AncestralTree('D')
  e = AncestralTree('E')
  f = AncestralTree('F')
  g = AncestralTree('G')
  h = AncestralTree('H')
  i = AncestralTree('I')
  b.ancestor = a
  c.ancestor = a
  d.ancestor = b
  e.ancestor = b
  f.ancestor = c
  g.ancestor = c
  h.ancestor = d
  i.ancestor = d
  print(getYoungestCommonAncestor(a, e, i)) #b