package treeAssign;

public class Node<T> {
  private T data;
  private Node parent;
  private Node left;
  private Node right;

  public Node(T initData) {
    data = initData;
    parent = null;
    left = null;
    right = null;
  }
  public void setlNode(Node<T> node) {
    left = node;
  }
  public void setrNode(Node<T> node) {
    right = node;
  }
  public void setpNode(Node<T> node) {
    parent = node;
  }
  public void setData(T newData) {
    data = newData;
  }
  public T getData() {
    return data;
  }
  public Node<T> getpNode() {
    return parent;
  }
  public Node<T> getlNode() {
    return left;
  }
  public Node<T> getrNode() {
    return right;
  }
}
