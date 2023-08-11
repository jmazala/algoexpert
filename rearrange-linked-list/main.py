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


# METHOD 1 - 3 linked lists (<, >, =) and combine at end
# Iterate through the list and move nodes to separate linked lists
# for < and > values.  Combine them at the end
# This requires pointers to the heads and tails of all (3) lists
# TIME: O(n)
#   O(1) to create pointers
#   O(n) to iterate through the original list
#     O(1) to move pointers around
#   O(1) to combine lists
# SPACE: O(n)
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
    if lessTail.next is not None:  # We may not have added any equal values
        lessTail = prev  # If we did, prev tracks the tail of equal values list

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
