// https://www.algoexpert.io/questions/reverse-words-in-string

function reverseWordsInString(string) {
  let output = '';
  let foundWord = false;

  let i = string.length - 1;
  let j = i;
  let numSpaces = 0;

  while (i >= 0) {
    if (string[i] !== ' ') {
      i--;
      continue;
    }

    if (string[i] === ' ') {
      while (i >= 0 && string[i] === ' ') {
        i--;
        numSpaces++;
      }

      let temp = i + 1 + numSpaces;
      while (temp <= j) {
        output += string[temp];
        temp++;
      }

      while (numSpaces > 0) {
        output += ' ';
        numSpaces--;
      }

      j = i;
    }
  }

  let temp = i + 1 + numSpaces;
  while (temp <= j) {
    output += string[temp];
    temp++;
  }

  while (numSpaces > 0) {
    output += ' ';
    numSpaces--;
  }

  return output;
}

function reverseWordsInString2(string) {
  string = reverse(string, 0, string.length - 1);

  let start = 0;
  let end = 0;

  while (end < string.length) {
    while (end < string.length && string[end] !== ' ') {
      end++;
    }

    string = reverse(string, start, end - 1);
    while (string[end] === ' ') {
      end++;
    }

    start = end;
  }

  return string;
}

function reverse(string, start, end) {
  const array = new Array(string.length);
  let i = 0;
  while (i < start) {
    array[i] = string[i];
    i++;
  }

  while (end >= start) {
    array[i] = string[end];
    end--;
    i++;
  }

  while (i < string.length) {
    array[i] = string[i];
    i++;
  }

  return array.join('');
}

// Do not edit the line below.
exports.reverseWordsInString = reverseWordsInString;

console.log(reverseWordsInString2('tim is great')); // great is tim
console.log(reverseWordsInString2('whitespaces    4')); // 4    whitespaces
console.log(reverseWordsInString2('AlgoExpert is  the   best!')); // best!   the  is AlgoExpert