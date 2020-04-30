function getPermutations(array) {
  const answer = [];
  helper(array, 0);

  function helper(array, i) {
    if (i === (array.length - 1)) {
      answer.push(array.slice());
      return;
    }

    for (let j = i; j < array.length; j++) {
      swap(array, i, j);
      helper(array, i + 1);
      swap(array, i, j);
    }
  }

  return answer;
}

function swap(array, i, j) {
  const temp = array[i];
  array[i] = array[j];
  array[j] = temp;
}

// Do not edit the line below.
exports.getPermutations = getPermutations;


console.log(getPermutations([1, 2, 3]));