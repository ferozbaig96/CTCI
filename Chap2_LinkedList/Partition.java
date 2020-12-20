package Chap2_LinkedList;

import java.util.ArrayList;

/**
2.4 Partition: Write code to partition a linked list around a value x, such that all nodes less than x come
before all nodes greater than or equal to x. lf x is contained within the list, the values of x only need
to be after the elements less than x (see below). The partition element x can appear anywhere in the
"right partition"; it does not need to appear between the left and right partitions.
EXAMPLE
Input: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition = 5)
Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
Hints: #3, #24
 */

public class Partition
{
    private static class Node{
        int data;
        Node next;
        
        Node(int data){
            this.data=data;
            this.next=null;
        }
    }
    
    private Node start;

    void addNode(int data){
        Node newNode = new Node(data);
        
        if (this.start == null)
            start=newNode;
        else
        {
            Node last = this.start;
            
            while(last.next != null)
                last=last.next;
            
            last.next = newNode;
        }
    }
    
    void display(){
        if (this.start == null)
            System.out.println("Empty");
        else
        {
            Node currentNode = this.start;
            
            while(currentNode != null)
            {
                System.out.print(currentNode.data + " ");
                currentNode = currentNode.next;
            }
        }
        
        System.out.println();
    }
    
    /*
     * If order has to be preserved, then we can maintain two lists
     * 1. list having nodes less than partitionValue
     * 2. list having other nodes
     * The finally merge the two lists
     * 
     * If order does not matter, then we can have 
     * 1. add node with val < partitionValue to the start of the list
     * 2. add node with val >= partitionValue to the end of the list
     * 
     * In current case, order does not matter
     */
    void partitionList(int partitionValue)
    {
        if (this.start == null)
        {
            System.out.println("Empty list");
            return;
        }
        
        Node head = start;
        Node tail = start;
        
        Node currentNode = start;
        
        while (currentNode != null)
        {
            Node nextNode = currentNode.next;
            
            if (currentNode.data < partitionValue)
            {
                currentNode.next = head;
                head = currentNode;
            }
            else
            {
                tail.next = currentNode;
                tail = currentNode;
            }
            
            currentNode = nextNode;
        }
        tail.next = null;
        this.start = head;
    }
    
    public static void main(String agrs[])
    {
        Partition partition = new Partition();
        partition.addNode(3);
        partition.addNode(5);
        partition.addNode(8);
        partition.addNode(5);
        partition.addNode(10);
        partition.addNode(2);
        partition.addNode(1);
        
        partition.display();
        
        partition.partitionList(5);
        
        partition.display();
    }
}








