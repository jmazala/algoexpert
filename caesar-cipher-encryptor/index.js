function caesarCipherEncryptor(string, key) {
  const result = Array(string.length);
  key = key % 26;
  const alphabet = 'abcdefghijklmnopqrstuvwxyz'.split('');

  for (let i = 0; i < string.length; i++) {
    result[i] = nextLetter(string[i]);
  }

  return result.join('');

  function nextLetter(c) {
    let nextLetterIndex = alphabet.indexOf(c) + key;
    if (nextLetterIndex > 25) {
      nextLetterIndex = -1 + (nextLetterIndex % 25);
    }

    return alphabet[nextLetterIndex];
  }
}

// Do not edit the line below.
exports.caesarCipherEncryptor = caesarCipherEncryptor;
