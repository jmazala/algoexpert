//the key is on the shift when you binary search
//either left half is sorted or right half is sorted
//based on that you can eliminate half the array
function shiftedBinarySearch(array, target) {
  let low = 0;
	let high = array.length - 1;
	
	while (low <= high) {
		if (array[low] === target) {
			return low;
		}
		
		if (array[high] === target) {
			return high;
		}
		
		const mid = Math.floor((low + high) / 2);
		if (array[mid] === target) {
			return mid;
		}
		
		//left half is sorted
		if (array[low] < array[mid]) {
			if (target > array[low] && target < array[mid]) {
				high = mid - 1;
				continue;
			}
			
			low = mid + 1;
			continue;
		}
		
		//right half is sorted
		if (target > array[mid] && target < array[high]) {
			low = mid + 1;
			continue;
		}
		
		high = mid - 1;
	}
	
	return -1;
}

// Do not edit the line below.
exports.shiftedBinarySearch = shiftedBinarySearch;
