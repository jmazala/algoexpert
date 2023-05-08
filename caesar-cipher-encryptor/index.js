const ALPHABET = 'abcdefghijklmnopqrstuvwxyz'.split('');

// O(n) time and O(n) space
function caesarCipherEncryptor(string, key) {
  let output = '';

  for (let i = 0; i < string.length; i++) {
    const nextLetterIndex = (ALPHABET.indexOf(string[i]) + key) % 26;
    output += ALPHABET[nextLetterIndex];
  }

  return output;
}

// Do not edit the line below.
exports.caesarCipherEncryptor = caesarCipherEncryptor;

console.log(caesarCipherEncryptor('', 2)); // ''
console.log(caesarCipherEncryptor('a', 0)); // 'a'
console.log(caesarCipherEncryptor('a', 1)); // 'b'
console.log(caesarCipherEncryptor('a', 26)); // 'a'
console.log(caesarCipherEncryptor('a', 27)); // 'b'
console.log(caesarCipherEncryptor('xyz', 2)); // zab

