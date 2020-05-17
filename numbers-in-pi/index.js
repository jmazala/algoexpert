function numbersInPi(pi, numbers) {
  const set = new Set(numbers);
  let answer = Infinity;
  helper(0, 0);
  return answer === Infinity ? -1 : answer - 1;

  function helper(startIndex, numSpaces) {
    if (startIndex === pi.length) {
      return numSpaces;
    }

    for (let i = startIndex; i < pi.length; i++) {
      const prefix = pi.slice(startIndex, i + 1);
      if (set.has(prefix)) {
        const result = helper(i + 1, numSpaces + 1);
        answer = Math.min(answer, result);
      }
    }

    return Infinity;
  }
}

// Do not edit the line below.
exports.numbersInPi = numbersInPi;

console.log(numbersInPi("3141592653589793238462643383279", [
  "314159265358979323846",
  "26433",
  "8",
  "3279",
  "314159265",
  "35897932384626433832",
  "79"
])); //2

console.log(numbersInPi("3141592653589793238462643383279", [
  "3",
  "1",
  "4",
  "592",
  "65",
  "35",
  "8",
  "9793",
  "2384626",
  "55",
  "83279"
])); // 13
