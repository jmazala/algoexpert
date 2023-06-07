// https://www.algoexpert.io/questions/blackjack-probability

const NEXT_CARDS = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
const DELIMITER = ',';
const memo = {};

function blackjackProbability(target, startingHand) {
  const memoKey = getKey(target, startingHand);
  if (memoKey in memo) {
    return memo[memoKey];
  }

  if (startingHand > target) {
    return 1;
  }

  if (startingHand > (target - 5) && startingHand <= target) {
    return 0;
  }

  let probabilityBust = 0;
  for (const nextCard of NEXT_CARDS) {
    const nextHand = startingHand + nextCard;
    probabilityBust += .1 * blackjackProbability(target, nextHand);
  }

  probabilityBust = probabilityBust.toFixed(3);
  memo[memoKey] = probabilityBust;
  return probabilityBust;
}

function getKey(target, startingHand) {
  return [target, startingHand].join(DELIMITER);
}

// Do not edit the line below.
exports.blackjackProbability = blackjackProbability;


console.log(blackjackProbability(21, 1)); // .261 (good case for memoization stack overflow)
console.log(blackjackProbability(21, 15)); // .450
