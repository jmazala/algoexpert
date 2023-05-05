// https://www.algoexpert.io/questions/semordnilap
function semordnilap(words) {
  const wordSet = new Set(words);
  const result = [];

  for (const word of words) {
    wordSet.delete(word);
    const reverseWord = reverseString(word);
    if (!wordSet.has(reverseWord)) {
      continue;
    }

    result.push([reverseWord, word]);
    wordSet.delete(reverseWord);
  }
  return result;
}

const reverseString = (word) => word.split('').reverse().join('');

// Do not edit the line below.
exports.semordnilap = semordnilap;

console.log(JSON.stringify(semordnilap([]))); //[]
console.log(JSON.stringify(semordnilap(['aaa']))); //[]
console.log(JSON.stringify(semordnilap(['dog', 'god']))); //[['dog', 'god']]
console.log(JSON.stringify(semordnilap(['dog', 'ogd', 'god']))); //[['dog', 'god']]
console.log(JSON.stringify(semordnilap(['dog', 'ogd', 'aaa', 'god', 'lik', 'kil']))); //[['dog', 'god'], ['lik', 'kil']]
