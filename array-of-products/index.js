// https://www.algoexpert.io/questions/array-of-products

// array[i] =  product of every number in input array other than input[i]
function arrayOfProducts(array) {
  // brute force solution is O(n^2)
  output = Array(array.length).fill(1);
  for (let i = 0; i < array.length; i++) {
    for (let j = 0; j < array.length; j++) {
      if (i === j) {
        continue;
      }

      output[i] *= array[j];
    }
  }

  return output;
}

// Time O(n) solution
function arrayOfProducts2(array) {
  output = Array(array.length).fill(1);
  leftProduct = 1;
  rightProduct = 1;

  for (let i = 0; i < array.length; i++) {
    left = i;
    right = array.length - 1 - i;
    output[left] *= leftProduct;
    output[right] *= rightProduct;
    leftProduct *= array[left];
    rightProduct *= array[right];
  }

  return output;
}


console.log(arrayOfProducts([])); // []
console.log(arrayOfProducts([2])); //[2] ??
console.log(arrayOfProducts2([5, 1, 4, 2])); //[8, 40, 10, 20]

// Do not edit the line below.
exports.arrayOfProducts = arrayOfProducts;
