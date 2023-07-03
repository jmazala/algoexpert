// https://www.algoexpert.io/questions/iterative-in-order-traversal

function iterativeInOrderTraversal(tree, callback) {
  // left node right
  let previous = null;
  let current = tree;

  while (current) {
    let next;

    // we're traversing down the tree
    if (!previous || previous === current.parent) {
      // favor left child
      if (current.left) {
        next = current.left;
      } else {
        callback(current);
        next = current.right ? current.right : current.parent;
      }
    } else if (previous === current.left) {
      callback(current);
      // we're traversing upwards, haven't yet hit right child
      next = current.right ? current.right : current.parent;
    } else {
      // we're traversing upwards, have hit right child
      next = current.parent;
    }

    previous = current;
    current = next;
  }
}

// Do not edit the line below.
exports.iterativeInOrderTraversal = iterativeInOrderTraversal;
