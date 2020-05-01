// Feel free to add new properties and methods to the class.
class MinMaxStack {
  constructor() {
    this.values = []
    this.minMaxStack = [];
  }

  peek() {
    return this.values[this.values.length - 1];
  }

  pop() {
    this.minMaxStack.pop();
    return this.values.pop();
  }

  push(number) {
    const newMinMaxEntry = { min: number, max: number };
    if (this.minMaxStack.length) {
      const lastMinMax = this.minMaxStack[this.minMaxStack.length - 1];
      newMinMaxEntry.max = Math.max(number, lastMinMax.max);
      newMinMaxEntry.min = Math.min(number, lastMinMax.min);
    }

    this.values.push(number);
    this.minMaxStack.push(newMinMaxEntry);
  }

  getMin() {
    return this.minMaxStack[this.minMaxStack.length - 1].min;
  }

  getMax() {
    return this.minMaxStack[this.minMaxStack.length - 1].max;
  }
}

// Do not edit the line below.
exports.MinMaxStack = MinMaxStack;
