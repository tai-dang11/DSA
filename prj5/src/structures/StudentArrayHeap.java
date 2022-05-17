package structures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class StudentArrayHeap<P, V> extends AbstractArrayHeap<P, V> {


  public StudentArrayHeap(Comparator<P> comparator) {
    super(comparator);
    // TODO Auto-generated constructor stub
  }

  @Override
  public int getLeftChildOf(int index) {
    // TODO Auto-generated method stub
    if (index < 0)
      throw new IndexOutOfBoundsException();
    return 2*index +1;
  }

  @Override
  public int getRightChildOf(int index) {
    // TODO Auto-generated method stub
    if (index < 0)
      throw new IndexOutOfBoundsException();
    return 2*index +2;
  }

  @Override
  public int getParentOf(int index) {
    // TODO Auto-generated method stub
    if (index < 1)
      throw new IndexOutOfBoundsException();
    return (index-1)/2;
  }

  @Override
  protected void bubbleUp(int index) {
    // TODO Auto-generated method stub
    
    while (index > 0 && needSwap(index, getParentOf(index))){
      swap(index, getParentOf(index));
      index = getParentOf(index);
    }
  }

  @Override
  protected void bubbleDown(int index) {
    // TODO Auto-generated method stub
    while(needSwap(getLeftChildOf(index), index) || needSwap(getRightChildOf(index), index)){
      int temp = getChildToSwap(getLeftChildOf(index), getRightChildOf(index));
      swap(index, temp);
      index = temp;
    }
  }
  

  protected boolean needSwap(int child, int parent) {
    if(child >= heap.size()){
        return false;
    }
    // If our child is larger than the parent, we need to swap
    if(comparator.compare(heap.get(child).getPriority(), heap.get(parent).getPriority()) == 1){
        return true;
    }
    // If the child is not larger than the parent, we don't need to swap.
    return false;
}


private int getChildToSwap(int child1, int child2) {
    // If neither child is valid, throw an exception
    if(child1 >= heap.size() && child2 >= heap.size()){
        throw new IndexOutOfBoundsException(
            "Both children (" + child1 + " and " + child2 + ") are outside the valid range for heap of size " + heap.size()
        );
    }

    // If one child is valid, return the other
    if(child1 >= heap.size()){
        return child2;
    }
    if(child2 >= heap.size()){
        return child1;
    }

    // If both children are valid
    if(comparator.compare(heap.get(child1).getPriority(), heap.get(child2).getPriority()) == 1){
        return child1;
    } else {
        return child2;
    }
  }
  
}