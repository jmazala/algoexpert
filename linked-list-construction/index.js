// https://www.algoexpert.io/questions/linked-list-construction

// This is an input class. Do not edit.
class Node {
  constructor(value) {
    this.value = value;
    this.prev = null;
    this.next = null;
  }
}

// Feel free to add new properties and methods to the class.
class DoublyLinkedList {
  constructor() {
    this.head = null;
    this.tail = null;
  }

  toString() {
    let s = '';
    let currentNode = this.head;
    while (currentNode) {
      s += `${currentNode.value} <-> `;
      currentNode = currentNode.next;
    }

    return s.substring(0, s.length - 4);
  }

  nodeExistsInList(node) {
    let currentNode = this.head;
    while (currentNode) {
      if (currentNode === node) {
        return true;
      }

      currentNode = currentNode.next;
    }

    return false;
  }

  setHead(node) {
    if (this.head === node) {
      return;
    }

    if (this.nodeExistsInList(node)) {
      this.remove(node);
    }

    if (this.head) {
      this.insertBefore(this.head, node);
    }

    this.head = node;

    if (!this.tail) {
      this.tail = node;
    }
  }

  setTail(node) {
    if (this.tail === node) {
      return;
    }

    if (this.nodeExistsInList(node)) {
      this.remove(node);
    }

    if (this.tail) {
      this.insertAfter(this.tail, node);
    }

    this.tail = node;

    if (!this.head) {
      this.head = node;
    }
  }

  // INSERT a BEFORE y: x <-> a <-> y
  // x <-> y <-> z
  insertBefore(node, nodeToInsert) {
    if (this.nodeExistsInList(nodeToInsert)) {
      this.remove(nodeToInsert);
    }

    nodeToInsert.next = node;
    nodeToInsert.prev = node.prev;
    if (nodeToInsert.prev) {
      nodeToInsert.prev.next = nodeToInsert;
    }

    node.prev = nodeToInsert;

    if (this.head === node) {
      this.head = nodeToInsert;
    }
  }

  // INSERT a AFTER x:  x <-> a <-> y
  // x <-> y <-> z
  insertAfter(node, nodeToInsert) {
    if (this.nodeExistsInList(nodeToInsert)) {
      this.remove(nodeToInsert);
    }

    nodeToInsert.prev = node;
    nodeToInsert.next = node.next;

    if (nodeToInsert.next) {
      nodeToInsert.next.prev = nodeToInsert;
    }

    node.next = nodeToInsert;

    if (this.tail === node) {
      this.tail = nodeToInsert;
    }
  }


  // INSERT x at POSITION 2:  
  // a <-> b <-> c <-> d <-> e
  insertAtPosition(position, nodeToInsert) {
    if (position === 1) {
      return this.setHead(nodeToInsert);
    }

    let currentPosition = 1;
    let currentNode = this.head;

    while (currentPosition < position) {
      currentNode = currentNode.next;
      currentPosition++;
    }

    return this.insertBefore(currentNode, nodeToInsert);
  }

  removeNodesWithValue(value) {
    let currentNode = this.head;

    while (currentNode) {
      if (currentNode.value === value) {
        const temp = currentNode.next;
        this.remove(currentNode);
        currentNode = temp;
      } else {
        currentNode = currentNode.next;
      }
    }
  }

  // REMOVE y: x <-> z
  // x <-> y <-> z
  remove(node) {
    if (this.head === node) {
      this.head = node.next;
    }

    if (this.tail === node) {
      this.tail = node.prev;
    }

    if (node.prev) {
      node.prev.next = node.next;
    }

    if (node.next) {
      node.next.prev = node.prev;
    }

    delete this;
  }

  containsNodeWithValue(value) {
    let currentNode = this.head;

    while (currentNode) {
      if (currentNode.value === value) {
        return true;
      }

      currentNode = currentNode.next;
    }

    return false;
  }
}

// Do not edit the lines below.
exports.Node = Node;
exports.DoublyLinkedList = DoublyLinkedList;

function testOne() {
  const one = new Node(1);
  const two = new Node(2);
  const three = new Node(3);
  const four = new Node(4);
  const five = new Node(5);

  const list = new DoublyLinkedList();
  list.head = one;
  list.tail = five;
  one.next = two;
  two.prev = one;
  two.next = three;
  three.prev = two;
  three.next = four;
  four.prev = three;
  four.next = five;
  five.prev = four;
  list.tail = five;

  console.log(list.toString()); // 1 <-> 2 <-> 3 <-> 4 <-> 5

  const three2 = new Node(3);
  const three3 = new Node(3);
  const six = new Node(6);

  list.setHead(four);
  console.log(list.toString()); // 4 <-> 1 <-> 2 <-> 3 <-> 5

  list.setTail(six);
  console.log(list.toString()); // 4 <-> 1 <-> 2 <-> 3 <-> 5 <-> 6

  list.insertBefore(six, three);
  console.log(list.toString()); // 4 <-> 1 <-> 2 <-> 3 <-> 5 <-> 3 <-> 6

  list.insertAfter(six, three2);
  console.log(list.toString()); // 4 <-> 1 <-> 2 <-> 5 <-> 3 <-> 6 <-> 3

  list.insertAtPosition(1, three3);
  console.log(list.toString()); // 3 <-> 4 <-> 1 <-> 2 <-> 5 <-> 3 <-> 6 <-> 3

  list.removeNodesWithValue(3);
  console.log(list.toString()); // 4 <-> 1 <-> 2 <-> 5 <-> 6

  list.remove(two);
  console.log(list.toString()); // 4 <-> 1 <-> 5 <-> 6

  console.log(list.containsNodeWithValue(5)); // true
}

function testTwo() {
  const one = new Node(1);
  const two = new Node(2);
  const three = new Node(3);
  const four = new Node(4);
  const five = new Node(5);
  const six = new Node(6);
  const seven = new Node(7);
  const list = new DoublyLinkedList();

  list.setHead(one);
  list.insertAfter(one, two);
  list.insertAfter(two, three);
  list.insertAfter(three, four);
  list.insertAfter(four, five);
  list.insertAfter(five, six);
  list.insertAfter(six, seven);
  list.insertAtPosition(7, one);
  console.log(list.toString()); // 2 <-> 3 <-> 4 <-> 5 <-> 6 <-> 1 <-> 7 
  console.log(list.head);
  console.log(list.tail);
}

testOne();
testTwo();