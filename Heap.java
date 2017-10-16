import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
public class Heap {

  private String[] data;
  private int next_index = 0;

  public Heap(int max_size){
    data = new String[max_size];
  }

  public String getMin()  {
    String min = data[0];
    return min;
  }

  public int getLeftChildIndex(int parent_index){
    return (parent_index * 2) + 1;
  }
  public int getRightChildIndex(int parent_index){
    return (parent_index * 2) + 2;
  }

  //helper method to switch the indicies of two nodes
  public void swapIndex(int current_index, int child)  {
    String temp = data[current_index];
    data[current_index] = data[child];
    data[child] = temp;
    current_index = child;
  }

  public String removeMin(){
    String result = data[0];
    String last = data[next_index - 1];
    data[0] = last;
    next_index --;

    int current_index = 0;
    int left_child_index = getLeftChildIndex(current_index);
    int right_child_index = getRightChildIndex(current_index);

    boolean done = false;
    while (!done){
      //no children for current index
      if (left_child_index >= next_index && right_child_index >= next_index){
        done = true;
      }
      //only has a left child
      else if(right_child_index >= next_index){
        if (data[current_index].compareTo(data[left_child_index]) > 0){
          swapIndex(current_index,left_child_index);
        }
        done = true;
      }
      else{
        if (data[left_child_index].compareTo(data[right_child_index]) < 0)  {
          if (data[current_index].compareTo(data[left_child_index]) > 0)  {
            swapIndex(current_index, left_child_index);
          }
          else{
            done = true;
          }
        }
        else{
          if (data[current_index].compareTo(data[right_child_index]) > 0) {
            swapIndex(current_index, right_child_index);
          }
          else{
            done = true;
          }
        }
      }
      left_child_index = getLeftChildIndex(current_index);
      right_child_index = getRightChildIndex(current_index);
    }
    return result;
  }

  ///Helper method to calculate and return parent index of given node 
  public int getParentIndex(int child_index){
    return (child_index -1) / 2;
  }

  public void add(String s){
    data[next_index] = s;
    int curr_index = next_index;
    int parent_index = getParentIndex(curr_index);
    while (parent_index >=0 && (data[curr_index].compareTo(data[parent_index]) <0)){
      String temp = data[parent_index];
      data[parent_index] = data[curr_index];
      data[curr_index] = temp;
      curr_index = parent_index;
      parent_index = getParentIndex(curr_index);
    }
    next_index++;
  }

  public static void main(String[] args) throws FileNotFoundException{
    Heap myHeap = new Heap(65000);
    Scanner myScanner = new Scanner(new File("words3.txt"));
    while (myScanner.hasNextLine()){
      String currentWord= myScanner.nextLine();
      myHeap.add(currentWord);
    }
    System.out.println(myHeap.removeMin());
  }
}
