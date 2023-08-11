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
    n = getListLength(head)
    dummy = LinkedList("x")
    dummy.next = head
    insertPos = 0

    prev = dummy
    cur = head
    next = head.next
    for _ in range(n):
        if cur.value < k:
            temp = cur
            cur = next
            next = cur.next
            prev.next = cur
            insertAt(temp, dummy, insertPos)
            insertPos += 1
        elif cur.value > k:
            temp = cur
            cur = next
            next = cur.next
            prev.next = cur
            moveToEnd(temp, dummy)
        else:
            prev = cur
            cur = prev.next
            next = cur.next

    return dummy.next


def getListLength(head: LinkedList) -> int:
    temp = head
    i = 0
    while temp is not None:
        i += 1
        temp = temp.next

    return i


def moveToEnd(node: LinkedList, cur: LinkedList) -> None:
    while cur.next is not None:
        cur = cur.next

    cur.next = node
    node.next = None


def insertAt(node: LinkedList, prev: LinkedList, pos: int) -> None:
    for i in range(pos):
        prev = prev.next

    node.next = prev.next
    prev.next = node


head = LinkedList(3)
temp = head
for i in [0, 5, 2, 1, 4]:
    temp.next = LinkedList(i)
    temp = temp.next

print(rearrangeLinkedList(head, 3))
