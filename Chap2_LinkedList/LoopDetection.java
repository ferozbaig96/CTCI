package Chap2_LinkedList;

/*
2.8 Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the
beginning of the loop.
DEFINITION
Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so
as to make a loop in the linked list.
EXAMPLE
Input: A -> B -> C -> D -> E - > C [the same C as earlier]
Output: C
Hints: #50, #69, #83, #90
 */

public class LoopDetection {

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node start;

    private Node findLoopBeginning(Node start) {
        Node slow = start;
        Node fast = start;

        // Finding meeting point
        while (slow != null && fast != null) {
            slow = slow.next;
            // move 2 steps ahead
            fast = fast.next.next;

            if (slow == fast) // Collision
                break;
        }

        // Error check - no meeting point, and therefore no loop
        if (slow == null || fast == null)
            return null;

        // move slow to start, keep fast at meeting point.
        // Move both slow and fast in one step each
        slow = start;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        // Both now point to the start of the loop. Return either one
        return fast;
    }
}
