const _ = require('lodash');

//2 pass solution
function waterArea(heights) {
  const maxHeights = Array(heights.length);
  let leftMax = 0;

  for (let i = 0; i < heights.length; i++) {
    maxHeights[i] = leftMax;
    leftMax = Math.max(leftMax, heights[i]);
  }

  let rightMax = 0;
  for (let i = heights.length - 1; i >= 0; i--) {
    const height = heights[i];
    const minHeight = Math.min(maxHeights[i], rightMax);

    if (height < minHeight) {
      maxHeights[i] = minHeight - height;
    } else {
      maxHeights[i] = 0;
    }

    rightMax = Math.max(rightMax, height);
  }

  return _.sum(maxHeights);
}

//3 pass solution
// function waterArea(heights) {
//   const leftRightMaxHeights = heights.map(i => { return { left: -Infinity, right: -Infinity }; });
//   let leftMax = -Infinity;
//   let rightMax = -Infinity;

//   //find leftMaxes at each point
//   for (let i = 1; i < heights.length; i++) {
//     leftMax = Math.max(leftMax, heights[i - 1]);
//     leftRightMaxHeights[i].left = leftMax;
//   }

//   //find rightMaxes at each point
//   for (let i = heights.length - 2; i >= 0; i--) {
//     rightMax = Math.max(rightMax, heights[i + 1]);
//     leftRightMaxHeights[i].right = rightMax;
//   }

//   let answer = 0;

//   for (let i = 0; i < heights.length; i++) {
//     const height = heights[i];
//     const left = leftRightMaxHeights[i].left;
//     const right = leftRightMaxHeights[i].right;
//     if (left <= 0 || right <= 0) {
//       continue;
//     }

//     const waterAtThisPoint = Math.min(left, right) - height;
//     answer += Math.max(waterAtThisPoint, 0);
//   }

//   return answer;
// }

// Do not edit the line below.
exports.waterArea = waterArea;

console.log(waterArea([0, 8, 0, 0, 5, 0, 0, 10, 0, 0, 1, 1, 0, 3]));