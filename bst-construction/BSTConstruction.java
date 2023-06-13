// https://www.algoexpert.io/questions/bst-construction

class BSTConstruction {
  static class BST {
    public int value;
    public BST left;
    public BST right;

    public BST(int value) {
      this.value = value;
    }

    private int getMinValue() {
      if (this.left != null) {
        return this.left.getMinValue();
      }

      return this.value;
    }

    private int getMinValueIterative() {
      BST node = this;
      while (node.left != null) {
        node = node.left;
      }

      return node.value;
    }

    public BST insert(int value) {
      if (value < this.value) {
        if (this.left == null) {
          this.left = new BST(value);
        } else {
          left.insert(value);
        }
      } else {
        if (this.right == null) {
          this.right = new BST(value);
        } else {
          right.insert(value);
        }
      }

      return this;
    }

    public BST insertIterative(int value) {
      BST current = this;

      while (true) {
        if (value < current.value) {
          if (current.left == null) {
            current.left = new BST(value);
            break;
          }

          current = current.left;
        } else {
          if (current.right == null) {
            current.right = new BST(value);
            break;
          } else {
            current = current.right;
          }
        }
      }

      return this;
    }

    public boolean contains(int value) {
      if (this.value == value) {
        return true;
      }

      if (value < this.value) {
        if (this.left == null) {
          return false;
        }

        return this.left.contains(value);
      }

      if (this.right == null) {
        return false;
      }

      return this.right.contains(value);
    }

    public boolean containsIterative(int value) {
      BST node = this;

      while (node != null) {
        if (value == node.value) {
          return true;
        }

        if (value < node.value) {
          node = node.left;
        } else {
          node = node.right;
        }
      }

      return false;
    }

    public BST remove(int value) {
      this.removeHelper(value, null);
      return this;
    }

    public BST removeIterative(int value) {
      this.removeHelperIterative(value, null);
      return this;
    }

    public void removeHelper(int value, BST parent) {
      if (value < this.value) {
        if (this.left != null) {
          this.left.removeHelper(value, this);
        }
        return;
      }

      if (value > this.value) {
        if (this.right != null) {
          this.right.removeHelper(value, this);
        }
        return;
      }

      /*
       * Removing a node somewhere in the middle of tree.
       * get the smallest greater than or equal to value from right subtree
       * swap values with that value (which will be a leaf node) and remove that leaf
       * node
       * Note: this works recursively even if the tree contains n
       * more nodes with the same value
       */
      if (this.left != null && this.right != null) {
        this.value = right.getMinValue();
        right.removeHelper(this.value, this);
        return;
      }

      if (parent == null) {
        // we are the only node in the tree. Don't do anything
        if (this.left == null && this.right == null) {
          return;
        }

        /*
         * we are removing the root node and we only have a left subtree
         * This tree becomes the left subtree
         */
        if (left != null) {
          this.value = this.left.value;
          this.right = this.left.right;
          this.left = this.left.left;
          return;
        }

        /*
         * we are removing the root node and we only have a right subtree
         * this tree becomes the right subtree
         */
        if (right != null) {
          this.value = this.right.value;
          this.left = this.right.left;
          this.right = this.right.right;
          return;
        }
      }

      // we are in the left tree of the parent
      if (parent.left == this) {
        if (this.left != null) {
          parent.left = this.left;
        } else {
          parent.left = this.right;
        }

        return;
      }

      // we are in the right tree of the parent
      if (parent.right == this) {
        if (this.left != null) {
          parent.right = this.left;
        } else {
          parent.right = this.right;
        }
      }
    }

    public void removeHelperIterative(int value, BST parent) {
      BST current = this;

      while (current != null) {
        if (value < current.value) {
          parent = current;
          current = current.left;
          continue;
        }

        if (value > current.value) {
          parent = current;
          current = current.right;
          continue;
        }

        if (current.left != null && current.right != null) {
          current.value = current.right.getMinValueIterative();
          current.right.removeHelperIterative(current.value, current);
          break;
        }

        if (parent == null) {
          if (current.left == null && current.right == null) {
            // can't remove the only node
            break;
          }

          if (current.left != null) {
            current.value = current.left.value;
            current.right = current.left.right;
            current.left = current.left.left;
            break;
          }

          if (current.right != null) {
            current.value = current.right.value;
            current.left = current.right.left;
            current.right = current.right.right;
            break;
          }
        }

        if (parent.left == current) {
          if (current.left != null) {
            parent.left = current.left;
          } else {
            parent.left = current.right;
          }

          break;
        }

        if (parent.right == current) {
          if (current.left != null) {
            parent.right = current.left;
          } else {
            parent.right = current.right;
          }

          break;
        }
      }
    }
  }

  public static void main(String[] args) {

  }
}
