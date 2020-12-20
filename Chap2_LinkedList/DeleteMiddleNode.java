package Chap2_LinkedList;

/**
 * 2.3 Delete Middle Node: Implement an algorithm to delete a node in the middle (i.e., any node but
the first and last node, not necessarily the exact middle) of a singly linked list, given only access to
that node.
EXAMPLE
Input: the node c from the linked list a - >b- >c - >d - >e- >f
Result: nothing is returned, but the new linked list looks like a - >b- >d - >e- >f
Hints: #72
 */

public class DeleteMiddleNode
{
    private static class LinkedListNode{
        LinkedListNode next;
        int data;
    }

    // n is the Node to be deleted
    boolean deleteNode(LinkedListNode n)
    {
        if (n==null)
            return false;

        // last node
        if ( n.next == null){
            n=null;
            return true;
        }
        
        n.data = n.next.data;
        n.next = n.next.next;
        return true;
    }
}
