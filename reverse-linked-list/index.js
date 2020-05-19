const { LinkedListNode } = require('../js-includes');

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

const head = new LinkedListNode(1);
head.next = new LinkedListNode(2);
head.next.next = new LinkedListNode(3);

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