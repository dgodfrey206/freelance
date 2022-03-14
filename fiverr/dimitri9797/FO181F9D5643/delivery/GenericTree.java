package treeAssign;

import java.util.Iterator;

/**
 * @author sarwars1
 *
 */
public class GenericTree<T> implements Iterable<T> {

  private int size;
  private int index;
  private Object root;
  private Node<T> nArray;

  public GenericTree() {
    super();
    this.size = 0;
    this.index = -1;
    this.root = null;
    this.nArray = null;
  }

  public<T extends Comparable<T>> boolean add(T t) {
    boolean result = false;

    Node<T> newNode = new Node(t);
    Node<T> oldNode = (Node<T>)root;

    this.adjustTree(newNode, oldNode);

    this.size++;
    return result;
  }

  private<T extends Comparable<T>> Node<T> adjustTree(Node<T> newNode, Node<T> oldNode) {
    // TODO Auto-generated method stub
    Node<T> result = null;

    if (oldNode == null) {
      if (this.size == 0) {
        this.setRoot(newNode);
      }
    } else if (this.isLarger(newNode, oldNode)) {
      result = this.adjustTree(newNode, oldNode.getlNode());
      if (result == null) {
        oldNode.setlNode(newNode);
        newNode.setpNode(oldNode);
      }
      result = newNode;
    } else {
      result = this.adjustTree(newNode, oldNode.getrNode());
      if (result == null) {
        oldNode.setrNode(newNode);
        newNode.setpNode(oldNode);
      }
      result = newNode;

    }

    return result;
  }

  private<T extends Comparable<T>> boolean isLarger(Node<T> newNode, Node<T> oldNode) {
    return ((T)newNode.getData()).compareTo(oldNode.getData()) > 0;
  }

  public<T extends Comparable<T>> Node<T> Search(T value) {
    Node<T> [] res = new Node[getSize()];
    this.setIndex(0);
    inOrder(res, (Node<T>) root);
    for (int i = 0; i < getSize(); i++) {
      if (res[i].getData().compareTo(value) == 0) {
        return res[i];
      }
    }
    return null;
  }

  private<T> void inOrder(Node<T>[] nArray, Node<T> node) {
    Node<T> result = null;

    if (node == null) {
      return;
    }
    inOrder(nArray, node.getlNode());
    nArray[index++] = node;
    inOrder(nArray, node.getrNode());
  }

  @SuppressWarnings("unused")
  private<T> Node<T> inOrderAscending(Node<T>[] nArray, Node<T> node) {
    Node<T> result = null;

    if (node == null) {
      return null;
    }

    inOrderAscending(nArray, node.getrNode());
    nArray[index++] = node;
    inOrderAscending(nArray, node.getlNode());

    return result;
  }

  public void forEach(Function<T> func) {
    Node<T>[] nArray = new Node[this.getSize()];
    this.setIndex(0);
    this.inOrderAscending(nArray, (Node<T>)this.getRoot());
    for (int i = 0; i < this.size; i++) {
      func.forEach(nArray[i]);
    }
    nArray = null;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public Object getRoot() {
    return root;
  }

  public void setRoot(Object root) {
    this.root = root;
  }

  @Override
  public Iterator<T> iterator() {
    // TODO Auto-generated method stub
    return new CustomIterator<T>(this);
  }

  public class CustomIterator<T> implements Iterator<T> {

    private GenericTree<T> collection;
    private Node<T>[] nArray;
    int cursor;

    @SuppressWarnings("unchecked")
    public CustomIterator(GenericTree<T> genericTree) {
      // TODO Auto-generated constructor stub
      this.collection = genericTree;
      this.cursor = 0;
      this.nArray = new Node[this.collection.getSize()];
      this.collection.setIndex(0);
      this.collection.inOrder(nArray, (Node<T>)this.collection.getRoot());
    }

    @Override
    public boolean hasNext() {
      // TODO Auto-generated method stub
      boolean result = false;
      if (this.cursor < size) {
        result = true;
      } else {
        for (int i = 0; i < this.collection.getSize(); i++) {
          nArray[i] = null;
        }

        nArray = null;
      }

      return result;
    }

    @Override
    public T next() {
      // TODO Auto-generated method stub
      Node<T> node = nArray[cursor++];
      return node.getData();
    }

  }

}
