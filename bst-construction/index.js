// Do not edit the class below except for
// the insert, contains, and remove methods.
// Feel free to add new properties and methods
// to the class.
class BST {
  constructor(value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }

  insert(value) {
    if (value < this.value) {
      if (!this.left) {
        this.left = new BST(value);
      } else {
        this.left.insert(value);
      }
    } else {
      if (!this.right) {
        this.right = new BST(value);
      } else {
        this.right.insert(value);
      }
    }

    return this;
  }

  contains(value) {
    let temp = this;
    
    while (temp) {
      if (value === temp.value) {
        return true;
      }

      if (value < temp.value) {
        temp = temp.left;
        continue;
      }

      temp = temp.right;
    }

    return false;
  }

  remove(value, parent = null) {
    if (value < this.value) {
      if (this.left) {
        this.left.remove(value, this);
      }
    } else if (value > this.value) {
      if (this.right) {
        this.right.remove(value, this);
      }
    } else {
      if (this.left && this.right) { //node has 2 children
        this.value = this.right.getMinValue(); //replace this node with min value of right subtree.
        this.right.remove(this.value, this);
      } else if (!parent) { //remove the root node
        if (this.left) { //default to replace with left subtree
          this.value = this.left.value;
          this.right = this.left.right;
          this.left = this.left.left;
        } else if (this.right) { //if no left subtree try right
          this.value = this.right.value;
          this.right = this.right.right;
          this.left = this.right.left;
        } else { //no trees, single node tree, don't allow it
        }
      } else if (parent.left === this) {
        parent.left = this.left ? this.left : this.right;
      } else if (parent.right === this) {
        parent.right = this.left ? this.left : this.right;
      }
    }
    
    return this;
  }

  getMinValue() {
    if (!this.left) {
      return this.value;
    }

    return this.left.getMinValue();
  }
}

// Do not edit the line below.
exports.BST = BST;
