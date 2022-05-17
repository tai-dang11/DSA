package language;

public abstract class UnaryOperator<T> implements Operator<T> {

    protected Operand<T> op0;

    @Override
    public final int getNumberOfArguments() {
        return 1;
      }

    @Override
    public void setOperand(int position, Operand<T> operand) {
        // TODO Auto-generated method stub
        if (operand == null) throw new NullPointerException("Could not set null operand.");
        if (position >= 1)
        throw new IllegalArgumentException(
            "Binary operator only accepts operands 0 and 1 but recieved " + position + ".");
        if (position == 0) {
            if (op0 != null)
            throw new IllegalStateException("Position " + position + " has been previously set.");
            op0 = operand;
        }     
    }

}
