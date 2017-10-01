//implementation of a binary tree
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.lang.Math;

public class Tree<T extends Comparable> {

  int count = 0;
  boolean contain = false;
  TreeNode<T> previous;
  TreeNode<T> curr;
  TreeNode<T> bottom;
  TreeNode<T> prev_bottom;
  TreeNode<T> right;
  TreeNode<T> parent;
  Stack<TreeNode<T>> history = new Stack<TreeNode<T>>();
  private TreeNode<T> root;

  public Tree() {
    root = null;
  }

  public TreeNode<T> getRoot()  {
    return root;
  }

  public boolean isEmpty()  {
    if (root == null) {
      return true;
    }
    else  {
      return false;
    }
  }

  //Traverses the tree recursively, returning the highest leaf
  public int getHeight(TreeNode<T> current_node)  {
    if (current_node == null) {
        return 0;
    }
    else  {
      return 1 +  Math.max(getHeight(current_node.getLeftChild()), getHeight(current_node.getRightChild()));
    }
  }

  //recursively traverses the tree keeping a count of all visited nodes
  public void getSizeHelper(TreeNode<T> current_node) {
    if (current_node != null) {
      count++;
      getSizeHelper(current_node.getLeftChild());
      getSizeHelper(current_node.getRightChild());
    }
  }

  public int getSize()  {
    getSizeHelper(root);
    return count;
  }

  //Uses a pre-order traversal to search tree for check_node.
  public boolean contains(TreeNode<T> current_node, T check_node) {
    if (current_node != null) {
      if (current_node.getValue().equals(check_node))  {
        contain = true;
        curr = current_node;
        return contain;
      }
      if (current_node.getLeftChild() != null)  {
        previous = current_node;
        contains(current_node.getLeftChild(), check_node);
      }
      if (current_node.getRightChild() != null)  {
        previous = current_node;
        contains(current_node.getRightChild(), check_node);
      }
    }
    return contain;
  }


  //helper method to find the lowest leaf node, for comparison in balance check
  public int lowestLeafHeight(TreeNode<T> current_node) {
    if (!isEmpty()) {
      if (current_node.getRightChild()==null && current_node.getLeftChild()==null) {
        return -1;
      }
      else  {
        if (current_node.getRightChild() == null && current_node.getLeftChild() != null)  {
          return 1 + getHeight(current_node.getLeftChild());
        }
        else if (current_node.getRightChild() != null && current_node.getLeftChild() == null)  {
          return 1 + getHeight(current_node.getRightChild());
        }
        else  {
          return 1 + Math.min(getHeight(current_node.getRightChild()), getHeight(current_node.getLeftChild()));
        }
      }
    }
      else{
        return 0;
      }
  }

  //compares lowest leaf and highest leaf
  public boolean isBalanced() {
    if (getHeight(root) - lowestLeafHeight(root) > 1)  {
      return false;
    }
    else{
      return true;
    }
  }

  //searches the tree using the pre-order traversal algorithm, in order to keep
  //track of parent nodes using a stack.
  public void preorderSearch(TreeNode<T> current_node, T target)  {
    if (current_node != null) {
      history.push(current_node);
      if (current_node.getValue().equals(target))  {
      }
      else if (current_node.getValue().compareTo(target)>0) {
        preorderSearch(current_node.getLeftChild(),target);
      }
      else if (current_node.getValue().compareTo(target)<0) {
        preorderSearch(current_node.getRightChild(),target);
      }
    }
  }

  //Method to call preorderSearch and assign variables to targetnode and parentnode of target
  public void preorderSearchHelp(T target)  {
    preorderSearch(root, target);
    curr = history.pop().getElement();
    parent = history.pop().getElement();
  }

  public void remove(T target)  {
    //find target and parent in tree and assign them variables
    preorderSearchHelp(target);
    //case where target node is a leaf
    if (curr.getLeftChild()==null && curr.getRightChild()==null)  {
      if (parent.getLeftChild()==curr) {
        parent.addLeftChild(null);
      }
      else  {
        parent.addRightChild(null);
      }
    }
    //cases where target node has one child
    else if (curr.getLeftChild()!=null && curr.getRightChild()==null)  {
      parent.addLeftChild(curr.getLeftChild());
    }
    else if (curr.getLeftChild()==null && curr.getRightChild()!=null)  {
      parent.addRightChild(curr.getRightChild());
    }
    //case where the target has two children
    //finds lowest left leaf off of the right child of a the target node, then replaces target with leaf.
    else if (curr.getLeftChild()!=null && curr.getRightChild()!=null) {
      //find the lowest, leftmost leaf off of the right child of the target
      right = curr.getRightChild();
      bottom = right;
      while (bottom.getLeftChild()!= null)  {
        prev_bottom = bottom;
        bottom = bottom.getLeftChild();
      }
      //reassign replacement parent and child values.
      prev_bottom.addLeftChild(null);
      bottom.addLeftChild(curr.getLeftChild());
      bottom.addRightChild(curr.getRightChild());
      if (parent.getLeftChild() == curr)  {
        parent.addLeftChild(bottom);
      }
      else if (parent.getRightChild()== curr)  {
        parent.addRightChild(bottom);
      }
    }
  }


  public void addNode(T value) {
    TreeNode<T> new_node = new TreeNode<T>(value);

    if (root == null) {
      root = new_node;
    }
    else {
      TreeNode<T> current_node = root;
      while (current_node != null) {
        //if the new node is less than the current node
        if (new_node.getValue().compareTo(current_node.getValue()) < 0) {
          //sets new node as left child of current node if it's currently null
          if (current_node.getLeftChild() == null) {
            current_node.addLeftChild(new_node);
            return;
          }
          else {
            //if current node's left child exists
            current_node = current_node.getLeftChild();
          }
        }
        else {
          //sets new node to right child if currently null
          if (current_node.getRightChild() == null) {
            current_node.addRightChild(new_node);
            return;
          }
          else {
            current_node = current_node.getRightChild();
          }
        }
      }
    }
  }

//////////////Traversals////////////////

  //traverses the tree in order from smallest to largest node values
  public void inorderTraversalHelper(TreeNode<T> current_node) {
    if (current_node != null) {
      inorderTraversalHelper(current_node.getLeftChild());
      System.out.println(current_node);
      inorderTraversalHelper(current_node.getRightChild());
    }
  }
  public void inorderTraversal()  {
    inorderTraversalHelper(root);
  }

//traverses the tree, processing the parent nodes before the children
  public void preorderTraversalHelper(TreeNode<T> current_node) {
    if (current_node != null) {
      System.out.println(current_node);
      preorderTraversalHelper(current_node.getLeftChild());
      preorderTraversalHelper(current_node.getRightChild());
    }
  }
  public void preorderTraversal() {
    preorderTraversalHelper(root);
  }

//traverses the tree, processing the children nodes before the parents
  public void postorderTraversalHelper(TreeNode<T> current_node)  {
    if (current_node != null) {
      postorderTraversalHelper(current_node.getLeftChild());
      postorderTraversalHelper(current_node.getRightChild());
      System.out.println(current_node);
    }
  }
  public void postorderTraversal() {
    preorderTraversalHelper(root);
  }

  //helper method to print a single level of the tree
  public void printLevel(TreeNode<T> current_node ,int level)  {
    if (current_node == null) {
        return;
    }
    //base case to print all values when desired level is reached
    if (level == 1) {
        System.out.println(current_node.getValue());
    }
    else if (level > 1)  {
      //recursive call to get children as level increases
        printLevel(current_node.getLeftChild(), level-1);
        printLevel(current_node.getRightChild(), level-1);
    }
  }

  //traverses the tree one level at a time from top to bottom, (root to heighest node)
  //uses print level method, called in a loop for each level of the tree
  public void levelorderTraversal()  {
    for (int i=1; i<=getHeight(root); i++)  {
      printLevel(root, i);
    }
  }

//Test method using 'words2.txt' file included 
public static void main(String[] args) throws FileNotFoundException {

    Tree<String> myTree = new Tree();
    Scanner myScanner = new Scanner(new File("words2.txt"));
    while (myScanner.hasNext()) {
        myTree.addNode(myScanner.next());
    }
    System.out.println();
    System.out.println("Height:  " + myTree.getHeight(myTree.root));
    System.out.println("Size:  " + myTree.getSize());
    System.out.println();
    System.out.println("inorderTraversal: ");
    myTree.inorderTraversal();
    System.out.println("preorderTraversal: ");
    myTree.preorderTraversal();
    System.out.println();
    System.out.println("postorderTraversal: ");
    myTree.postorderTraversal();
    System.out.println();
    System.out.println("levelorderTraversal: ");
    myTree.levelorderTraversal();
    System.out.println();
    System.out.println("Is balanced?  " + myTree.isBalanced());
    myTree.remove("book");
  }
}
