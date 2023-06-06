// https://www.algoexpert.io/questions/kruskals-algorithm

/*
e is # of edges
n is # of nodes
TIME:  O(e log e) to sort the edges (even though it's half)
SPACE:
  O(n) for ranks
  O(n) for parents
  O(e) for mst
*/
function kruskalsAlgorithm(edges) {
  const sortedEdges = [];

  // filter out duplicates and sort edges ascending by weight
  for (let start = 0; start < edges.length; start++) {
    for (const edge of edges[start]) {
      const [end, weight] = edge;

      // don't want to store duplicates
      if (start < end) {
        sortedEdges.push([start, end, weight]);
      }
    }
  }

  /*
  Don't forget JS is super funky when it comes to
  sorting Integers.  YOU MUST DEFINE THIS FUNCTION
  */
  sortedEdges.sort((a, b) => {
    if (a[2] > b[2]) {
      return 1;
    }

    if (a[2] < b[2]) {
      return -1;
    }

    return 0;
  });

  // Set up disjoint set
  const ranks = [];
  const parents = [];
  const mst = [];
  for (let i = 0; i < edges.length; i++) {
    ranks.push(0);
    parents.push(i);
    mst.push(new Array());
  }

  for (const item of sortedEdges) {
    const [node1, node2, weight] = item;
    const parent1 = getParent(node1);
    const parent2 = getParent(node2);

    // These nodes are already in the same connected graph
    if (parent1 === parent2) {
      continue;
    }

    mst[node1].push([node2, weight]);
    mst[node2].push([node1, weight]);
    union(parent1, parent2);
  }

  return mst;

  function getParent(node) {
    if (node !== parents[node]) {
      parents[node] = getParent(parents[node]);
    }

    return parents[node];
  }

  function union(parent1, parent2) {
    if (ranks[parent1] < ranks[parent2]) {
      parents[parent1] = parent2;
      return;
    }

    if (ranks[parent1] > ranks[parent2]) {
      parents[parent2] = parent1;
      return;
    }

    parents[parent2] = parent1;
    ranks[parent1]++;
  }
}

// Do not edit the line below.
exports.kruskalsAlgorithm = kruskalsAlgorithm;

console.log(JSON.stringify(kruskalsAlgorithm([
  [[1, 3], [2, 5]],
  [[0, 3], [2, 10], [3, 12]],
  [[0, 5], [1, 10]],
  [[1, 12]]
])));

console.log(JSON.stringify(kruskalsAlgorithm([
  [[1, 5], [2, 10]],
  [[0, 5], [2, 6]],
  [[0, 10], [1, 6]]
]
))); /* [
  [[1,5]],
  [[0,5],[2,6]],
  [[1,6]]]

  0 => 1 with weight 5
  0 => 2 with weight 10 = total weight 15

  0 => 1 with weight 5
  0 => 2 with weight 6 = total weight 11
  */
