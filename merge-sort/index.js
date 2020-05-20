function mergeSort(array) {
  if (array.length < 2) {
    return array;
  }

  //divide it into sorted left and right halves
  const middle = Math.floor(array.length / 2);
  const left = mergeSort(array.slice(0, middle));
  const right = mergeSort(array.slice(middle));

  return merge(left, right);
}

function merge(a1, a2) {
  if (!a1 || !a1.length) {
    return a2;
  }

  if (!a2 || !a2.length) {
    return a1;
  }

  let result = [];

  let i = 0;
  let j = 0;

  while (i < a1.length && j < a2.length) {
    if (a1[i] < a2[j]) {
      result.push(a1[i++]);
    } else {
      result.push(a2[j++]);
    }
  }

  result = result.concat(a1.slice(i));
  result = result.concat(a2.slice(j));
  return result;
}

// Do not edit the line below.
exports.mergeSort = mergeSort;

console.log(mergeSort([8, 5, 2, 9, 5, 6, 3]));