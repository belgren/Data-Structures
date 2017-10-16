public class Queue<T> {
  private DoublyLinkedList<T> queue;

  public Queue() {
    queue = new DoublyLinkedList<T>();
  }

  public void enqueue(T new_element) {
    queue.addToBack(new_element);
  }

  public T dequeue() {
    T dequeued;
    dequeued = queue.getFirst().getElement();
    queue.removeFirst();
    return dequeued;
  }

  public DLLNode look() {
    return queue.getFirst();
  }

  public boolean isEmpty()  {
    return queue.isEmpty();
  }

  public int size()  {
    return queue.length();
  }

  public static void main(String[] args) {

    Queue<String> my_queue = new Queue<String>();

    my_queue.enqueue("one");
    my_queue.enqueue("two");
    my_queue.enqueue("three");
    System.out.println(my_queue.size());
    System.out.println(my_queue.dequeue());
    System.out.println(my_queue.look());
    System.out.println(my_queue.isEmpty());
  }
}
