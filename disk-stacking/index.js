// https://www.algoexpert.io/questions/disk-stacking

const WIDTH = 0;
const DEPTH = 1;
const HEIGHT = 2;

function diskStacking(disks) {
  if (disks.length === 0) {
    return [];
  }

  if (disks.length === 1) {
    return disks;
  }

  //we sort the disks so we can use dp to solve the problem
  disks.sort((a, b) => a[HEIGHT] - b[HEIGHT]);

  //dp[i] represents max height stack using disks 0 to i
  const dp = new Array(disks.length);
  dp[0] = [disks[0]];

  //remember, we want to return the stack with the max height
  let answer = [dp[0]];
  let maxHeight = disks[0][HEIGHT];

  for (let i = 1; i < disks.length; i++) {
    let currentDisk = disks[i];
    let stackHeight = currentDisk[HEIGHT];
    dp[i] = [currentDisk];

    let depth = currentDisk[DEPTH];
    let width = currentDisk[WIDTH];
    let height = currentDisk[HEIGHT];

    for (let j = 0; j < i; j++) {
      //can our current disk go below the other calculated stacks?
      const otherDisk = disks[j];
      if (otherDisk[HEIGHT] < height && otherDisk[DEPTH] < depth && otherDisk[WIDTH] < width) {
        const tempStack = dp[j].slice();
        tempStack.push(currentDisk);
        const tempStackHeight = getStackHeight(tempStack);
        if (tempStackHeight > stackHeight) {
          dp[i] = tempStack;
          stackHeight = tempStackHeight;
        }
      }
    }

    // is this new stack the highest?
    if (stackHeight > maxHeight) {
      answer = dp[i];
      maxHeight = stackHeight;
    }
  }

  return answer;
}

function getStackHeight(stack) {
  return stack.reduce((a, b) => a + b[HEIGHT], 0);
}

// Do not edit the line below.
exports.diskStacking = diskStacking;

console.log(JSON.stringify(diskStacking([[2, 1, 2], [3, 2, 3], [2, 2, 8], [2, 3, 4], [1, 3, 1], [4, 4, 5]]))); // [[2,1,2], [3,2,3],[4,4,5]]
console.log(JSON.stringify(diskStacking([[2, 1, 2]]))); // [[2,1,2]