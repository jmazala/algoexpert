function quickselect(array, k) {
  return helper(0, array.length - 1);
	
	function helper(low, high) {
		while (true) {
			const pivot = low;
			let left = low + 1;
			let right = high;
			
			while (left <= right) {
				if (array[left] > array[pivot] && array[right] < array[pivot]) {
          swap(array, left, right)
          left++;
          right--;
          continue;
				}
				
				if (array[left] <= array[pivot]) {
					left++;
				}
				
				if (array[right] >= array[pivot]) {
					right--;
				}
			}
			
			swap(array, pivot, right);
			if (right === k - 1) {
				return array[right];
			}
			
			if (right < k -1) {
				low = right + 1;
			} else {
				high = right - 1;
			}
		}
	}
}

function swap(array, i, j) {
	const temp = array[i];
	array[i] = array[j];
	array[j] = temp;
}

// Do not edit the line below.
exports.quickselect = quickselect;