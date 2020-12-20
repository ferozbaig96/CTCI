package Chap2_LinkedList;

/*
2.7 Intersection: Given two (singly) linked lists, determine if the two lists intersect. Return the intersecting
node. Note that the intersection is defined based on reference, not value. That is, if the kth
node of the first linked list is the exact same node (by reference) as the jth node of the second
linked list, then they are intersecting.
Hints: #20, #45, #55, #65, #76, #93, #111, #120, #129
 */

public class Intersection {
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node start;

    // main function to fetch intersection node
    private Node findIntersection(Node list1, Node list2) {
        if (list1 == null || list2 == null)
            return null;

        // get tail node and sizes
        PartialResult result1 = getTailAndSize(list1);
        PartialResult result2 = getTailAndSize(list2);

        // if different tail nodes, there is no intersection
        if (result1.tail != result2.tail)
            return null;

        Node shorter = (result1.len < result2.len) ? list1 : list2;
        Node longer = (result1.len < result2.len) ? list2 : list1;

        // Advance the pointer for the longer linked list by difference in lengths
        longer = getKthNode(longer, Math.abs(result1.len - result2.len));

        while (shorter != longer) {
            shorter = shorter.next;
            longer = longer.next;
        }

        // return either one
        return longer;
    }

    private PartialResult getTailAndSize(Node node) {
        if (node == null)
            return new PartialResult(null, 0);

        int count = 0;
        Node currentNode = node;
        while (currentNode.next != null) {
            count++;
            currentNode = currentNode.next;
        }
        return new PartialResult(currentNode, count);
    }

    private static class PartialResult {
        Node tail;
        int len;

        public PartialResult(Node tail, int len) {
            this.tail = tail;
            this.len = len;
        }
    }

    private Node getKthNode(Node node, int k) {
        Node currentNode = node;
        while (currentNode != null && k > 0) {
            k--;
            currentNode = currentNode.next;
        }
        return currentNode;
    }

}
