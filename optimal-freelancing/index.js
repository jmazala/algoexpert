// https://www.algoexpert.io/questions/optimal-freelancing

const DAYS_IN_WEEK = 7;

/*
TIME: O(n log n) + O(7n) = O(n log n)
  O(n log n) to sort
  O(n) for jobs loop
  O(7) for inner loop
SPACE: O(7) + O(1) = O(1)
  O(7) for timeline
  O(1) for earnings
*/
function optimalFreelancing(jobs) {
  // Prioritize highest paying jobs
  jobs.sort((a, b) => b.payment - a.payment);

  const timeline = new Array(DAYS_IN_WEEK).fill(false);
  let earnings = 0;

  for (let i = 0; i < jobs.length; i++) {
    const { deadline, payment } = jobs[i];

    // Try to schedule each job at the latest possible time
    const latestTime = Math.min(DAYS_IN_WEEK, deadline);

    for (let day = latestTime - 1; day >= 0; day--) {
      if (!timeline[day]) {
        timeline[day] = true;
        earnings += payment;
        break;
      }
    }
  }

  return earnings;
}

// Do not edit the line below.
exports.optimalFreelancing = optimalFreelancing;



const jobs = [
  {
    "deadline": 2,
    "payment": 1
  },
  {
    "deadline": 1,
    "payment": 4
  },
  {
    "deadline": 3,
    "payment": 2
  },
  {
    "deadline": 1,
    "payment": 3
  },
  {
    "deadline": 4,
    "payment": 3
  },
  {
    "deadline": 4,
    "payment": 2
  },
  {
    "deadline": 4,
    "payment": 1
  },
  {
    "deadline": 5,
    "payment": 4
  },
  {
    "deadline": 8,
    "payment": 1
  }
];

console.log(optimalFreelancing(jobs)); // 16