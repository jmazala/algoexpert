// https://www.algoexpert.io/questions/longest-peak

function longestPeak(array) {
  let answer = 0;

  //measure increasing until the top, recording the length
  //measure decreasing until the bottom, adding to the length
  let i = 1;

  while (i < array.length - 1) {
    const isPeak = array[i] > array[i - 1] && array[i] > array[i + 1];

    if (!isPeak) {
      i++;
      continue;
    }

    let left = i - 1;
    while (left > 0 && array[left - 1] < array[left]) {
      left--;
    }

    let right = i + 1;
    while (right < array.length - 1 && array[right + 1] < array[right]) {
      right++;
    }

    const peakLength = right - left + 1;
    answer = Math.max(answer, peakLength);
    i = right;
  }

  return answer;
}

// Do not edit the line below.
exports.longestPeak = longestPeak;

console.log(longestPeak([1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3])); //6