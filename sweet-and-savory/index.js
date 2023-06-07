// https://www.algoexpert.io/questions/sweet-and-savory

function sweetAndSavory(dishes, target) {
  const answer = [];

  dishes.sort((a, b) => a - b);

  let sweet = 0;
  let savory = dishes.length - 1;

  while (sweet < dishes.length - 1 && dishes[sweet] < 0 && savory > 0 && dishes[savory] > 0) {
    const balance = dishes[sweet] + dishes[savory];

    // we never want too savory
    if (balance > target) {
      savory--;
      continue;
    }

    if (balance === target) {
      return [dishes[sweet], dishes[savory]];
    }

    if (!answer.length) {
      answer[0] = dishes[sweet];
      answer[1] = dishes[savory];
    }

    const answerBalance = answer[0] + answer[1];
    if ((target - answerBalance) > (target - balance)) {
      answer[0] = dishes[sweet];
      answer[1] = dishes[savory];
    }

    sweet++;
  }



  if (answer.length === 0) {
    return [0, 0];
  }

  return answer;
}

// Do not edit the line below.
exports.sweetAndSavory = sweetAndSavory;

console.log(sweetAndSavory([-3, -5, 1, 7], 8)); // [-3, 7]
console.log(sweetAndSavory([3, 5, 7, 2, 6, 8, 1], 10)); // [0, 0]
console.log(sweetAndSavory([2, 5, -4, -7, 12, 100, -25], 10)); // [-4, 12]
console.log(sweetAndSavory([2, 5, -4, -7, 12, 100, -25], -20)); // [-25, 5]