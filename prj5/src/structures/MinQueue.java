package structures;

import comparators.ReverseIntegerComparator;

import java.util.Comparator;
import java.util.Iterator;

public class MinQueue<V> implements PriorityQueue<Integer, V>{

  // TODO Auto-generated constructor stub

  
  Comparator<Integer> C = new ReverseIntegerComparator();
  StudentArrayHeap<Integer,V> heap = new StudentArrayHeap<Integer,V>(C);

  @Override
  public PriorityQueue<Integer, V> enqueue(Integer priority, V value) {
    // TODO Auto-generated method stub
    if (priority == null || value == null)
      throw new NullPointerException();
      heap.add(priority, value);
    return this;
  }

  @Override
  public V dequeue() {
    // TODO Auto-generated method stub
    if (size() == 0)
      throw new IllegalStateException();
    V value = heap.remove();
    return value;

  }

  @Override
  public V peek() {
    // TODO Auto-generated method stub
    if (size() == 0)
      throw new IllegalStateException();
    return heap.peek();
  }

  @Override
  public Iterator<Entry<Integer, V>> iterator() {
    // TODO Auto-generated method stub
    Iterator<Entry<Integer, V>> iter = heap.asList().iterator();
    return iter;
  }


  @Override
  public Comparator<Integer> getComparator() {
    // TODO Auto-generated method stub
    return C;
  }

  @Override
  public int size() {
    // TODO Auto-generated method stub
    return heap.size();
  }

  @Override
  public boolean isEmpty() {
    // TODO Auto-generated method stub
    return heap.isEmpty();
  }
  // TODO: Implement all abstract methods from PriorityQueue.
}
