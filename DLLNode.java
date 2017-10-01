public class DLLNode<T> {

  private T element;
  private DLLNode<T> neighbor;
  private DLLNode<T> prevNeighbor;


  public DLLNode(T _element) {
    element = _element;
    neighbor = null;
    prevNeighbor = null;
  }

  public DLLNode<T> getNeighbor() {
    return neighbor;
  }

  public DLLNode<T> getPrevNeighbor() {
    return prevNeighbor;
  }

  public void setNeighbor(DLLNode<T> new_neighbor) {
    neighbor = new_neighbor;
  }

  public void setPrevNeighbor(DLLNode<T> new_prev_neighbor) {
    prevNeighbor = new_prev_neighbor;
  }

  public T getElement() {
    return element;
  }

  public String toString()  {
    return element.toString();
  }

}
