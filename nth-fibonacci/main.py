# https://www.algoexpert.io/questions/nth-fibonacci


def getNthFib(n: int) -> int:
    minusTwo = 0
    minusOne = 1

    for i in range(3, n + 1):
        temp = minusTwo + minusOne
        minusTwo = minusOne
        minusOne = temp

    return minusOne if n > 1 else minusTwo


print(getNthFib(1))  # 0
print(getNthFib(2))  # 1
print(getNthFib(6))  # 5
