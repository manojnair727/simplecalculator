package org.manu.math.domain;

/**
 * Expression class that evaluates the multiplication of two expressions/numbers. Here
 * multiplication is performed on two operands. Operand could be another expression or
 * a simple number or an variable whose variable already assigned by let
 * expression.
 * 
 * @author Manoj
 *
 */
public class MultExpression implements Expression {

	private Expression operandExpression1;
	
	private Expression operandExpression2;
	
	public MultExpression(Expression a, Expression b) {
		this.operandExpression1 = a;
		this.operandExpression2 = b;
	}
	
	@Override
	public double evaluate() {
		return this.operandExpression1.evaluate() * this.operandExpression2.evaluate();
	}

}
