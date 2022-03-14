package csit254project03simulationusingqueues21;

/**
 *
 * @author Veniskumar Patel
 */
public class Queue<E> {

    private Node<E> front;
    private Node<E>rear;
    private int numElements;

    /**
     * no arg-constructor
     *
     */
    public Queue() {
        front = null;
        rear = null;
        numElements = 0;

    }

    /**
     * The add method adds a new node to Queue and adds 1 to numElements
     *
     * @param element E to top of queue and increases numElements by 1
     */
    public void add(E element) {
        if (numElements == 0) {
            front = new Node(element, null);
            rear = front;
            numElements++;
        } else {
            Node newNode = new Node(element, rear);
            rear = newNode;
            numElements++;
        }
    }

    /**
     * The remove method removes node from Queue and decrements numElements by 1
     *
     * @return the value form the element field
     *
     * @throws EmptyQueue exception if queue is empty-throw 
     */
    public E remove() throws EmptyQueue {

        if (front == null) {
            throw new EmptyQueue();
        }
        E element = front.getData();
        Node temp = rear;
        if (numElements == 1) {
            numElements--;
            front = null;
            rear = null;
            return element;
        }
        while (temp.getNext() != front) {
            temp = temp.getNext();
        }
        front = temp;
        front.setNext(null);

        numElements--;

        return element;
    }
    /**
     * The size method returns value of numElements
     * 
     * @return the value form the numElements field 
     */
    public int size() {

        return numElements;
    }
}

