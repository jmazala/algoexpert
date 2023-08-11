# https://www.algoexpert.io/questions/rearrange-linked-list


# This is the class of the input linked list.
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


def rearrangeLinkedList(head, k) -> LinkedList:
    lessHead = LinkedList("l")
    lessTail = lessHead
    moreHead = LinkedList("m")
    moreTail = moreHead
    equalHead = LinkedList("d")
    equalHead.next = head
    prev = equalHead
    cur = head

    while cur is not None:
        if cur.value < k:
            prev.next = cur.next
            temp = cur
            cur = cur.next
            lessTail = moveToEnd(temp, lessTail)
        elif cur.value > k:
            prev.next = cur.next
            temp = cur
            cur = cur.next
            moreTail = moveToEnd(temp, moreTail)
        else:
            prev = cur
            cur = cur.next

    lessTail.next = equalHead.next
    while lessTail.next is not None:
        lessTail = lessTail.next

    lessTail.next = moreHead.next
    return lessHead.next


def moveToEnd(node: LinkedList, tail: LinkedList) -> None:
    tail.next = node
    tail = node
    node.next = None
    return tail


head = LinkedList(3)
temp = head
for i in [0, 5, 2, 1, 4]:
    temp.next = LinkedList(i)
    temp = temp.next

print(rearrangeLinkedList(head, 3))
