# https://www.algoexpert.io/questions/quick-sort


def quickSort(array):
    helper(array, 0, len(array) - 1)
    return array


def helper(array, low, high):
    if low >= high:
        return

    partitionIndex = partition(array, low, high)
    helper(array, low, partitionIndex - 1)
    helper(array, partitionIndex + 1, high)
    return


def partition(array, low, high):
    pivot = array[low]
    i = low + 1
    j = high

    while i <= j:
        if array[i] > pivot and array[j] < pivot:
            swap(array, i, j)
            i += 1
            j -= 1
            continue
        if array[i] <= pivot:
            i += 1
        if array[j] >= pivot:
            j -= 1

    swap(array, low, j)
    return j


def swap(array, i, j):
    temp = array[i]
    array[i] = array[j]
    array[j] = temp


print(quickSort([8, 11, 2, 3, 1, 4, 9, 15, 15, 13, 6]))
