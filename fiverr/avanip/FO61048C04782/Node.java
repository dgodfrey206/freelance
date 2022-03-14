//package csit254project03simulationusingqueues21;

/**
 *
 * @author Veniskumar Patel
 */
public class Node<E> {

    //attributes
    private E data;
    private Node<E> next;

    /**
     *
     * @param initialData value to be used for the data field
     * @param initalNext value to be used for the next field
     */
    public Node(E initialData, Node<E> initalNext) {

        data = initialData;
        next = initalNext;

    }

    /**
     *
     * The getData returns the value form the data field
     *
     * @return the value form the data field
     *
     */
    public E getData() {
        return data;
    }

    /**
     * The getNext returns the value form the next field
     *
     * @return the value form the next field
     */
    public Node<E> getNext() {
        return next;
    }

    /**
     * The setData method assigns a new value to the data field
     *
     * @param newData value to be used for the data field
     */
    public void setData(E newData) {
        data = newData;
    }

    /**
     * The setNext method assigns a new value to the next field
     *
     * @param newNext value to be used for the next field
     */
    public void setNext(Node<E> newNext) {
        next = newNext;
    }
}

