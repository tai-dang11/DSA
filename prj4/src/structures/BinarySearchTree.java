package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;



public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T> {
  protected BSTNode<T> root;

  
  public boolean isEmpty() {
    return root == null;
  }

  public int size() {
    return subtreeSize(root);
  }

  protected int subtreeSize(BSTNode<T> node) {
    if (node == null) {
      return 0;
    } else {
      return 1 + subtreeSize(node.getLeft()) + subtreeSize(node.getRight());
    }
  }

  public boolean contains(T t) {
    // TODO: Implement the contains() method
    if (t == null)
      throw new NullPointerException();

    if (get(t) != null)
      return true;
    else
      return false;


  }

  public boolean remove(T t) {
    if (t == null) {
      throw new NullPointerException();
    }
    boolean result = contains(t);
    if (result) {
      root = removeFromSubtree(root, t);
    }
    return result;
  }

  private BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {
    // node must not be null
    int result = t.compareTo(node.getData());
    if (result < 0) {
      node.setLeft(removeFromSubtree(node.getLeft(), t));
      return node;
    } 
    else if (result > 0) {
      node.setRight(removeFromSubtree(node.getRight(), t));
      return node;
    } 
    else { // result == 0
      if (node.getLeft() == null) {
        return node.getRight();
      } else if (node.getRight() == null) {
        return node.getLeft();
      } else { // neither child is null
        T predecessorValue = getHighestValue(node.getLeft());
        node.setLeft(removeRightmost(node.getLeft()));
        node.setData(predecessorValue);
        return node;
      }
    }
  }

  private T getHighestValue(BSTNode<T> node) {
    // node must not be null
    if (node.getRight() == null) {
      return node.getData();
    } else {
      return getHighestValue(node.getRight());
    }
  }

  private BSTNode<T> removeRightmost(BSTNode<T> node) {
    // node must not be null
    if (node.getRight() == null) {
      return node.getLeft();
    } else {
      node.setRight(removeRightmost(node.getRight()));
      return node;
    }
  }

  public T get(T t) {
    // TODO: Implement the get() method 
    if (t == null)
      throw new NullPointerException();
    return findNode(root, t);
  }

  private T findNode(BSTNode<T> base, T t){
    if (base == null){
      return null;
    }
    if (base.getData().compareTo(t) == 0)
      return base.getData();

    if (t.compareTo(base.getData()) > 0)
      return findNode(base.getRight(), t);

    else   
      return findNode(base.getLeft(), t);

  }

  public void add(T t){
    if (t == null) {
      throw new NullPointerException();
      // return;
    }
    root = addToSubtree(root, new BSTNode<T>(t, null, null));
  }

  protected BSTNode<T> addToSubtree(BSTNode<T> node, BSTNode<T> toAdd) {
    if (node == null) {
      return toAdd;
    }
    int result = toAdd.getData().compareTo(node.getData());
    if (result <= 0) {
      node.setLeft(addToSubtree(node.getLeft(), toAdd));

    } else {
      node.setRight(addToSubtree(node.getRight(), toAdd));

    }
    return node;
  }

  @Override
  public T getMinimum() {
    // TODO: Implement the getMinimum() method

    BSTNode<T> temp = root;
    if (root == null)
      return null;

    while (temp.getLeft() != null){
      temp = temp.getLeft();
    }

    return temp.getData();
  }


  @Override
  public T getMaximum() {
    // TODO: Implement the getMaximum() method
    if (root == null)
      return null;
      
    BSTNode<T> temp = root;
    while (temp.getRight() != null){
      temp = temp.getRight();

    }

    return temp.getData();
  }


  @Override
  public int height() {
    // TODO: Implement the height() method
    return subHeight(root);
  }



  public Iterator<T> preorderIterator() {
    // TODO: Implement the preorderIterator() method
    Queue<T> queue = new LinkedList<T>();
    preorderTraverse(queue, root);
    return queue.iterator();
  }


  public void preorderTraverse(Queue<T> queue, BSTNode<T> node) {
    if (node == null)
      return;
    else{
      queue.add(node.getData());
    }
    if (node.getLeft() != null)
      preorderTraverse(queue,node.getLeft());

    if (node.getRight() != null){
      preorderTraverse(queue,node.getRight());
    }
    
  }

  public Iterator<T> inorderIterator() {
    return inorderIteratorHelper(getRoot());
  }

  private Iterator<T> inorderIteratorHelper(BSTNode<T> base){
    Queue<T> queue = new LinkedList<T>();
    inorderTraverse(queue, base);
    return queue.iterator();
  }

  private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
    if (node != null) {
      inorderTraverse(queue, node.getLeft());
      queue.add(node.getData());
      inorderTraverse(queue, node.getRight());
    }
  }

  public Iterator<T> postorderIterator() {
    // TODO: Implement the postorderIterator() method
    Queue<T> queue = new LinkedList<T>();
    postderTraverse(queue, root);
    return queue.iterator();
  }

  public void postderTraverse(Queue<T> queue, BSTNode<T> node) {

    if (node != null && (node.getLeft() != null || node.getRight() != null)){
      postderTraverse(queue,node.getLeft());
      postderTraverse(queue,node.getRight());
    }

    if (node == null)
      return;
    if (node != null){
      queue.add(node.getData());
    }
  }


  @Override
  public boolean equals(BSTInterface<T> other) {
    // TODO: Implement the equals() method
    // BSTNode<T> root2 = other.getRoot();
    // if (root == null && root2 == null)
    //   return true;

    // if (root2 == null || root == null)
    //   return false;

    // Queue<BSTNode<T>> queue1 = new LinkedList<BSTNode<T>>();
    // Queue<BSTNode<T>> queue2 = new LinkedList<BSTNode<T>>();
    // queue1.add(root);
    // queue2.add(root2);

    // while(queue1.isEmpty() && queue2.isEmpty()){
    //   if (queue1.peek().getData() != queue2.peek().getData())
    //     return false;
      
    //   BSTNode<T> temp1 = queue1.remove();
    //   BSTNode<T> temp2 = queue2.remove();

    //   if (temp1.getLeft() != null && temp2.getLeft() != null){
    //     queue1.add(temp1.getLeft());
    //     queue2.add(temp2.getLeft());
    //   }

    //   else if (temp1.getLeft() != null || temp2.getLeft() != null)
    //     return false;

    //   if (temp1.getRight() != null && temp2.getRight() != null){
    //     queue1.add(temp1.getRight());
    //     queue2.add(temp2.getRight());
    //   }
    //   else if (temp1.getRight() != null || temp2.getRight() != null)
    //     return false;
      
    // }
    // return true;
    return equalsHelper(root, other.getRoot());
  }


  protected boolean equalsHelper(BSTNode<T> base,BSTNode<T> other){
    if (base == null && other == null)
      return true;
    if (base == null || other == null)
      return false;
    if (base.getData() != other.getData())
      return false;
    return equalsHelper(base.getLeft(), other.getLeft()) && equalsHelper(base.getRight(), other.getRight());
    
  }

  @Override
  public boolean sameValues(BSTInterface<T> other) {
    // TODO: Implement the sameValues() method
    Iterator<T> case1 = inorderIterator();
    Iterator<T> case2 = other.inorderIterator();
    T data1 = null;
    T data2 = null;
    if ((case1.hasNext() && case2.hasNext() == false) || (case1.hasNext() == false && case2.hasNext()))
      return false;

    while (case1.hasNext() && case2.hasNext()){
      data1 = case1.next();
      data2 = case2.next();
      if (data1.compareTo(data2) ==0){
        continue;
      }
      else
        return false;
      
    }
    return true;
  }

  @Override
  public boolean isBalanced() {
    // TODO: Implement the isBalanced() method

    int count = size();
    if (Math.pow(2, height()) <= count && count < Math.pow(2, height()+1))
      return true;

    return false;

  }



  public int subHeight(BSTNode<T> base){

    if (base == null)
      return -1;
    int left = subHeight(base.getLeft());
    int right = subHeight(base.getRight());
    return Math.max(left, right) +1;

  }

  @Override
  
  public void balance() {
    // TODO: Implement the balanceHelper() method
    Balancing(root);
  }

  @SuppressWarnings("unchecked")
  public void Balancing(BSTNode<T> base){
    Iterator<T> inorder = inorderIteratorHelper(base);
    int size = subtreeSize(base);
    T[] arr = (T[]) new Comparable[size];

    for (int i =0; i < size;i++){
      arr[i] = inorder.next();
    }
    BSTNode<T> parent = base.getParent();
    if (parent != null){
      if (parent.getData().compareTo(base.getData()) > 0){
        parent.setLeft(null);
      }
      else
        parent.setRight(null);
    }
    else
      root = null;

    balanceHelper(arr, 0, size-1);

  }

  // @SuppressWarnings("unchecked")
  private void balanceHelper(T[] arr, int low, int high){
    
    if (low == high)
      add(arr[low]);

    else if((low + 1) == high){
      add(arr[low]);
      add(arr[high]);
    }

    else{
      int mid = (low + high)/2;
      add(arr[mid]);
      balanceHelper(arr, low, mid-1);
      balanceHelper(arr, mid+1, high);
    }
  }


  @Override
  public BSTNode<T> getRoot() {
    // DO NOT MODIFY
    return root;
  }

  public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
    // header
    int count = 0;
    String dot = "digraph G { \n";
    dot += "graph [ordering=\"out\"]; \n";
    // iterative traversal
    Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
    queue.add(root);
    BSTNode<T> cursor;
    while (!queue.isEmpty()) {
      cursor = queue.remove();
      if (cursor.getLeft() != null) {
        // add edge from cursor to left child
        dot += cursor.getData().toString() + " -> " + cursor.getLeft().getData().toString() + ";\n";
        queue.add(cursor.getLeft());
      } else {
        // add dummy node
        dot += "node" + count + " [shape=point];\n";
        dot += cursor.getData().toString() + " -> " + "node" + count + ";\n";
        count++;
      }
      if (cursor.getRight() != null) {
        // add edge from cursor to right child
        dot +=
            cursor.getData().toString() + " -> " + cursor.getRight().getData().toString() + ";\n";
        queue.add(cursor.getRight());
      } else {
        // add dummy node
        dot += "node" + count + " [shape=point];\n";
        dot += cursor.getData().toString() + " -> " + "node" + count + ";\n";
        count++;
      }
    }
    dot += "};";
    return dot;
  }

  public static void main(String[] args) {
    for (String r : new String[] {"a", "b", "c", "d", "e", "f", "g"}) {
      BSTInterface<String> tree = new BinarySearchTree<String>();
      for (String s : new String[] {"d", "b", "a", "c", "f", "e", "g"}) {
        tree.add(s);
      }
      Iterator<String> iterator = tree.inorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
      iterator = tree.preorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
      iterator = tree.postorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();

      System.out.println(tree.remove(r));

      iterator = tree.inorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
    }

    BSTInterface<String> tree = new BinarySearchTree<String>();
    for (String r : new String[] {"a", "b", "c", "d", "e", "f", "g"}) {
      tree.add(r);
    }
    System.out.println(tree.size());
    System.out.println(tree.height());
    System.out.println(tree.isBalanced());
    tree.balance();
    System.out.println(tree.size());
    System.out.println(tree.height());
    System.out.println(tree.isBalanced());
  }



}
