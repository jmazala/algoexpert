/*
Recursive with memoization.  At each stack frame,
compare the chars at p1 and p2 with that of p3
incrementing pointers respectively for matches.
Since we can take p1 or p2's char (if they're equal)
there is not an else case.  This can result
in duplicate comparisons so we add a memoization matrix
to avoid the duplicate calculations.
*/
function interweavingStrings(one, two, three) {
  if ((one.length + two.length) !== three.length) {
    return false;
  }

  const memo = [];
  for (let i = 0; i <= one.length; i++) {
    memo.push(new Array(two.length + 1).fill(null));
  }

  return helper(0, 0);

  function helper(p1, p2) {
    if (memo[p1][p2] !== null) {
      return memo[p1][p2];
    }

    if ((p1 + p2) === three.length) {
      return true;
    }

    const c3 = three[p1 + p2];

    if (p1 < one.length) {
      const c1 = one[p1];
      if (c1 === c3) {
        memo[p1][p2] = helper(p1 + 1, p2);
        if (memo[p1][p2]) {
          return true;
        }
      }
    }

    if (p2 < two.length) {
      const c2 = two[p2];
      if (c2 === c3) {
        memo[p1][p2] = helper(p1, p2 + 1);
        if (memo[p1][p2]) {
          return true;
        }
      }
    }

    memo[p1][p2] = false;
    return false;
  }
}

// Do not edit the line below.
exports.interweavingStrings = interweavingStrings;

console.log(interweavingStrings("aacaaaa", "aaabaaa", "aaaabacaaaaaaa")); // true
console.log(interweavingStrings('algoexpert', 'your-dream-job', 'your-algodream-expertjob')); //true
console.log(interweavingStrings('algoexpert', 'your-dream-job', 'your-algodream-expertjo')); //false