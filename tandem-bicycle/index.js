// https://www.algoexpert.io/questions/tandem-bicycle

function tandemBicycle(redShirtSpeeds, blueShirtSpeeds, fastest) {
  redShirtSpeeds.sort((a, b) => a - b);
  blueShirtSpeeds.sort((a, b) => a - b);

  if (!fastest) {
    redShirtSpeeds.reverse();
  }

  let result = 0;
  for (let i = 0; i < redShirtSpeeds.length; i++) {
    result += Math.max(redShirtSpeeds[i], blueShirtSpeeds[blueShirtSpeeds.length - i - 1]);
  }

  return result;
}

/*
   * R: {9, 5, 5, 3, 2}
   * B: {7, 6, 3, 2, 1}
   * 
   * SORTED
   * R: {2, 3, 5, 5, 9}
   * B: {1, 2, 3, 6, 7}
   * 
   * we want 2 pointers for red and blue
   * red can move forwards blue can move backwards
   * 
   * if we're doing the slowest time, reverse red so we're comparing both maxes
   
   */

console.log(tandemBicycle([5, 5, 3, 9, 2], [3, 6, 7, 2, 1], true)); //32
console.log(tandemBicycle([5, 5, 3, 9, 2], [3, 6, 7, 2, 1], false)); //25
console.log(tandemBicycle([1, 2, 1, 9, 12, 3], [3, 3, 4, 6, 1, 2], false)); //30

// Do not edit the line below.
exports.tandemBicycle = tandemBicycle;