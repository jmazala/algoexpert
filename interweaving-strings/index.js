function interweavingStrings(one, two, three) {
  if ((one.length + two.length) !== three.length) {
    return false;
  }

  return helper(0, 0);

  function helper(p1, p2) {
    const p3 = p1 + p2;

    if (p3 === three.length) {
      return true;
    }

    if (one[p1] === three[p3]) {
      const result = helper(p1 + 1, p2);
      if (result) {
        return true;
      }
    }

    if (two[p2] === three[[p3]]) {
      const result = helper(p1, p2 + 1);
      if (result) {
        return true;
      }
    }

    return false;
  }
}

// Do not edit the line below.
exports.interweavingStrings = interweavingStrings;

console.log(interweavingStrings('algoexpert', 'your-dream-job', 'your-algodream-expertjob')); //true
console.log(interweavingStrings('algoexpert', 'your-dream-job', 'your-algodream-expertjo')); //false