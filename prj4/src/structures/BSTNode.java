package structures;

/**
 * A node in a BST.
 *
 * <p>Note that BSTNode MUST implement BSTNodeInterface; removing this will resulit in your program
 * failing to compile for the autograder.
 *
 * @author liberato
 * @param <T>
 */
public class BSTNode<T extends Comparable<T>> implements BSTNodeInterface<T> {
  private T data;
  private BSTNode<T> left;
  private BSTNode<T> right;
  private BSTNode<T> parent;


  public BSTNode(T data, BSTNode<T> left, BSTNode<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }

  // public BSTNode(T data, BSTNode<T> left, BSTNode<T> right, BSTNode<T> parent) {
  //   this.data = data;
  //   this.left = left;
  //   this.right = right;
  //   this.parent = parent;
  // }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public BSTNode<T> getLeft() {
    return left;
  }

  public void setLeft(BSTNode<T> left) {
    this.left = left;
    if (this.left != null)
      this.left.setParent(this);
  }

  public BSTNode<T> getRight() {
    return right;
  }

  public void setRight(BSTNode<T> right) {
    this.right = right;
    if (this.right != null)
      this.right.setParent(this);
  }

  private void setParent(BSTNode<T> parent) {
    this.parent = parent;
  }

  public BSTNode<T> getParent() {
    return parent;
  }

}
