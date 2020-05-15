function topologicalSort(jobs, deps) {
  //make a graph
  const degrees = {};
  const prereqs = {};

  for (const job of jobs) {
    degrees[job] = 0;
    prereqs[job] = [];
  }

  for (const dep of deps) {
    degrees[dep[1]]++;
    prereqs[dep[0]].push(dep[1]);
  }

  //start with those with degree 0
  const queue = [];
  Object.keys(degrees).forEach(job => {
    if (degrees[job] === 0) {
      queue.push(+job);
    }
  });

  const answer = [];

  while (queue.length) {
    const job = queue.shift();
    answer.push(job);
    prereqs[job].forEach(nextJob => {
      degrees[nextJob] -= 1;
      if (degrees[nextJob] == 0) {
        queue.push(nextJob);
      }
    });
  }

  return answer.length === jobs.length ? answer : [];
}

// Do not edit the line below.
exports.topologicalSort = topologicalSort;


console.log(topologicalSort([1, 2, 3, 4], [[1, 2], [1, 3], [3, 2], [4, 2], [4, 3]]));