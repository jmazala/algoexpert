// https://www.algoexpert.io/questions/sum-of-linked-lists

// This is an input class. Do not edit.
class LinkedList {
  constructor(value) {
    this.value = value;
    this.next = null;
  }
}

function sumOfLinkedLists(linkedListOne, linkedListTwo) {
  let carry = false;
  let result = new LinkedList(0);
  const head = result;

  while (true) {
    let sum = 0;
    if (carry) {
      sum += 1;
      carry = false;
    }

    if (linkedListOne) {
      sum += linkedListOne.value;
      linkedListOne = linkedListOne.next;
    }

    if (linkedListTwo) {
      sum += linkedListTwo.value;
      linkedListTwo = linkedListTwo.next;
    }

    if (sum > 9) {
      carry = true;
      sum -= 10
    }

    result.value = sum;

    if (linkedListOne || linkedListTwo || carry) {
      result.next = new LinkedList(0);
      result = result.next;
    } else {
      break;
    }
  }

  return head;
}

// Do not edit the lines below.
exports.LinkedList = LinkedList;
exports.sumOfLinkedLists = sumOfLinkedLists;

function printLinkedList(result) {
  let output = '';
  while (result) {
    output += `${result.value} -> `;
    result = result.next;
  }

  console.log(output.substring(0, output.length - 4));
}

const linkedListOne = new LinkedList(2);
linkedListOne.next = new LinkedList(4);
linkedListOne.next.next = new LinkedList(7);
linkedListOne.next.next.next = new LinkedList(1);
const linkedListTwo = new LinkedList(9);
linkedListTwo.next = new LinkedList(4);
linkedListTwo.next.next = new LinkedList(5);

printLinkedList(sumOfLinkedLists(linkedListOne, linkedListTwo)); // '1 -> 9 -> 2 -> 2'
printLinkedList(sumOfLinkedLists(new LinkedList(5), new LinkedList(9))); // '4 -> 1'



