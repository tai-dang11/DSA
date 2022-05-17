package structures;

public class ScapegoatTree<T extends Comparable<T>> extends BinarySearchTree<T> {
  private int upperBound;

  @Override
  public void add(T t) {
    // TODO: Implement the add() method
    BSTNode<T> u = new BSTNode<T>(t, null, null);
    if (t == null) {
      throw new NullPointerException();
    }
    root = addToSubtree(root,u);

    upperBound++;
    int d = height();

    if (d > (Math.log(upperBound)/Math.log(1.5))){
      BSTNode<T> w = u.getParent();
      while (3*subtreeSize(w) <= 2*subtreeSize(w.getParent())){
        w = w.getParent();
      }
      Balancing(w.getParent());
    }
  }

  @Override
  public boolean remove(T element) {
    // TODO: Implement the remove() method
    if (super.remove(element)){
      if (2*size() < upperBound){
        balance();
        upperBound = size();
      }
      return true;
    }
    return false;
  }
}
