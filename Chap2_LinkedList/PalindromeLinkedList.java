package Chap2_LinkedList;

import java.util.Stack;

/*
2.6 Palindrome: Implement a function to check if a linked list is a palindrome.
Hints: #5, #13, #29, #61, #101
 */
public class PalindromeLinkedList {

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node start;

    void addNode(int data) {
        Node newNode = new Node(data);

        if (this.start == null)
            start = newNode;
        else {
            Node last = this.start;

            while (last.next != null)
                last = last.next;

            last.next = newNode;
        }
    }

    void display() {
        if (this.start == null)
            System.out.println("Empty");
        else {
            Node currentNode = this.start;

            while (currentNode != null) {
                System.out.print(currentNode.data + " ");
                currentNode = currentNode.next;
            }
        }

        System.out.println();
    }

    // Solution 1 - iterative: reverse and compare
    private static Node reverseAndClone(Node node) {
        Node resultHead = null;

        while (node != null) {
            Node tempNode = new Node(node.data);    // Clone
            tempNode.next = resultHead;
            resultHead = tempNode;

            node = node.next;
        }
        return resultHead;
    }

    private static boolean isEqual(Node n1, Node n2) {
        while (n1 != null && n2 != null) {
            if (n1.data != n2.data)
                return false;
            n1 = n1.next;
            n2 = n2.next;
        }

        return n1 == null && n2 == null;
    }

    private static boolean isPalindromeSolution1(Node node) {
        Node reversedListNode = PalindromeLinkedList.reverseAndClone(node);
        return isEqual(node, reversedListNode);
    }

    // Solution 2 - runner technique, add to stack and compare while popping
    private static boolean isPalindromeSolution2(Node node) {
        // runner technique to find middle element
        Node n1 = node;
        Node n2 = node;

        // stack to store first half elements
        Stack<Node> nodeStack = new Stack<>();

        // to keep track of odd/even elements
        while (n2 != null && n2.next != null) {
            nodeStack.add(n1);

            n1 = n1.next;
            // for every 1 step of n1, move forward 2 steps for n2
            n2 = n2.next;
            n2 = n2.next;
        }

        // n1 is at middle
        // odd elements because n2.next == null
        if (n2 != null)
            n1 = n1.next;

        // popping stack and comparing
        while (n1 != null) {
            Node poppedNode = nodeStack.pop();
            if (poppedNode.data != n1.data)
                return false;
            n1 = n1.next;
        }

        return true;
    }

    // Solution 3 - recursive
    private static class PartialResult {
        Node node;
        boolean result;

        public PartialResult(Node node, boolean result) {
            this.node = node;
            this.result = result;
        }
    }

    private static boolean isPalindromeSolution3(Node node) {
        int nodeListLength = getNodeListLength(node);
        PartialResult partialResult = isPalindromeRecurse(node, nodeListLength);
        return partialResult.result;
    }

    private static PartialResult isPalindromeRecurse(Node node, int length) {
        if (node == null || length == 0) // Even number of nodes
            return new PartialResult(node, true);
        else if (length == 1) // Odd number of nodes
            return new PartialResult(node.next, true);

        // recurse on sublist
        PartialResult res = isPalindromeRecurse(node.next, length - 2);

        // If child calls are not a palindrome, pass back up a failure
        if (res.result == false || res.node == null)
            return res;

        // Checking if current node matches corresponding node on the other side
        res.result = (node.data == res.node.data);

        // return next node
        res.node = res.node.next;

        return res;
    }

    // returns length of node list
    private static int getNodeListLength(Node node) {
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        return count;
    }

    public static void main(String[] args) {
        PalindromeLinkedList pll = new PalindromeLinkedList();
        pll.addNode(0);
        pll.addNode(1);
        pll.addNode(2);
        pll.addNode(1);
        pll.addNode(0);

        pll.display();

        System.out.println("\nSolution 1 - iterative: reverse and compare");
        if (isPalindromeSolution1(pll.start))
            System.out.println("Palindrome");
        else
            System.out.println("Not a Palindrome");

        System.out.println("\nSolution 2 - runner technique, add to stack and compare while popping");
        if (isPalindromeSolution2(pll.start))
            System.out.println("Palindrome");
        else
            System.out.println("Not a Palindrome");

        System.out.println("\nSolution 3 - recursive");
        if (isPalindromeSolution3(pll.start))
            System.out.println("Palindrome");
        else
            System.out.println("Not a Palindrome");
    }
}
