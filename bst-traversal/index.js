// https://www.algoexpert.io/questions/bst-traversal

//left, node, right
function inOrderTraverse(tree, array) {
  if (!tree) {
    return [];
  }

  inOrderTraverse(tree.left, array);
  array.push(tree.value);
  inOrderTraverse(tree.right, array);
  return array;
}

//node, left, right
function preOrderTraverse(tree, array) {
  if (!tree) {
    return [];
  }

  array.push(tree.value);
  preOrderTraverse(tree.left, array);
  preOrderTraverse(tree.right, array);
  return array;
}

//left, right, node
function postOrderTraverse(tree, array) {
  if (!tree) {
    return [];
  }

  postOrderTraverse(tree.left, array);
  postOrderTraverse(tree.right, array);
  array.push(tree.value);
  return array;
}

// Do not edit the lines below.
exports.inOrderTraverse = inOrderTraverse;
exports.preOrderTraverse = preOrderTraverse;
exports.postOrderTraverse = postOrderTraverse;
