const LAND = 0;
const WATER = 1;
const VISITED = 2;
const DIRECTIONS = [[-1, 0], [1, 0], [0, -1], [0, 1]];

function riverSizes(matrix) {
  const M = matrix.length;
  const N = matrix[0].length;
  const output = [];

  for (let i = 0; i < M; i++) {
    for (let j = 0; j < N; j++) {
      if (matrix[i][j] === WATER) {
        bfs(i, j);
      }
    }
  }

  return output;

  function bfs(i, j) {
    let size = 0;
    const queue = [[i, j]];

    while (queue.length > 0) {
      const [i, j] = queue.shift();

      if (matrix[i][j] !== WATER) {
        continue;
      }

      size++;
      matrix[i][j] = VISITED;

      DIRECTIONS.forEach(direction => {
        const iDelta = direction[0];
        const jDelta = direction[1];
        const newI = i + iDelta;
        const newJ = j + jDelta;

        if (newI < 0 || newJ < 0 || newI >= M || newJ >= N || matrix[newI][newJ] !== WATER) {
          return;
        }

        queue.push([newI, newJ]);
      });
    }

    output.push(size);
  }
}

console.log(riverSizes([
  [1, 0, 0, 1, 0],
  [1, 0, 1, 0, 0],
  [0, 0, 1, 0, 1],
  [1, 0, 1, 0, 1],
  [1, 0, 1, 1, 0]
]));