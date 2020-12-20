/**
 * Simple SinglyLinkedList
 */
public class SinglyLinkedList
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

    private void deleteNode(int data)
    {
        // Empty list
        if (start==null)
            return;
        
        // delete first node
        if (start.data == data)
            start=start.next;

        Node currentNode = start;
        while(currentNode.next != null)
        {
            if (currentNode.next.data == data)
            {
                currentNode.next = currentNode.next.next;
                break;
            }
            currentNode=currentNode.next;
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
    }

    public static void main(String args[])
    {
        SinglyLinkedList sll=new SinglyLinkedList();
        sll.addNode(1);
        sll.addNode(2);
        sll.addNode(3);
        sll.addNode(4);
        
        sll.deleteNode(3);

        //Displays the nodes present in the list    
        sll.display();    
    }
}
