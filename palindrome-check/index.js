function isPalindrome(string) {
  return reverseString(string) === string;
}

function reverseString(string) {
  return string.split('').reverse().join('');
}

// Do not edit the line below.
exports.isPalindrome = isPalindrome;
