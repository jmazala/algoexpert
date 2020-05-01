function powerset(array) {
  const answer = [[]];

  array.forEach(num => {
    const iterations = answer.length;
    for (let j = 0; j < iterations; j++) {
      const temp = Array.from(answer[j]);
      temp.push(num);
      answer.push(temp);
    }
  });

  return answer;
}

// Do not edit the line below.
exports.powerset = powerset;

console.log(powerset([1, 2])); //[[], [1], [2], [1,2]]