// https://www.algoexpert.io/questions/nth-fibonacci

function getNthFib(n) {
  let minusTwo = 0;
  let minusOne = 1;

  for (let i = 3; i <= n; i++) {
    const temp = minusTwo + minusOne;
    minusTwo = minusOne;
    minusOne = temp;
  }

  return (n > 1) ? minusOne : minusTwo;
}

// Do not edit the line below.
exports.getNthFib = getNthFib;
