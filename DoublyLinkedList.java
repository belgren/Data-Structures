public class DoublyLinkedList<T>  {

  private DLLNode<T> first;
  private DLLNode<T> last;
  private DLLNode<T> previous;
  private DLLNode<T> following;

  public DoublyLinkedList() {
    first = null;   //because it starts empty
    last = null;
  }

  public void addToFront(T new_element)  {   //Add element to front of list
    DLLNode<T> new_node = new DLLNode<T>(new_element);
    //Check if list is empty
    if (first == null) {
      first = new_node;
      last = new_node;
    }
    //If list is not empty
    else {
      new_node.setNeighbor(first);
      first.setPrevNeighbor(new_node);
      first = new_node;
    }
  }

  public void addToBack(T new_element)  {   //Add element to end of list
    DLLNode<T> new_node = new DLLNode<T>(new_element);
    //Check if list is empty
    if (last == null) {
      first = new_node;
      last = new_node;
    }
    //If list is not empty
    else {
      last.setNeighbor(new_node);
      new_node.setPrevNeighbor(last);
      last = new_node;
    }
  }

  //Adds element after a certain other element
  public void addAfter(T position, T new_element) {
    DLLNode<T> new_node = new DLLNode<T>(new_element);
    DLLNode<T> current_node = first;
      boolean found = false;
      if (contains(position)) {
        while (!found)  {
          if (current_node.getElement().equals(position)) {
            found = true;
          }
          else  {
            current_node = current_node.getNeighbor();
          }
        }
        following = current_node.getNeighbor();
        current_node.setNeighbor(new_node);
        new_node.setPrevNeighbor(current_node);
        new_node.setNeighbor(following);
        following.setPrevNeighbor(new_node);
      }
  }

  //Adds element at certain position
  public void addAtPos(int position, T new_element) {
    DLLNode<T> new_node = new DLLNode<T>(new_element);
    DLLNode<T> current_node = first;
    //Find element currently in target position
    if (position < this.length() && position > 0) {
      int count = 0;
        while (current_node != null && count != position)  {
          count++;
          current_node = current_node.getNeighbor();
        }
        previous = current_node.getPrevNeighbor();
        previous.setNeighbor(new_node);
        new_node.setPrevNeighbor(previous);
        new_node.setNeighbor(current_node);
        current_node.setPrevNeighbor(new_node);
    }
  }

  //removes first node
  public void removeFirst() {
    DLLNode<T> second = first.getNeighbor();
    first = second;
    if (first!=null)  {
      first.setPrevNeighbor(null);
    }
  }

  //removes last node
  public void removeLast()  {
    DLLNode<T> secToLast = last.getPrevNeighbor();
    last = secToLast;
    last.setNeighbor(null);
  }

  //removes node based on element input
  public void removeElement(T target) {
    DLLNode<T> current_node = first;
    if (this.contains(target)) {
      boolean found = false;
      while(!found) {
        if (current_node.getElement().equals(target)) {
          found = true;
        }
        else{
          current_node = current_node.getNeighbor();
        }
      }
    }
    previous = current_node.getPrevNeighbor();
    following = current_node.getNeighbor();
    previous.setNeighbor(following);
    following.setPrevNeighbor(previous);
  }

  public DLLNode<T> getFirst() {
    return first;
  }

  public DLLNode<T> getLast() {
    return last;
  }

  public int length() {
    DLLNode<T> current_node = first;
    int count = 0;
    while (current_node != null)  {
      count++;
      current_node = current_node.getNeighbor();
    }
    return count;
  }

  public boolean isEmpty()  {
    boolean empty = false;
    if (length()==0)  {
      empty = true;
    }
    else if (length() > 0) {
      empty = false;
    }
    return empty;
  }

  public boolean contains(T in_question) {
    boolean contain = false;
    DLLNode<T> current_node = first;
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


  public void printList() {
    DLLNode<T> current_node = first;
    while (current_node != null)  {
      System.out.println(current_node);
      current_node = current_node.getNeighbor();
    }
    System.out.println();
  }


  public static void main(String[] args)  {

    DoublyLinkedList<String> days = new DoublyLinkedList<String>();
    days.addToFront("Tuesday");
    days.addToFront("Monday");
    days.addToBack("Sunday");
    days.printList();
    days.addAtPos(1,"Wednesday");
    days.printList();
    days.removeLast();
    days.printList();
    System.out.println(days.isEmpty());
    System.out.println(days.contains("Monday"));
    System.out.println(days.contains("Friday"));

  }

}
