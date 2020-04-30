// This is an input class. Do not edit.
class LinkedList {
  constructor(value) {
    this.value = value;
    this.next = null;
  }
}

//can assume we always have the kth node in the list
function removeKthNodeFromEnd(head, k) {
  //this will work for the last element.  but not the first
  //two pointers.  one to get length of
  let prev = head;
  let short = head;
  let long = head;
  let skipPrev = true;

  for (let i = 0; i < k; i++) {
    long = long.next;
  }

  if (!long) {
    head.value = head.next.value;
    head.next = head.next.next;
  }

  while (long) {
    long = long.next;
    short = short.next;

    if (skipPrev) {
      skipPrev = false;
    } else {
      prev = prev.next;
    }
  }

  //short is on element to remove
  //prev is right before it
  //long is null
  prev.next = short.next;
  return head;
}

// Do not edit the line below.
exports.LinkedList = LinkedList;
exports.removeKthNodeFromEnd = removeKthNodeFromEnd;

for (let i = 1; i <= 10; i++) {
  head = new LinkedList(0);
  head.next = new LinkedList(1);
  head.next.next = new LinkedList(2);
  head.next.next.next = new LinkedList(3);
  head.next.next.next.next = new LinkedList(4);
  head.next.next.next.next.next = new LinkedList(5);
  head.next.next.next.next.next.next = new LinkedList(6);
  head.next.next.next.next.next.next.next = new LinkedList(7);
  head.next.next.next.next.next.next.next.next = new LinkedList(8);
  head.next.next.next.next.next.next.next.next.next = new LinkedList(9);
  const arr = [];
  let answer = removeKthNodeFromEnd(head, i);
  while (answer) {
    arr.push(answer.value);
    answer = answer.next;
  }

  console.log(arr.join(' -> '));
}