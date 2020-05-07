function sameBsts(arrayOne, arrayTwo) {
  if (arrayOne.length == 0 && arrayTwo.length == 0) {
    return true;
  }

  if (arrayOne[0] !== arrayTwo[0] || arrayOne.length !== arrayTwo.length) {
    return false;
  }

  const root = arrayOne.shift();
  arrayTwo.shift();

  const less1 = [];
  const less2 = [];
  const greater1 = [];
  const greater2 = [];
  for (i of arrayOne) {
    if (i < root) {
      less1.push(i);
    } else {
      greater1.push(i);
    }
  }

  for (i of arrayTwo) {
    if (i < root) {
      less2.push(i);
    } else {
      greater2.push(i);
    }
  }

  return sameBsts(less1, less2) && sameBsts(greater1, greater2);
}

// Do not edit the line below.
exports.sameBsts = sameBsts;
