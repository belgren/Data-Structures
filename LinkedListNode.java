public class LinkedListNode<T> {

  private T element;
  private LinkedListNode<T> neighbor;

  public LinkedListNode(T _element) {
    element = _element;
    neighbor = null;
  }

  public LinkedListNode<T> getNeighbor() {
    return neighbor;
  }

  public void setNeighbor(LinkedListNode<T> new_neighbor) {
    neighbor = new_neighbor;
  }

  public T getElement() {
    return element;
  }

  public String toString()  {
    return element.toString();
  }
}
