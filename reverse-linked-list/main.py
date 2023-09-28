# https://www.algoexpert.io/questions/reverse-linked-list


class LinkedList:
    def __init__(self, value):
        self.value = value
        self.next = None


def reverseLinkedList(head: LinkedList) -> LinkedList:
    if head is None or head.next is None:
        return head

    prev = None
    cur = head

    while cur:
        next = cur.next
        cur.next = prev
        prev = cur
        cur = next

    return prev
