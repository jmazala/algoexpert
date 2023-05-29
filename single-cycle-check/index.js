//simpler O(n) time and O(1) space
function hasSingleCycle(array) {
  let index = 0;
  let numVisited = 0;

  while (numVisited < array.length) {
    if (numVisited > 0 && index === 0) {
      return false;
    }
    numVisited++;

    index = (index + array[index]) % array.length;
    if (index < 0) {
      index = array.length + index;
    }
  }

  return index === 0;
}

// // Do not edit the line below.
exports.hasSingleCycle = hasSingleCycle;

//0, 2, 3, 5, 1, 4, 0
console.log(hasSingleCycle([2, 3, 1, -4, -4, 2])); //true
//0, 2, 1, 0
console.log(hasSingleCycle([2, 2, -1])); //true
//0, 2, 1, 0
console.log(hasSingleCycle([2, 2, 2])); //true
console.log(hasSingleCycle([0, 1, 1, 1, 1])); //false
//1, 2, 4, 8, 
console.log(hasSingleCycle([1, 2, 3, 4, -2, 3, 7, 8, -26])); //true
console.log(hasSingleCycle([1, -1, 1, -1])); //false;