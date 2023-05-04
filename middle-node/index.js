// https://www.algoexpert.io/questions/middle-node

// This is an input class. Do not edit.
class LinkedList {
  constructor(value) {
    this.value = value;
    this.next = null;
  }
}

exports.LinkedList = LinkedList;

function middleNode(linkedList) {
  let p1 = linkedList;
  let p2 = linkedList;

  while (p2 && p2.next) {
    p1 = p1.next;
    p2 = p2.next.next;
  }

  return p1;
}

// Do not edit the line below.
exports.middleNode = middleNode;

const one = new LinkedList(1);
const two = new LinkedList(2);
const three = new LinkedList(3);
const four = new LinkedList(4);
const five = new LinkedList(5);
const six = new LinkedList(6);

console.log(middleNode(one)); // 1
one.next = two;
console.log(middleNode(one)); // 2 (1 -> 2)
two.next = three;
console.log(middleNode(one)); // 2 (1 -> 2 -> 3)
three.next = four;
console.log(middleNode(one)); // 3 (1 -> 2 -> 3 -> 4)
four.next = five;
console.log(middleNode(one)); // 3 (1 -> 2 -> 3 -> 4 -> 5)
five.next = six;
console.log(middleNode(one)); // 4 (1 -> 2 -> 3 -> 4 -> 5 -> 6)