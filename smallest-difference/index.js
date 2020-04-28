//could try sorting both arrays
function smallestDifference(arrayOne, arrayTwo) {
  arrayOne.sort((a, b) => a - b);
  arrayTwo.sort((a, b) => a - b);

  let i = 0;
  let j = 0;

  let answer = [];
  let difference = Infinity;

  while (i < arrayOne.length && j < arrayTwo.length) {
    if (arrayOne[i] === arrayTwo[j]) {
      return [arrayOne[i], arrayTwo[j]];
    }

    const currentDifference = Math.abs(arrayOne[i] - arrayTwo[j]);
    if (currentDifference < difference) {
      answer = [arrayOne[i], arrayTwo[j]];
      difference = currentDifference;
    }

    if (arrayOne[i] < arrayTwo[j]) {
      i++;
    } else {
      j++;
    }
  }

  return answer;
}

//could try looking for min distance and doing some hash searches
function smallestDifference(arrayOne, arrayTwo) {
  let difference = 0;
  const set = new Set();
  let counts = 0;

  arrayTwo.forEach(i => set.add(i));

  while (true) {
    for (let i = 0; i < arrayOne.length; i++) {
      if (set.has(arrayOne[i] - difference)) {
        return [arrayOne[i], arrayOne[i] - difference];
      }
    }

    difference++;
  }
}

// Do not edit the line below.
exports.smallestDifference = smallestDifference;

console.log(smallestDifference([-1, 5, 10, 20, 28, 3], [26, 134, 135, 15, 17])); // [28, 26]
console.log(smallestDifference([10, 1000, 9124, 2142, 59, 24, 596, 591, 124, -123, 530], [-1441, -124, -25, 1014, 1500, 660, 410, 245, 530]));