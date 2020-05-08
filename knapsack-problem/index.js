function knapsackProblem(items, capacity) {
  //DQ any heavier items than our knapsack can hold
  items = items.filter(i => i[1] <= capacity);

  //columns represent capacities from 0 to c
  //rows represent the first # of items
  //matrix[r][c] = max value for the first r items with capacity c
  const matrix = Array(items.length + 1).fill().map(i => Array(capacity + 1).fill(0));

  //populate row of the matrix where we choose the first i item(s)
  for (let i = 1; i <= items.length; i++) {
    const [value, weight] = items[i - 1];

    for (let currentCapacity = 0; currentCapacity <= capacity; currentCapacity++) {
      if (weight > currentCapacity) {
        matrix[i][currentCapacity] = matrix[i - 1][currentCapacity];
      } else {
        matrix[i][currentCapacity] = Math.max(matrix[i - 1][currentCapacity], matrix[i - 1][currentCapacity - weight] + value);
      }
    }
  }

  return [matrix[items.length][capacity], getItemsUsed(matrix, items)];
}

function getItemsUsed(matrix, items) {
  const itemsUsed = [];

  //start in the bottom right corner
  let i = matrix.length - 1;
  let j = matrix[0].length - 1;

  while (i > 0 && j > 0) {
    //we didn't use this item, just go up 1 row
    if (matrix[i][j] === matrix[i - 1][j]) {
    } else {
      //we used this item, move your pointers according to the weight
      itemsUsed.push(i - 1);
      j -= items[i - 1][1];
    }

    i--;
  }

  return itemsUsed;
}

// Do not edit the line below.
exports.knapsackProblem = knapsackProblem;

//first is value, second is weight
console.log(JSON.stringify(knapsackProblem([[4, 3], [1, 2], [5, 6], [6, 7], [10000, 25]], 10))); //[10, [1, 3]]