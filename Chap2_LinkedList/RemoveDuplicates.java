package Chap2_LinkedList;

import java.util.HashSet;
/**
 * 2.1 Remove Dups: Write code to remove duplicates from an unsorted linked list.
FOLLOW UP
How would you solve this problem if a temporary buffer is not allowed?
 */
public class RemoveDuplicates
{
    private class Node{
        int data;
        Node next;

        Node(int data){
            this.data=data;
        }
    }

    private Node start;

    private RemoveDuplicates()
    {
    }

    private RemoveDuplicates(RemoveDuplicates rd)
    {
        this.start=rd.start;
    }

    private void addNode(int data)
    {
        Node newNode = new Node(data);

        if (this.start == null)
            this.start=newNode;
        else
        {
            Node last = this.start;
            while(last.next != null)
            {
                last = last.next;
            }

            last.next = newNode;
        }
    }

    // with buffer
    private void removeDups()
    {
        HashSet<Integer> set=new HashSet<Integer>();

        Node currentNode = this.start;
        Node prev = null;

        while (currentNode != null)
        {
            if(set.contains(currentNode.data))
            {
                prev.next=currentNode.next;
            }
            else{
                set.add(currentNode.data);
                prev=currentNode;
            }
            currentNode=currentNode.next;
        }
    }

    //without buffer
    private void removeDupsWithoutBuffer()
    {
        Node currentNode=this.start;
        Node runnerNode;
        Node prev;

        while (currentNode != null)
        {
            runnerNode=currentNode.next;
            prev = currentNode;

            while(runnerNode!=null)
            {
                if(currentNode.data == runnerNode.data)
                {
                    prev.next = runnerNode.next;
                }
                else{
                    prev = runnerNode;
                }
                runnerNode = runnerNode.next;
            }

            currentNode = currentNode.next;
        }
    }

    private void display()
    {
        if (this.start == null)
            System.out.println("Empty");
        else
        {
            Node currentNode = this.start;

            while(currentNode !=null)
            {
                System.out.print(currentNode.data+ " ");
                currentNode=currentNode.next;
            }
        }

        System.out.println();
    }

    public static void main(String args[])
    {
        RemoveDuplicates rd=new RemoveDuplicates();
        rd.addNode(1);
        rd.addNode(1);
        rd.addNode(1);
        rd.addNode(5);
        rd.addNode(1);
        rd.addNode(2);
        rd.addNode(2);

        rd.display();

        RemoveDuplicates rd2 = new RemoveDuplicates(rd);

        rd.removeDups();
        rd.display();
        
        System.out.println();
        System.out.println();
        
        rd2.removeDupsWithoutBuffer();
        rd2.display();
    }
}
