package evaluator.arith;

import language.Operand;
import language.Operator;
import parser.IllegalPostfixExpressionException;
import parser.PostfixParser.Type;
import parser.Token;
import parser.arith.ArithPostfixParser;
import stack.LinkedStack;
import stack.StackInterface;
import evaluator.PostfixEvaluator;

/** An {@link ArithPostfixEvaluator} is a postfix evaluator over simple arithmetic expressions. */
public class ArithPostfixEvaluator implements PostfixEvaluator<Integer> {

  private final StackInterface<Operand<Integer>> stack;

  /** Constructs an {@link ArithPostfixEvaluator} */
  public ArithPostfixEvaluator() {
    // TODO Initialize to your LinkedStack
    stack = new LinkedStack<Operand<Integer>>();
  }

  /** {@inheritDoc} */
  @Override
  public Integer evaluate(String expr) throws IllegalPostfixExpressionException {
    ArithPostfixParser parser = new ArithPostfixParser(expr);
    int result = 0;
    int number = 0;
    int sign = 0;
    for (Token<Integer> token : parser) {
      Type type = token.getType();
      switch (type) {
        case OPERAND:
          // TODO What do we do when we see an operand?
          number++;
          Operand<Integer> v = token.getOperand();
          stack.push(v);
          if (stack.size() == 1)
            result = stack.top().getValue();
          break;
        case OPERATOR:
          // TODO What do we do when we see an operator?
          Operator<Integer> o = token.getOperator();
          if (String.valueOf(o) == "*" || String.valueOf(o) == "/" || String.valueOf(o) == "+" || String.valueOf(o) == "-"){
            sign++;
            Operand<Integer> num1 = stack.pop();
            Operand<Integer> num0 = stack.pop();
            o.setOperand(0, num0);
            o.setOperand(1, num1);
            Operand<Integer> num = o.performOperation();
            stack.push(num);
            result = num.getValue();
          }

          else if (String.valueOf(o) == "!"){
            Operand<Integer> num = stack.pop();
            o.setOperand(0, num);
            Operand<Integer> num0 = o.performOperation();
            stack.push(num0);
            result = num0.getValue();
          }

          break;
          
        default:
          throw new IllegalStateException("Parser returned an invalid Type: " + type);
      }
    }

    if (sign != number-1)
      throw new IllegalPostfixExpressionException();

    return result;

  }
}
