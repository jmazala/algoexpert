//WITH POINTERS AND A HASH
function fourNumberSum(array, targetSum) {
  const answer = [];
	const hash = {};
	
	for (let i = 1; i < array.length - 1; i++) {
		for (let j = i + 1; j < array.length; j++) {
			const firstSum = array[i] + array[j];
			const remainder = targetSum - firstSum;
			
			if (remainder in hash) {
				for ([x, y] of hash[remainder]) {
					answer.push([array[i], array[j], x, y]);
				}
			}
		}
		
		for (let k = 0; k < i; k++) {
			const firstSum = array[i] + array[k];
			hash[firstSum] = hash[firstSum] || [];
			hash[firstSum].push([array[k], array[i]]);
		}
	}
	
	return answer;
}

//USING SETS TO KILL DUPLICATES
// function fourNumberSum(array, targetSum) {
//   const hash = {};

//   for (let i = 0; i < array.length - 1; i++) {
//     for (let j = i + 1; j < array.length; j++) {
//       const twoSum = array[i] + array[j];
//       hash[twoSum] = hash[twoSum] || [];
//       hash[twoSum].push([array[i], array[j]]);
//     }
//   }

//   const seen = new Set();
//   const answerStrings = new Set();

//   Object.keys(hash).forEach(twoSum => {
//     twoSum = +twoSum;
//     const remainder = targetSum - twoSum;
//     if (!hash[remainder] || seen.has(remainder) || seen.has(twoSum)) {
//       return;
//     }

//     seen.add(twoSum);
//     seen.add(remainder);

//     hash[twoSum].forEach(([x1, y1]) => {
//       hash[remainder].forEach(([x2, y2]) => {
//         if (x1 === x2 || x1 === y2 || y1 === x2 || y1 === y2) {
//           return;
//         }

//         const subAnswer = [x1, y1, x2, y2].sort((a, b) => a - b).join(',');
//         answerStrings.add(subAnswer);
//       });
//     });
//   });

//   const answer = [];
//   answerStrings.forEach(i => answer.push(i.split(',').map(i => +i)));
//   return answer;
// }

// Do not edit the line below.
exports.fourNumberSum = fourNumberSum;

console.log(fourNumberSum([7, 6, 4, -1, 1, 2], 16)); //[7,6,4,-1], [7,6,1,2]