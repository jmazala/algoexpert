# https://www.algoexpert.io/questions/linked-list-palindrome


# This is an input class. Do not edit.
class LinkedList:
    def __init__(self, value):
        self.value = value
        self.next = None


# Naive Solution.  Use a queue and stack, compare
# TIME: O(n)
# SPACE: O(n)
def linkedListPalindrome(head: LinkedList) -> bool:
    queue = []
    stack = []

    temp = head
    while temp is not None:
        queue.append(temp.value)
        stack.insert(0, temp.value)
        temp = temp.next

    return queue == stack


# Find halfway point, reverse the rest, then iterate and compare
# TIME: O(n)
# SPACE: O(1)
def linkedListPalindrome(head: LinkedList) -> bool:
    slow = head
    fast = head

    while fast is not None and fast.next is not None:
        slow = slow.next
        fast = fast.next.next

    # slow is at halfway point.  reverse that list
    p1 = reverseLinkedList(slow)

    p2 = head

    while p1 is not None:
        if p1.value != p2.value:
            return False

        p1 = p1.next
        p2 = p2.next

    return True


def reverseLinkedList(node):
    prev = None
    cur = node

    while cur is not None:
        next = cur.next
        cur.next = prev
        prev = cur
        cur = next

    return prev


head = LinkedList(0)
print(linkedListPalindrome(head))  # True
head.next = LinkedList(1)
print(linkedListPalindrome(head))  # False
head.next.next = LinkedList(2)
head.next.next.next = LinkedList(2)
head.next.next.next.next = LinkedList(1)
head.next.next.next.next.next = LinkedList(0)
print(linkedListPalindrome(head))  # True
