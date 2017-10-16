public class LinkedList<T>  {

  private LinkedListNode<T> first;
  private LinkedListNode<T> last;

  public LinkedList() {
    first = null;
    last = null;
  }

  public void addFront(T new_element)  {   //Add element to front of list
    LinkedListNode<T> new_node = new LinkedListNode<T>(new_element);

    //Check if list is empty
    if (first == null) {
      first = new_node;
      last = new_node;
    }
    //If list is not empty
    else {
      new_node.setNeighbor(first);
      first = new_node;
    }
  }

  public void add(T new_element)  {   //Add element to end of list
    LinkedListNode<T> new_node = new LinkedListNode<T>(new_element);
    //Check if list is empty
    if (last == null) {
      first = new_node;
      last = new_node;
    }
    //If list is not empty
    else {
      last.setNeighbor(new_node);
      last = new_node;
    }
  }

  //Adds element at certain position
  public void addAtPos(int position, T new_element) {
    LinkedListNode<T> new_node = new LinkedListNode<T>(new_element);
    LinkedListNode<T> current_node = first;
    if (position < this.size() && position > 0) {
      int count = 0;
        while (current_node != null && count+1 != position)  {
          count++;
          current_node = current_node.getNeighbor();
        }

      LinkedListNode<T> follower = current_node.getNeighbor();
      current_node.setNeighbor(new_node);
      new_node.setNeighbor(follower);
    }
  }

  public void removeFirst() {
    first = first.getNeighbor();
  }

  public void removeLast()  {
    LinkedListNode<T> current_node = first;
    while (current_node.getNeighbor() != last)  {
      current_node = current_node.getNeighbor();
    }
    last = current_node;
    current_node.setNeighbor(null);
  }

  //Removes element from certain position
  public void removePos(int position) {
    LinkedListNode<T> current_node = first;
    if (position < this.size()) {
      if( position == 0)  {
        this.removeFirst();
      }
      else{
      int count = 0;
        while (current_node != null && count+1 != position)  {
          count++;
          current_node = current_node.getNeighbor();
        }
      }
    current_node.setNeighbor(current_node.getNeighbor().getNeighbor());
    }
  }

  //Remove certain element
  public void removeEl(T target)  {
    LinkedListNode<T> current_node = first;
    LinkedListNode<T> previous = null;
    if (this.contains(target)) {
      boolean found = false;
      while (found == false)  {
        if (current_node.getElement().equals(target)) {
          found = true;
        }
        else  {
          previous = current_node;
          current_node = current_node.getNeighbor();
        }
      }
      previous.setNeighbor(current_node.getNeighbor());
    }
  }

  public LinkedListNode<T> getFirst()  {
    return first;
  }

  public LinkedListNode<T> getLast()  {
    return last;
  }

  public int size() {
    LinkedListNode<T> current_node = first;
    int count = 0;
    while (current_node != null)  {
      count++;
      current_node = current_node.getNeighbor();
    }
    return count;
  }

  public boolean isEmpty() {
    if (first == null)  {
      return true;
    }
    else {
      return false;
    }
  }

  public boolean contains(T in_question) {
    boolean contain = false;
    LinkedListNode<T> current_node = first;
    while (current_node != null && contain == false)  {
      if (current_node.getElement().equals(in_question)) {
        contain = true;
      }
      else  {
        current_node = current_node.getNeighbor();
      }
    }
    return contain;
  }


  //methods to print the list using a recursive algorithm
  public void printListRecursive()  {
    printListRecursiveHelper(first);
  }
  public void printListRecursiveHelper(LinkedListNode<T> current_node)  {
    if (current_node != null) {
      System.out.println(current_node);
      printListRecursiveHelper(current_node.getNeighbor());
    }
  }

  //method to print list using a while loop
  public void printList() {
    LinkedListNode<T> current_node = first;
    while (current_node != null)  {
      System.out.println(current_node);   //Java automatically knows to look for a "toString" method when print is called
      current_node = current_node.getNeighbor();
    }
  }

  public static void main(String[] args)  {

    LinkedList<String> names = new LinkedList<String>();

    names.add("Matthew");
    names.add("Tracy");
    names.add("Ben");
    names.add("David");
    names.printListRecursive();
    System.out.println("The first name is " + names.getFirst());
    System.out.println("The last name is " + names.getLast());
    System.out.println();
    names.addAtPos(3, "Bob");
    names.removeEl("Ben");
    names.printList();
    names.addFront("Firstname");
    names.printList();
    System.out.println(names.contains("Matthew"));
    System.out.println();
    names.removeFirst();
    names.removeLast();
    System.out.println("First and Last removed:");
    names.printList();

    System.out.println("Size is : " + names.size());
    System.out.println("Empty? : " + names.isEmpty());
    names.getFirst();
    names.getLast();
  }
}
