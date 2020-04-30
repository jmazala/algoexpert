function hasSingleCycle(array) {
  const edges = Array(array.length);
  for (let i = 0; i < array.length; i++) {
    next = i + array[i];
    while (next < 0) {
      next = array.length + next;
    }

    if (next >= array.length) {
      next = next % array.length;
    }
    edges[i] = next;
  }

  for (let start = 0; start < edges.length; start++) {
    const complete = helper(start);
    if (complete) {
      return true;
    }
  }

  return false;

  function helper(start) {
    const original = start;
    const visited = new Set();

    let steps = edges.length;
    while (steps) {
      if (visited.has(start)) {
        return false;
      }

      visited.add(start);
      start = edges[start];
      steps--;
    }

    return start === original;
  }
}

// Do not edit the line below.
exports.hasSingleCycle = hasSingleCycle;

console.log(hasSingleCycle([2, 3, 1, -4, -4, 2])); //true
console.log(hasSingleCycle([2, 2, -1])); //true
console.log(hasSingleCycle([2, 2, 2])); //true
console.log(hasSingleCycle([0, 1, 1, 1, 1])); //false
console.log(hasSingleCycle([1, 2, 3, 4, -2, 3, 7, 8, -26])); //true