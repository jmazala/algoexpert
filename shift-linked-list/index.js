// https://www.algoexpert.io/questions/shift-linked-list

//using my class for print() function
const { LinkedListNode } = require('../js-includes');

function shiftLinkedList(head, k) {
  if (k === 0 || !head || !head.next) {
    return head;
  }

  //find the length of the list to mod k by so we know
  //how much to actually shift.
  let length = 1;
  let lastElement = head;

  while (lastElement.next) {
    lastElement = lastElement.next;
    length++;
  }

  k = k % length;
  if (k === 0) {
    return head;
  }

  if (k < 0) {
    k += length;
  }

  //find the kth to last element.  this is the new head
  //find the last element and point it to the old head
  let kthElement = head;
  let beforeKthElement = head;

  for (let i = 0; i < length - k; i++) {
    kthElement = kthElement.next;
    if (i === 0) {
      continue;
    }

    beforeKthElement = beforeKthElement.next;
  }

  //remove link from k-1 to k
  beforeKthElement.next = null;
  lastElement.next = head;
  return kthElement;
}

// Do not edit the line below.
exports.shiftLinkedList = shiftLinkedList;

//original list is 0 -> 1 -> 2 -> 3 -> 4 -> 5
//length is 6
//shifting left 1 is shifting right 5
//shifting left 2 is shifting right 4

const head = new LinkedListNode(0);
head.next = new LinkedListNode(1);
head.next.next = new LinkedListNode(2);
head.next.next.next = new LinkedListNode(3);
head.next.next.next.next = new LinkedListNode(4);
head.next.next.next.next.next = new LinkedListNode(5);

const shifted = shiftLinkedList(head, 2);
console.log(shifted.print()); // 4 -> 5 -> 0 -> 1 -> 2 -> 3

const head2 = new LinkedListNode(0);
head2.next = new LinkedListNode(1);
head2.next.next = new LinkedListNode(2);
head2.next.next.next = new LinkedListNode(3);
head2.next.next.next.next = new LinkedListNode(4);
head2.next.next.next.next.next = new LinkedListNode(5);
const shifted2 = shiftLinkedList(head2, -1);
console.log(shifted2.print()); // 1 -> 2 -> 3 -> 4 -> 5 -> 0