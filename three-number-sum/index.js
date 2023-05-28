// https://www.algoexpert.io/questions/three-number-sum

function threeNumberSum(array, targetSum) {
  if (array.length < 3) {
    return [];
  }

  const answer = [];

  //first sort the array
  array.sort((a, b) => a - b);

  //fix the first position
  for (let i = 0; i < array.length - 2; i++) {
    let left = i + 1;
    let right = array.length - 1;

    //left and right window
    while (left < right) {
      if (array[i] + array[left] > targetSum) {
        break;
      }

      const sum = array[i] + array[left] + array[right];
      if (sum === targetSum) {
        answer.push([array[i], array[left], array[right]]);
        left++;
        right--;
      } else if (sum > targetSum) {
        right--;
      } else {
        left++;
      }
    }
  }

  return answer;
}

// Do not edit the line below.
exports.threeNumberSum = threeNumberSum;

console.log(threeNumberSum([12, 3, 1, 2, -6, 5, -8, 6], 0)); // [[-8, 2, 6], [-8, 3, 5], [-6, 1, 5]]