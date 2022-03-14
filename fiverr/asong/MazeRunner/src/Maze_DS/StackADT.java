package ADTs;

public interface StackADT<T> {
  void pop();
  void push(T element);
  int size();
  T peek();
  boolean isEmpty();
}
