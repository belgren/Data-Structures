public class Stack<T> {
  private LinkedList<T> stack;
  private LinkedListNode<T> first;

  public Stack()  {
    stack = new LinkedList<T>();
  }

  public void push(T new_element) {
    stack.addFront(new_element);
  }

  public LinkedListNode<T> pop()  {
    first = stack.getFirst();
    stack.removeFirst();
    return first;
  }

  public LinkedListNode<T> peek() {
    return stack.getFirst();
  }

  public boolean isEmpty() {
    return stack.isEmpty();
  }

  public int size()  {
    return stack.size();
  }

  public static void main(String[] args)  {

    Stack<String> my_stack = new Stack<String>();

    my_stack.push("one");
    my_stack.push("two");
    System.out.println(my_stack.pop());
    System.out.println(my_stack.peek());
    System.out.println(my_stack.size());
    System.out.println(my_stack.isEmpty());

  }
}
