//Code from lecture on 2/1/17
public class TreeNode<T extends Comparable> {

    private T value;
    private TreeNode<T> left_child;
    private TreeNode<T> right_child;

    public TreeNode(T _value) {
      value = _value;
      left_child = null;
      right_child = null;
    }

    public void addLeftChild(TreeNode<T> _child) {
      left_child = _child;
    }

    public void addRightChild(TreeNode<T> _child) {
      right_child = _child;
    }

    public TreeNode<T> getLeftChild() {
      return left_child;
    }

    public TreeNode<T> getRightChild() {
      return right_child;
    }

    public T getValue() {
      return value;
    }

    public String toString() {
      return "TreeNode: " + value.toString();
    }

}
