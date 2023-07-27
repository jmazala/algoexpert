# https://www.algoexpert.io/questions/reversePolishNotation

from typing import List


def reversePolishNotation(tokens: List[str]) -> int:
    stack = []

    for token in tokens:
        if isOperand(token):
            y = stack.pop()
            x = stack.pop()

            result = operate(int(x), int(y), token)
            stack.append(int(result))
        else:
            stack.append(int(token))

    return stack.pop()


def isOperand(token: str) -> bool:
    return token == "+" or token == "-" or token == "/" or token == "*"


def operate(x: int, y: int, operand: str) -> int:
    if operand == "+":
        return x + y
    if operand == "-":
        return x - y
    if operand == "/":
        return int(x / y)
    if operand == "*":
        return x * y


print(reversePolishNotation(["10", "-3", "/"]))  # -3
print(reversePolishNotation(["2", "4", "+"]))  # 6
print(reversePolishNotation(["18", "4", "-", "7", "/"]))  # 2
print(reversePolishNotation(["50", "3", "17", "+", "2", "-", "/"]))  # 2
