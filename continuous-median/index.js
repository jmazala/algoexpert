// https://www.algoexpert.io/questions/continuous-median

// Do not edit the class below except for
// the insert method. Feel free to add new
// properties and methods to the class.
class ContinuousMedianHandler {
  constructor(value) {
    // Write your code here.
    this.median = null;
    this.values = [];

    if (value !== undefined) {
      this.values.push(value);
      this.median = value;
    }
  }

  /*
  TIME: O(log n) for binary search for index, O(1) for inserting into linked list
  SPACE: O(n) for this.values to hold every number inserted
  */
  insert(number) {
    let low = 0;
    let high = this.values.length - 1;

    while (low <= high) {
      const mid = Math.floor((low + high) / 2);
      if (this.values[mid] === number) {
        this.values.splice(mid, 0, number);
        setMedian();
        return;
      } else if (this.values[mid] < number) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    this.values.splice(low, 0, number);
    this.setMedian();
  }

  setMedian() {
    if (this.values.length % 2 === 0) {
      this.median = (this.values[this.values.length / 2] + this.values[(this.values.length / 2) - 1]) / 2;
    } else {
      this.median = this.values[Math.floor(this.values.length / 2)];
    }
  }

  getMedian() {
    return this.median;
  }
}

// Do not edit the line below.
exports.ContinuousMedianHandler = ContinuousMedianHandler;

const cmh = new ContinuousMedianHandler();
cmh.insert(5);
cmh.insert(10);
console.log(cmh.getMedian()); // 7.5
cmh.insert(100);
console.log(cmh.getMedian()); // 10