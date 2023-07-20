// https://www.algoexpert.io/questions/maximize-expression

function maximizeExpression(array) {
  if (array.length < 4) {
    return 0;
  }

  const maxA = new Array(array.length - 1);
  maxA[0] = array[0];
  for (let i = 1; i < array.length - 1; i++) {
    maxA[i] = Math.max(maxA[i - 1], array[i]);
  }

  const maxAMinusB = new Array(array.length - 1);
  maxAMinusB[0] = -Infinity;
  maxAMinusB[1] = array[0] - array[1];

  for (let i = 2; i < array.length - 1; i++) {
    maxAMinusB[i] = Math.max(maxAMinusB[i - 1], maxA[i - 1] - array[i]);
  }

  const maxAMinusBPlusC = new Array(array.length - 1);
  maxAMinusBPlusC[0] = -Infinity;
  maxAMinusBPlusC[1] = -Infinity;
  maxAMinusBPlusC[2] = array[0] - array[1] + array[2];

  for (let i = 3; i < array.length - 1; i++) {
    maxAMinusBPlusC[i] = Math.max(maxAMinusBPlusC[i - 1], maxAMinusB[i - 1] + array[i]);
  }

  const maxAMinusBPlusCMinusD = new Array(array.length);
  maxAMinusBPlusCMinusD[0] = -Infinity;
  maxAMinusBPlusCMinusD[1] = -Infinity;
  maxAMinusBPlusCMinusD[2] = -Infinity;
  maxAMinusBPlusCMinusD[3] = array[0] - array[1] + array[2] - array[3];

  for (let i = 4; i < array.length; i++) {
    maxAMinusBPlusCMinusD[i] = Math.max(maxAMinusBPlusCMinusD[i - 1], maxAMinusBPlusC[i - 1] - array[i]);
  }

  return maxAMinusBPlusCMinusD[maxAMinusBPlusCMinusD.length - 1];
}

// Do not edit the line below.
exports.maximizeExpression = maximizeExpression;

console.log(maximizeExpression([3, 6, 1, -3, 2, 7])); // 4