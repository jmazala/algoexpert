function searchForRange(array, target) {
  const answer = [-1, -1];
  helper(0, array.length - 1, true);
  helper(0, array.length - 1, false);
  return answer;

  //could supply mid once we've found it in the first binary search
  function helper(low, high, goLeft) {
    while (low <= high) {
      const mid = Math.floor((low + high) / 2);

      if (array[mid] < target) {
        low = mid + 1;
        continue;
      }

      if (array[mid] > target) {
        high = mid - 1;
        continue;
      }

      if (goLeft) {
        if (mid === 0 || array[mid - 1] !== target) {
          answer[0] = mid;
          return;
        }

        high = mid - 1;
        continue;
      }

      if (mid === (array.length - 1) || array[mid + 1] !== target) {
        answer[1] = mid;
        return;
      }

      low = mid + 1;
    }
  }
}

// Do not edit the line below.
exports.searchForRange = searchForRange;
