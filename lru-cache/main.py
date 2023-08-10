# https://www.algoexpert.io/questions/lru-cache

# Do not edit the class below except for the insertKeyValuePair,
# getValueFromKey, and getMostRecentKey methods. Feel free
# to add new properties and methods to the class.


class Node:
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.next = None
        self.prev = None


class LRUCache:
    def __init__(self, maxSize):
        self.maxSize = maxSize or 1
        self._data = {}
        self._head = Node(None, None)
        self._tail = Node(None, None)
        self._head.next = self._tail
        self._tail.prev = self._head

    def _moveToFront(self, node):
        self._removeNode(node)
        self._insertAtHead(node)

    def _removeNode(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def _insertAfter(self, existingNode, nodeToInsert):
        nodeToInsert.prev = existingNode
        nodeToInsert.next = existingNode.next

        nodeToInsert.next.prev = nodeToInsert
        existingNode.next = nodeToInsert

    def _insertAtHead(self, node):
        self._insertAfter(self._head, node)

    def _evict(self):
        node = self._tail.prev
        self._removeNode(node)
        del self._data[node.key]

    def insertKeyValuePair(self, key, value):
        if len(self._data) == self.maxSize:
            self._evict()

        if key in self._data:
            self._data[key].value = value
            self._moveToFront(self._data[key])
        else:
            self._data[key] = Node(key, value)
            self._insertAtHead(self._data[key])

    def getValueFromKey(self, key):
        if key not in self._data:
            return None

        node = self._data[key]
        self._moveToFront(node)
        return node.value

    def getMostRecentKey(self):
        return self._head.next.key


l = LRUCache(3)
l.insertKeyValuePair("b", 2)
l.insertKeyValuePair("a", 1)
l.insertKeyValuePair("c", 3)
print(l.getMostRecentKey())  # c
print(l.getValueFromKey("a"))  # 1
print(l.getMostRecentKey())  # a
l.insertKeyValuePair("d", 4)
print(l.getValueFromKey("b"))  # None
l.insertKeyValuePair("a", 5)
print(l.getValueFromKey("a"))  # 5
