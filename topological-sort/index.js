// https://www.algoexpert.io/questions/topological-sort

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
  const queue = Object.keys(degrees).filter(job => degrees[job] === 0).map(job => +job);
  const completed = [];

  while (queue.length) {
    const job = queue.shift();

    // In the queue means completed
    completed.push(job);

    for (const nextJob of prereqs[job]) {
      // We've completed a prereq
      degrees[nextJob]--;

      // If all prereqs are completed for nextJob, queue it
      if (degrees[nextJob] === 0) {
        queue.push(nextJob);
      }
    }
  }

  return completed.length === jobs.length ? completed : [];
}

// Do not edit the line below.
exports.topologicalSort = topologicalSort;


console.log(topologicalSort([1, 2, 3, 4], [[1, 2], [1, 3], [3, 2], [4, 2], [4, 3]]));