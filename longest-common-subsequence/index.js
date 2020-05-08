function longestCommonSubsequence(str1, str2) {
  const matrix = Array(str1.length + 1).fill().map(i => Array(str2.length + 1).fill(''));

  for (let i = 0; i <= str1.length; i++) {
    for (let j = 0; j <= str2.length; j++) {
      if (i === 0 || j === 0) {
        continue;
      }

      if (str1[i - 1] === str2[j - 1]) {
        matrix[i][j] = matrix[i - 1][j - 1] + str1[i - 1];
      } else {
        matrix[i][j] = matrix[i - 1][j].length > matrix[i][j - 1].length ? matrix[i - 1][j] : matrix[i][j - 1];
      }
    }
  }

  return matrix[str1.length][str2.length].split('');
}

// Do not edit the line below.
exports.longestCommonSubsequence = longestCommonSubsequence;

console.log(longestCommonSubsequence('ZXVVYZW', 'XKYKZPW')); // ['X', 'Y', 'Z', 'W']