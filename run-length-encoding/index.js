// https://www.algoexpert.io/questions/run-length-encoding

function runLengthEncoding(string) {
  let start = 0;
  let end = 0;
  let c;
  let output = '';

  while (start < string.length) {
    c = string[start];
    while (end < string.length && string[end] === c) {
      end++;
    }

    let numOfCharToEncode = end - start;
    while (numOfCharToEncode > 0) {
      if (numOfCharToEncode >= 9) {
        output += '9' + c;
        numOfCharToEncode -= 9;
      } else {
        output += numOfCharToEncode + c;
        numOfCharToEncode = 0;
      }
    }

    start = end;
  }

  return output;
}

// Do not edit the line below.
exports.runLengthEncoding = runLengthEncoding;
