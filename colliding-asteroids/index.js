// https://www.algoexpert.io/questions/colliding-asteroids

function collidingAsteroids(asteroids) {
  let i = 1;

  while (i < asteroids.length && asteroids.length > 1) {
    if (i === 0) {
      i++;
    }

    const asteroid1 = asteroids[i];
    const asteroid2 = asteroids[i - 1];

    if (asteroid1 > 0 && asteroid2 > 0) {
      i++;
      continue;
    }

    if (asteroid1 < 0 && asteroid2 < 0) {
      i++;
      continue;
    }

    if (asteroid1 > 0 && asteroid2 < 0) {
      i++;
      continue;
    }

    if (asteroid1 < 0 && asteroid2 > 0) {
      if (Math.abs(asteroid1) > Math.abs(asteroid2)) {
        asteroids.splice(i - 1, 1);
        i--;
      } else if (Math.abs(asteroid1) < Math.abs(asteroid2)) {
        asteroids.splice(i, 1);
      } else {
        asteroids.splice(i - 1, 2);
        i--;
      }
    }
  }

  return asteroids;
}

// Do not edit the line below.
exports.collidingAsteroids = collidingAsteroids;

console.log(collidingAsteroids([1])); //[1]
console.log(collidingAsteroids([5, -5])); //[]
console.log(collidingAsteroids([-5, 5])); //[-5, 5]
console.log(collidingAsteroids([-5, -5])); //[-5, 5]
console.log(collidingAsteroids([5, 5])); //[5, 5]
console.log(collidingAsteroids([1, 2, 3, 4, 5])); //[1, 2, 3, 4, 5]
console.log(collidingAsteroids([-1, -2, -3, -4, -5])); //[-1, -2, -3, -4, -5]
console.log(collidingAsteroids([1, 2, 3, -4])); //[-4]
console.log(collidingAsteroids([4, -1, -2, -3])); //[4]
console.log(collidingAsteroids([-3, 7, -8, 6, 7, -5, -7])); //[-3, -8, 6]
console.log(collidingAsteroids([-5, -5, -5]));