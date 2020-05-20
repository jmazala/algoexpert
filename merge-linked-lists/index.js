// This is an input class. Do not edit.
class LinkedList {
  constructor(value) {
    this.value = value;
    this.next = null;
  }
}

//SHUFFLING POINTERS
function mergeLinkedLists(headOne, headTwo) {
  //going to merge headTwo into headOne

  //need 3 pointers:  
  let previous = null; //previous in first list
  let p1 = headOne; //current in first list
  let p2 = headTwo; // current in second list

  while (p1 && p2) {
    //since we're merging into list 1, just advance both pointers
    if (p1.value < p2.value) {
      previous = p1;
      p1 = p1.next;
      continue;
    }

    //edge case when we're NOT at the head
    if (previous) {
      previous.next = p2;
    }

    previous = p2;
    p2 = p2.next;
    previous.next = p1;
  }

  if (!p1) {
    previous.next = p2;
  }

  return headOne.value < headTwo.value ? headOne : headTwo;
}

//DUMMY NODE
// function mergeLinkedLists(headOne, headTwo) {
//   const dummy = new LinkedList(0); //placeholder
//   let tail = dummy; //temp end of the list

//   while (true) {
//     if (!headOne) {
//       tail.next = headTwo;
//       break;
//     }

//     if (!headTwo) {
//       tail.next = headOne;
//       break;
//     }

//     if (headOne.value <= headTwo.value) {
//       tail.next = headOne;
//       headOne = headOne.next;
//     } else {
//       tail.next = headTwo;
//       headTwo = headTwo.next;
//     }

//     tail = tail.next;
//   }

//   return dummy.next;
// }

// Do not edit the line below.
exports.LinkedList = LinkedList;
exports.mergeLinkedLists = mergeLinkedLists;
