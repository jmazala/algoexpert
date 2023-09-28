# https://www.algoexpert.io/questions/zip-linked-list


# This is an input class. Do not edit.
class LinkedList:
    def __init__(self, value):
        self.value = value
        self.next = None

    def __str__(self) -> str:
        s = ""
        temp = self

        while temp is not None:
            s += f"{temp.value} -> "
            temp = temp.next

        return s[:-4]


def zipLinkedList(linkedList):
    if linkedList.next is None or linkedList.next.next is None:
        return linkedList

    firstHalfHead = linkedList
    secondHalfHead = splitLinkedList(linkedList)
    reversedSecondHalfHead = reverseLinkedList(secondHalfHead)
    return merge(firstHalfHead, reversedSecondHalfHead)


def merge(list1, list2):
    node1 = list1
    node2 = list2

    while node1 is not None and node2 is not None:
        node1Next = node1.next
        node2Next = node2.next

        node1.next = node2
        node2.next = node1Next
        node1 = node1Next
        node2 = node2Next

    return list1


def splitLinkedList(linkedList: LinkedList) -> LinkedList:
    slow = linkedList
    fast = linkedList

    while fast is not None and fast.next is not None:
        slow = slow.next
        fast = fast.next.next

    split = slow.next
    slow.next = None
    return split


def reverseLinkedList(head: LinkedList) -> LinkedList:
    prev = None
    cur = head

    while cur is not None:
        next = cur.next
        cur.next = prev  # type: ignore
        prev = cur
        cur = next

    return prev  # type: ignore
