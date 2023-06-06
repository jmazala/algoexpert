// https://www.algoexpert.io/questions/laptop-rentals

/*
TIME: O(n log n) to sort times, O(max(time) - min(time) for inner loop)
SPACE:  O(n) for inUse laptops.
Would be better to use a minHeap and check just the time intervals
*/
function laptopRentals(times) {
  if (times.length < 2) {
    return times.length;
  }

  times.sort((a, b) => a[0] - b[0]);

  const inUse = [];
  const minTime = times[0][0];
  const maxTime = times[times.length - 1][1];

  let available = 0;
  let maxLaptops = 0;

  for (let currentTime = minTime; currentTime <= maxTime; currentTime++) {
    /*
    check if any in use laptops can be freed.
    Note:  this could be optimized using a minHeap of endTimes
    for inUse
    */
    let inUseIndex = 0;
    while (inUseIndex < inUse.length) {
      if (inUse[inUseIndex][1] === currentTime) {
        available++;
        inUse.splice(inUseIndex, 1);
      } else {
        inUseIndex++;
      }
    }

    while (times.length > 0) {
      // this kid needs a laptop
      if (currentTime === times[0][0]) {
        if (available > 0) {
          available--;
        } else {
          maxLaptops++;
        }

        inUse.push(times.shift());
      } else {
        break;
      }
    }
  }

  return maxLaptops;
}

/*
Use 2 pointers for sorted startTimes and endTimes
Give a laptop to every kid as we iterate through
startTime, but also check endTime to try and free
one up
*/
function laptopRentals2(times) {
  if (times.length < 2) {
    return times.length;
  }

  let laptopCount = 0;
  const startTimes = times.map(a => a[0]).sort((a, b) => a - b);
  const endTimes = times.map(a => a[1]).sort((a, b) => a - b);

  let startIndex = 0;
  let endIndex = 0;

  while (startIndex < times.length) {
    // laptop has freed up
    if (startTimes[startIndex] >= endTimes[endIndex]) {
      laptopCount--;
      endIndex++;
    }

    laptopCount++;
    startIndex++;
  }

  return laptopCount;
}

// Do not edit the line below.
exports.laptopRentals = laptopRentals;

console.log(laptopRentals2([
  [0, 2],
  [1, 4],
  [4, 6],
  [0, 4],
  [7, 8],
  [9, 11],
  [3, 10]
])); // 3