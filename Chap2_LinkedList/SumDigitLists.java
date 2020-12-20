package Chap2_LinkedList;

/**
 * 2.5 Sum Lists: You have two numbers represented by a linked list, where each node contains a single
 * digit. The digits are stored in reverse order, such that the 1 's digit is at the head of the list. Write a
 * function that adds the two numbers and returns the sum as a linked list.
 * EXAMPLE
 * Input: (7-) 1 -) 6) + (5 -) 9 -) 2) .That is,617 + 295.
 * Output: 2 -) 1 -) 9. That is, 912.
 * FOLLOW UP
 * Suppose the digits are stored in forward order. Repeat the above problem.
 * EXAMPLE
 * Input: (6 -) 1 -) 7) + (2 -) 9 -) 5).That is,617 + 295.
 * Output: 9 -) 1 -) 2. That is, 912.
 * Hints: #7, #30, #71, #95, #109
 */
public class SumDigitLists {
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

    // iterative solution
    private static SumDigitLists nodelistDigitSum(SumDigitLists list1, SumDigitLists list2) {
        SumDigitLists sumList = new SumDigitLists();

        Node list1currentNode = list1.start;
        Node list2currentNode = list2.start;

        boolean carryOver = false;

        while (list1currentNode != null || list2currentNode != null) {
            int dataSum = 0;

            // int dataSum = (list1currentNode != null ? list1currentNode.data : 0) + (list2currentNode != null ? list2currentNode.data : 0);
            if (list1currentNode != null)
                dataSum += list1currentNode.data;
            if (list2currentNode != null)
                dataSum += list2currentNode.data;

            if (carryOver)
                dataSum++;

            carryOver = (dataSum >= 10);
            dataSum = dataSum % 10;
            sumList.addNode(dataSum);

            if (list1currentNode != null)
                list1currentNode = list1currentNode.next;

            if (list2currentNode != null)
                list2currentNode = list2currentNode.next;
        }

        if (carryOver)
            sumList.addNode(1);

        return sumList;
    }

    // recursive solution
    private static Node nodeDigitSumRecursive(Node list1node, Node list2node, int carry) {
        if (list1node == null && list2node == null && carry == 0)
            return null;

        Node sumNode;
        int val = carry + (list1node != null ? list1node.data : 0) + (list2node != null ? list2node.data : 0);
        sumNode = new Node(val % 10);

        if (list1node != null || list2node != null) {
            Node nextNode;
            nextNode = nodeDigitSumRecursive(
                    list1node != null ? list1node.next : null,
                    list2node != null ? list2node.next : null,
                    val / 10
            );

            sumNode.next = nextNode;
        }

        return sumNode;
    }

    // follow up solution
    // compute length of both lists and prepend 0's to the shorter list to make length equal
    // recursive call will return both carry as well as result

    // calculates length of the list
    private static int nodeListLength(Node node) {
        int len = 0;

        while (node != null) {
            len++;
            node = node.next;
        }

        return len;
    }

    // pads zeros to the front of the list
    private static Node padZerosToNodeList(Node startNode, int numPadding) {
        Node newNode = new Node(0);
        Node newStartNode = newNode;

        for (int i = 1; i < numPadding; i++) {
            newNode.next = new Node(0);
            newNode = newNode.next;
        }
        newNode.next = startNode;

        return newStartNode;
    }

    private static class PartialSum {
        Node sumNode = null;
        int carry = 0;
    }

    // recursive helper to return PartialSum (sum and carry from forward result)
    private static PartialSum sumListHelper(Node l1node, Node l2node) {
        if (l1node == null || l2node == null)
            return new PartialSum();

        PartialSum partialSum = new PartialSum();

        int val = (l1node.data + l2node.data);
        // current carry to send forward
        partialSum.carry = (val / 10);

        PartialSum partialSumFromBack = sumListHelper(l1node.next, l2node.next);
        int sum = (val % 10) + partialSumFromBack.carry;
        partialSum.sumNode = new Node(sum);
        partialSum.sumNode.next = partialSumFromBack.sumNode;

        return partialSum;
    }

    // actual main function for follow up
    private static Node nodeDigitSumForwardOrder(Node l1node, Node l2node) {
        if (l1node == null && l2node == null)
            return null;

        int l1len = nodeListLength(l1node);
        int l2len = nodeListLength(l2node);

        if (l1len < l2len)
            l1node = padZerosToNodeList(l1node, l1len - l2len);
        else {
            if (l1len > l2len)
                l2node = padZerosToNodeList(l2node, l2len - l1len);
        }

        PartialSum sum = sumListHelper(l1node, l2node);

        if (sum.carry == 1) {
            Node result = new Node(1);
            result.next = sum.sumNode;
            return result;
        } else
            return sum.sumNode;
    }

    public static void main(String[] agrs) {
        SumDigitLists list1 = new SumDigitLists();
        SumDigitLists list2 = new SumDigitLists();
        list1.addNode(7);
        list1.addNode(1);
        list1.addNode(6);

        list2.addNode(5);
        list2.addNode(9);
        list2.addNode(2);

//        list1.addNode(9);
//        list1.addNode(9);
//
//        list2.addNode(9);
//        list2.addNode(9);
//        list2.addNode(0);
//        list2.addNode(9);

//        list1.addNode(9);
//        list1.addNode(9);
//        list1.addNode(9);
//        list1.addNode(9);
//        list1.addNode(9);
//        list1.addNode(9);
//        list1.addNode(9);
//
//        list2.addNode(1);

        list1.display();
        list2.display();

        System.out.println("\nReverse Order Addition");

        // iterative
        System.out.println("Iterative");
        SumDigitLists list3 = SumDigitLists.nodelistDigitSum(list1, list2);
        list3.display();

        // recursive
        System.out.println("Recursive");
        SumDigitLists list4 = new SumDigitLists();
        list4.start = SumDigitLists.nodeDigitSumRecursive(list1.start, list2.start, 0);
        list4.display();

        System.out.println("\nFollow Up: Forward Order Addition");
        SumDigitLists l5list = new SumDigitLists();
        l5list.start = SumDigitLists.nodeDigitSumForwardOrder(list1.start, list2.start);
        l5list.display();
    }
}
