
package Chap2_LinkedList;

/**
 * 2.2 Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.
Hints: #8, #25, #47, #67, # 726
 */
public class GetKthLastNode
{
    private static class Node{
        Node next;
        int data;
    }

    private Node start;

    // recursive - only get data and NOT node
    private int getKthLastNodeRecursiveVal(int k)
    {
        return getKthLastNodeRecursiveValHelper(this.start,k);
    }

    private int getKthLastNodeRecursiveValHelper(Node p, int k)
    {
        if (p == null)
            return 0;

        int index=getKthLastNodeRecursiveValHelper(p.next,k) + 1;

        if(index == k)
            System.out.println(k+ "th last node value is: "+p.data);

        return index;
    }

    // recursive - get Node
    private static class Index{
        int value = 0;
    }

    private Node getKthLastNodeRecursive(int k)
    {
        Index idx= new Index();
        return getKthLastNodeRecursiveHelper(this.start, k, idx);
    }

    private Node getKthLastNodeRecursiveHelper(Node p, int k, Index idx)
    {
        if (p == null)
        return null;
        
        Node node = getKthLastNodeRecursiveHelper(p.next, k, idx);
        idx.value++;
        if(idx.value == k)
            return p;
        
        return node;
    }

    // iterative
    private Node getKthLastNode(int k)
    {
        Node p = this.start;
        Node runner = this.start;

        for(int i=0;i<k;i++)
        {
            if(runner.next!=null)
                runner=runner.next;
            else 
                return null;
        }

        while(runner != null)
        {
            runner=runner.next;
            p=p.next;
        }

        return p;
    }
}
