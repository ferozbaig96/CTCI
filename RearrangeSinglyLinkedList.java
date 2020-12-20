/**
 * you had a linked list 
 * a1 - >a2 - > ••• - >an - >b1- >b2- > ••• - >bn 
 * and you wanted to rearrange it into 
 * a1- >b1- >a2- >b2- > •.• - >an- >bn• 
 * You do not know the length of the linked list
 */
public class RearrangeSinglyLinkedList
{
    class Node
    {
        int data;
        Node next;

        public Node(int data)
        {
            this.data = data;
            next = null;
        }
    }

    private Node start;

    private void addNode(int data)
    {
        Node newNode = new Node(data);

        if (start == null)
            start=newNode;
        else
        {
            Node last = start;
            while(last.next != null)
            {
                last = last.next;
            }

            last.next = newNode;
        }
    }

    private void rearrange()
    {
        Node t1=start, t2=start;
        
        while (t1 != null)
        {
            t2=t2.next;
            // t1 takes two steps for each one step of t2
            t1=t1.next;
            t1=t1.next;
        }
        
        // Now t2 is at the middle
        // weave t1,t2
        
        Node t1n, t2n;
        t1 = start;
        t1n=t1.next;
        t2n=t2.next;
        
        Node t = t1;
        t.next = t2;
        t=t.next;
        
        t1=t1n;
        t2=t2n;
        
        while (t2 !=null)
        {
            t1n=t1.next;
            t2n=t2.next;
        
            t.next = t1;
            t.next.next = t2;
            t=t.next.next;
            
            t1=t1n;
            t2=t2n;
            
        }
        
    }
    
    private void display()
    {
        if (start == null)
            System.out.println("Empty");
        else
        {
            Node currentNode = start;

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
        RearrangeSinglyLinkedList rsll=new RearrangeSinglyLinkedList();
        rsll.addNode(1);
        rsll.addNode(2);
        rsll.addNode(3);
        rsll.addNode(10);
        rsll.addNode(20);
        rsll.addNode(30);        
        
        rsll.display();   
        rsll.rearrange();   
        rsll.display();    
    }
}
