package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

// import language.arith.*;
import language.*;
import language.arith.DivOperator;
import language.arith.MultOperator;
import language.arith.NegateOperator;
import language.arith.PlusOperator;
import language.arith.SubOperator;
import stack.*;
import evaluator.*;
import evaluator.arith.*;
import parser.IllegalPostfixExpressionException;

// these are the same as public tests
public class ProjectTests {
  Operator<Integer> divOperator;
  Operator<Integer> multOperator;
  Operator<Integer> negOperator;
  Operator<Integer> plusOperator;
  Operator<Integer> subOperator;
  Operand<Integer> op0;
  Operand<Integer> op1;

  private StackInterface<Integer> stack;
  private PostfixEvaluator<Integer> evaluator;

  @Before
  public void before() {
    stack = new LinkedStack<Integer>();
    evaluator = new ArithPostfixEvaluator();
    divOperator = new DivOperator();
    multOperator = new MultOperator();
    negOperator = new NegateOperator();
    plusOperator = new PlusOperator();
    subOperator = new SubOperator();
    op0 = new Operand<Integer>(5);
    op1 = new Operand<Integer>(7);

	
	  
  }

   // DivOperator tests
  @Test(timeout = 5000)
  public void testPerformOperationDivOperatorPublic() {
  	divOperator.setOperand(0, op0);
  	divOperator.setOperand(1, op1);

  	Operand<Integer> result = divOperator.performOperation();
  	int value = result.getValue();
  	assertEquals("DivOperator applied to 5 and 7 should produce 0.", 0, value);
  }

  @Test(timeout = 5000, expected = NullPointerException.class)
  public void testNullArgumentExceptionDivOperatorPublic() {
  	divOperator.setOperand(0, null);
  	fail("DivOperator should not allow null arguments");
  }

  // MultOperator Tests
  @Test(timeout = 5000)
  public void testPerformOperationMultOperatorPublic() {
  	multOperator.setOperand(0, op0);
  	multOperator.setOperand(1, op1);

  	Operand<Integer> result = multOperator.performOperation();
  	int value = result.getValue();
  	assertEquals("MultOperator applied to 5 and 7 should produce 35.", 35, value);
  }

  // NegateOperator Tests
  @Test(timeout = 5000, expected = NullPointerException.class)
  public void testNullArgumentExceptionNegateOperatorPublic() {
  	multOperator.setOperand(0, null);
  	fail("MultOperator should not allow null arguments");
  }

  @Test(timeout = 500)
  public void testGetNumberOfArgumentsNegateOperatorPublic() {
  	assertEquals("Unary operator should have 1 argument.", negOperator.getNumberOfArguments(), 1);
  }

  @Test(timeout = 5000, expected = IllegalArgumentException.class)
  public void testIllegalArgumentExceptionNegateOperatorPublic() {
  	negOperator.setOperand(2, op0);
  	fail("Unary operator should not except input to position 2");
  }

  // PlusOperator Tests
  @Test(timeout = 5000)
  public void testPerformOperationPlusOperatorPublic() {
  	plusOperator.setOperand(0, op0);
  	plusOperator.setOperand(1, op1);

  	Operand<Integer> result = plusOperator.performOperation();
  	int value = result.getValue();
  	assertEquals("Operator applied to 5 and 7 should produce 12.", 5 + 7, value);
  }

  @Test(timeout = 5000)
  public void testGetNumberOfArgumentsPlusOperatorPublic() {
  	assertEquals("Binary operator should have 2 arguments.", plusOperator.getNumberOfArguments(), 2);
  }

  @Test(timeout = 5000, expected = IllegalArgumentException.class)
  public void testIllegalArgumentExceptionPlusOperatorPublic() {
  	plusOperator.setOperand(2, op0);
  	fail("Binary operator should not except input to position 2");
  }

  @Test(timeout = 5000, expected = NullPointerException.class)
  public void testNullArgumentExceptionPlusOperatorPublic() {
  	plusOperator.setOperand(0, null);
  	fail("Operator should not allow null arguments");
  }

  @Test(timeout = 5000, expected = IllegalStateException.class)
  public void testIllegalStateExceptionPlusOperatorPublic() {
  	plusOperator.setOperand(0, op0);
  	plusOperator.setOperand(0, op0);

  	fail("Operator should not allow position 0 to be set more than once");
  }

  @Test(timeout = 5000, expected = IllegalStateException.class)
  public void testIllegalStateExceptionPerformPlusOperatorPublic() {
  	plusOperator.performOperation();
  	fail("Operator should not compute when all arguments have not been set.");
  }

  // SubOperator Tests
  @Test(timeout = 500)
  public void testPerformOperationSubOperatorPublic() {
  	subOperator.setOperand(0, op0);
  	subOperator.setOperand(1, op1);

  	Operand<Integer> result = subOperator.performOperation();
  	int value = result.getValue();
  	assertEquals("Operator applied to 5 and 7 should produce -2.", 5 - 7, value);
  }

  // Evaluator Tests
  @Test(timeout = 5000)
  public void testEvaluateSimplePublic() throws Exception {
  	Integer result = evaluator.evaluate("1");
  	assertEquals(Integer.valueOf(1), result);
  }

  @Test(timeout = 5000)
  public void testEvaluatePlusPublic() throws Exception {
  	Integer result = evaluator.evaluate("1 2 +");
  	assertEquals(Integer.valueOf(3), result);

  	result = evaluator.evaluate("1 2 3 + +");
  	assertEquals(Integer.valueOf(6), result);

  	result = evaluator.evaluate("10000 1000 100 10 1 + + + +");
  	assertEquals(Integer.valueOf(11111), result);
  }

  @Test(timeout = 5000)
  public void testEvaluateSubPublic() throws Exception {
  	Integer result = evaluator.evaluate("1 2 -");
  	assertEquals(Integer.valueOf(-1), result);

  	result = evaluator.evaluate("1 2 3 - -");
  	assertEquals(Integer.valueOf(2), result);

  	result = evaluator.evaluate("1000 100 10 1 - - -");
  	assertEquals(Integer.valueOf(909), result);
  }

  @Test(timeout = 5000)
  public void testEvaluateMultPublic() throws Exception {
  	Integer result = evaluator.evaluate("1 2 *");
  	assertEquals(Integer.valueOf(2), result);

  	result = evaluator.evaluate("1 2 3 * *");
  	assertEquals(Integer.valueOf(6), result);

  	result = evaluator.evaluate("1 2 3 4 * * *");
  	assertEquals(Integer.valueOf(24), result);
  }

  @Test(timeout = 5000)
  public void testEvaluateNegatePublic() throws Exception {
  	Integer result = evaluator.evaluate("1 !");
  	assertEquals(Integer.valueOf(-1), result);

  	result = evaluator.evaluate("2 !");
  	assertEquals(Integer.valueOf(-2), result);

  	result = evaluator.evaluate("-15 !");
  	assertEquals(Integer.valueOf(15), result);
  }

  @Test(timeout = 5000, expected = IllegalPostfixExpressionException.class)
  public void testInvalidExpressionPublic() throws Exception {
  	evaluator.evaluate("1 2");
  }

  // Stack Tests
  @Test(timeout = 500)
  public void testStack() {
  	assertTrue("Stack should be empty after being constructed.", stack.isEmpty());
  	assertEquals("Stack should contain one element after being constructed.", 0, stack.size());

  	stack.push(5);
  	assertFalse("Stack should not be empty.", stack.isEmpty());
  	assertEquals("The top element should be 5", Integer.valueOf(5), stack.top());
  	assertEquals("The stack should contain one element.", 1, stack.size());

  	stack.push(4);
  	assertEquals("The top element should be 4", Integer.valueOf(4), stack.top());
  	assertEquals("The stack should contain two elements.", 2, stack.size());

  	Integer t = stack.pop();
  	assertEquals("The popped element should be 4", Integer.valueOf(4), t);
  	assertEquals("The top element should be 5", Integer.valueOf(5), stack.top());
  	assertEquals("The stack should contain one element.", 1, stack.size());
  	assertFalse("The stack should not be empty.", stack.isEmpty());

  	t = stack.pop();
  	assertEquals("The popped element should be 5", Integer.valueOf(5), t);
  	assertTrue("The stack should be empty.", stack.isEmpty());
  }

  @Test(timeout = 500, expected = StackUnderflowException.class)
  public void testStackUnderflowPop() {
  	stack.pop();
  }

  @Test(timeout = 500, expected = StackUnderflowException.class)
  public void testStackUnderflowPop2() {
  	stack.push(5);
  	stack.pop();
  	stack.pop();
  }

  @Test(timeout = 500, expected = StackUnderflowException.class)
  public void testStackUnderflowPop3() {
  	stack.push(5);
  	stack.push(6);
  	stack.pop();
  	stack.pop();
  	stack.pop();
  }

  @Test(timeout = 500, expected = StackUnderflowException.class)
  public void testStackUnderflowTop() {
  	stack.top();
  }

  @Test(timeout = 500, expected = StackUnderflowException.class)
  public void testStackUnderflowTop2() {
  	stack.push(5);
  	stack.pop();
  	stack.top();
  }

  @Test(timeout = 500, expected = StackUnderflowException.class)
  public void testStackUnderflowTop3() {
  	stack.push(5);
  	stack.push(6);
  	stack.pop();
  	stack.pop();
  	stack.top();
  }

}
