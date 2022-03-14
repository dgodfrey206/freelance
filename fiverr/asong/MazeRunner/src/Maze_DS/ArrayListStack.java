
package Maze_DS;
import ADTs.StackADT;
import Exceptions.EmptyCollectionException;
import java.util.*;
/**
 *
 * @author user
 */
public class ArrayListStack <T> /*implements StackADT <T>*/   {
    private int count;
    private ArrayList<T> top;

    /**
     * Initializes the Stack
     *
     */
    public ArrayListStack() {
        count = 0;
        top = new ArrayList<T>();

    }

    /**
     * Removes the top element
     *
     * @returns the previous top element
     * @exception EmptyCollectionException
     */
    @Override
    public T pop() throws EmptyCollectionException {
        T result;
        if (count != 0) {
            result = top.remove(size() - 1);
            count--;
        } else {
            throw new EmptyCollectionException();
        }
        return result;

    }

    /**
     * Pushes an element to the stack
     *
     * @param element The element to push to the stack
     */
    @Override
    public void push(T element)   {
      top.add(element);
      count++;
  }

    /**
     * Returns the topmost element on the stack
     *
     * @exception EmptyCollectionException
     */
    @Override
    public T peek() throws EmptyCollectionException {
        T result;
        if (count != 0) {
          result = top.get(size() - 1);

        } else {
            throw new EmptyCollectionException();
        }
        return result;

    }

    /**
     * Returns a boolean indicating if the stack is empty
     *
     * @returns true or false
     */
    @Override
    public boolean isEmpty() {
        if (count == 0) {
            return true;
        }
        return false;
    }

    /**
     * The size of the stack
     *
     * @returns an integer size
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * String representation of the stack
     *
     * @returns a string with the size of the stack as well as the elements
     */
    @Override
    public String toString() {
        return "LinkedListStack{" + "count=" + count + ", top=" + top + '}';
    }

}
