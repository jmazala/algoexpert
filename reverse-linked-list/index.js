// https://www.algoexpert.io/questions/reverse-linked-list
class LinkedList {
  constructor(value) {
    this.value = value;
    this.next = null;
  }
}

function reverseLinkedList(head) {
  if (!head || !head.next) {
    return head;
  }

  let previous = null;
  let current = head;

  while (current) {
    const next = current.next;
    current.next = previous;
    previous = current;
    current = next;
  }

  return previous;
}

// Do not edit the line below.
exports.reverseLinkedList = reverseLinkedList;

const head = new LinkedList(1);
head.next = new LinkedList(2);
head.next.next = new LinkedList(3);

let newHead = reverseLinkedList(head);
let output = '';
while (newHead) {
  output += newHead.val;
  newHead = newHead.next;
  if (newHead) {
    output += ' -> ';
  }
}

console.log(output);