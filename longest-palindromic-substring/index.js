//USING POINTERS
function longestPalindromicSubstring(string) {
  if (string.length < 2) {
    return string;
  }

  let answer = '';

  for (let i = 1; i < string.length; i++) {
    //palindrome can start with a single char in the middle or a pair of chars in the middle
    const palindromeFromOdd = palindromeFrom(string, i - 1, i + 1);
    const palindromeFromEven = (string[i - 1] === string[i]) ? palindromeFrom(string, i - 1, i) : '';
    answer = palindromeFromOdd.length > answer.length ? palindromeFromOdd : answer;
    answer = palindromeFromEven.length > answer.length ? palindromeFromEven : answer;
  }

  return answer;
}

function palindromeFrom(string, left, right) {
  while (left >= 0 && right < string.length && string[left] === string[right]) {
    left--;
    right++;
  }

  return string.slice(left + 1, right);
}

// Do not edit the line below.
exports.longestPalindromicSubstring = longestPalindromicSubstring;

//USING A HASH
// function longestPalindromicSubstring(string) {
//   if (string.length < 2) {
//     return string;
//   }

//   let answer = '';
//   const hash = {}; //O(n) SPACE and O(n) TIME for the hash

//   for (let i = 0; i < string.length; i++) {
//     const c = string[i];
//     hash[c] = hash[c] || [];
//     hash[c].push(i);
//   }

//   //O(n) TO LOOP
//   Object.keys(hash).forEach(c => {
//     if (hash[c].length === 1) {
//       return;
//     }

//     //this is worst case O(n^2)
//     for (let i = 0; i < hash[c].length - 1; i++) {
//       const start = hash[c][i];
//       for (let j = i + 1; j < hash[c].length; j++) {
//         const end = hash[c][j];
//         if ((end - start + 1) < answer.length) {
//           continue;
//         }

//         const substring = string.slice(start, end + 1);
//         answer = isPalindrome(substring) ? substring : answer;
//       }
//     }
//   });

//   return answer;
// }

// function isPalindrome(s) {
//   return Array.from(s).reverse().join('') === s;
// }

// Do not edit the line below.
exports.longestPalindromicSubstring = longestPalindromicSubstring;

console.log(longestPalindromicSubstring('abaxyzzyxfz'));