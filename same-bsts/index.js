function sameBsts(arrayOne, arrayTwo) {
  if (arrayOne.length == 0 && arrayTwo.length == 0) {
    return true;
  }

  if (arrayOne[0] !== arrayTwo[0] || arrayOne.length !== arrayTwo.length) {
    return false;
  }

  const root = arrayOne.shift();
  arrayTwo.shift();

  return sameBsts(arrayOne.filter(i => i < root), arrayTwo.filter(i => i < root)) && sameBsts(arrayOne.filter(i => i >= root), arrayTwo.filter(i => i >= root));
}

// Do not edit the line below.
exports.sameBsts = sameBsts;
