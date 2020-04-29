def longestPeak(array):
    #go through the array to find a peak
    answer = 0
    i = 1

    while i < len(array) - 1:
      isPeak = array[i] > array[i-1] and array[i] > array[i+1]

      if (not isPeak):
        i += 1
        continue

      
      left = i - 1
      while (left > 0 and array[left - 1] < array[left]):
        left -= 1
        
      right = i + 1
      while (right < len(array) - 1 and array[right] > array[right + 1]):
        right += 1
      
      answer = max(answer, right - left + 1)
      i = right


    #when you do find a peak, pointer left til the end
    #then pointer right til the end of the peak
    #record length
    return answer

if __name__ == '__main__':
  print(longestPeak([1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3])) #6