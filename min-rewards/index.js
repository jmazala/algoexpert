//this idea of expanding to the left and right
//of local mins doesn't have to happen by starting at local mins
//just iterate through the array from left to right
//and from right to left
function minRewards(scores) {
  //left to right
  const rewards = Array(scores.length).fill(1);
  for (let i = 1; i < scores.length; i++) {
    if (scores[i] > scores[i - 1]) {
      rewards[i] = rewards[i - 1] + 1;
    }
  }

  //right to left
  for (let i = scores.length - 2; i >= 0; i--) {
    if (scores[i] > scores[i + 1]) {
      rewards[i] = Math.max(rewards[i], rewards[i + 1] + 1);
    }
  }

  return rewards.reduce((a, b) => a + b);
}

//all valleys have 1 reward
//increase along the slope
// function minRewards(scores) {
//   const rewards = Array(scores.length).fill(1);
//   const valleyIndices = getValleyIndices(scores);

//   for (const valleyIndex of valleyIndices) {
//     expandFrom(valleyIndex, scores, rewards);
//   }

//   return rewards.reduce((a, b) => a + b);
// }

// function expandFrom(index, scores, rewards) {
//   let left = index - 1;

//   while (left >= 0 && scores[left] > scores[left + 1]) {
//     rewards[left] = Math.max(rewards[left], rewards[left + 1] + 1);
//     left--;
//   }

//   let right = index + 1;
//   while (right < scores.length && scores[right] > scores[right - 1]) {
//     rewards[right] = rewards[right - 1] + 1;
//     right++;
//   }
// }

// function getValleyIndices(array) {
//   if (array.length === 1) {
//     return [0];
//   }

//   //deal with the special case of left and right ends of the array
//   const indices = array[0] < array[1] ? [0] : [];
//   if (array[array.length - 1] < array[array.length - 2]) {
//     indices.push(array.length - 1);
//   }

//   for (let i = 1; i < array.length - 1; i++) {
//     if (array[i] < array[i - 1] && array[i] < array[i + 1]) {
//       indices.push(i);
//     }
//   }

//   return indices;
// }

//naive approach.  O(n^2) time O(n) space
// function minRewards(scores) {
//   if (scores.length === 0) {
//     return 0;
//   }

//   if (scores.length === 1) {
//     return 1;
//   }

//   if (scores.length === 2) {
//     return 3;
//   }

//   let rewards = Array(scores.length).fill(1);

//   for (let i = 1; i < scores.length; i++) {
//     if (scores[i] > scores[i - 1]) {
//       rewards[i] = rewards[i - 1] + 1;
//     } else {
//       let temp = i - 1;
//       while (temp >= 0 && scores[temp] > scores[temp + 1]) {
//         rewards[temp] = Math.max(rewards[temp], rewards[temp + 1] + 1);
//         temp--;
//       }
//     }
//   }

//   return rewards.reduce((a, b) => a + b);
// }

// Do not edit the line below.
exports.minRewards = minRewards;

console.log(minRewards([8, 4, 2, 1, 3, 6, 7, 9, 5])); //25