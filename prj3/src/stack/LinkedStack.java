package stack;


/**
 * A {@link LinkedStack} is a stack that is implemented using a Linked List
 * structure to allow for unbounded size.
 *
 * @param <T> the elements stored in the stack
 */
public class LinkedStack<T> implements StackInterface<T> {
  private LLNode<T> top;
  private T data;
  private int size;
  

  // public LinkedStack(){
  // top = null;
  // size = 0;
  // data = top.getData();
  // }

  /** {@inheritDoc} */
  @Override
  public T pop() throws StackUnderflowException {
    // TODO: Implement the stack operation for `pop`!
    if (top == null) {
      throw new StackUnderflowException();
    }
    data = top.getData();
    top = top.getNext();
    size--;
    return data;
  }

  /** {@inheritDoc} */
  @Override
  public T top() throws StackUnderflowException {
    // TODO: Implement the stack operation for `top`!
    if (top == null) {
      throw new StackUnderflowException();
    }
    return top.getData();
  }

  /** {@inheritDoc} */
  @Override
  public boolean isEmpty() {
    // TODO: Implement the stack operation for `isEmpty`!
    return size() == 0;
  }

  /** {@inheritDoc} */
  @Override
  public int size() {
    // TODO: Implement the stack operation for `size`!
    if (top == null)
      return 0;
    else if (top.getNext() == null)
      return 1;
    else
      return size;
  }

  /** {@inheritDoc} */
  @Override
  public void push(T elem) {
    // TODO: Implement the stack operation for `push`!
    LLNode<T> nodeToPush = new LLNode<T>(elem);
    LLNode<T> temp = top;
    nodeToPush.setNext(temp);
    top = nodeToPush;
    size++;

  }

}
