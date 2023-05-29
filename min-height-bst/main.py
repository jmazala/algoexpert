# https://www.algoexpert.io/questions/min-height-bst


def minHeightBst(array):
    return constructBST(array, 0, len(array) - 1)


def constructBST(array, start, end):
    if start > end:
        return None

    mid = (start + end) // 2
    node = BST(array[mid])
    node.left = constructBST(array, start, mid - 1)
    node.right = constructBST(array, mid + 1, end)
    return node


class BST:
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None

    def insert(self, value):
        if value < self.value:
            if self.left is None:
                self.left = BST(value)
            else:
                self.left.insert(value)
        else:
            if self.right is None:
                self.right = BST(value)
            else:
                self.right.insert(value)
