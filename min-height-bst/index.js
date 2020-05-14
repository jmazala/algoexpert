function minHeightBst(array) {
  return helper(array, 0, array.length - 1);
}

function helper(array, start, end) {
  if (end < start) {
    return null;
  }

  const midIndex = Math.floor((start + end) / 2);
  const node = new BST(array[midIndex]);
  node.left = helper(array, start, midIndex - 1);
  node.right = helper(array, midIndex + 1, end);
  return node;
}

class BST {
  constructor(value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }

  insert(value) {
    if (value < this.value) {
      if (this.left === null) {
        this.left = new BST(value);
      } else {
        this.left.insert(value);
      }
    } else {
      if (this.right === null) {
        this.right = new BST(value);
      } else {
        this.right.insert(value);
      }
    }
  }
}

// Do not edit the line below.
exports.minHeightBst = minHeightBst;
