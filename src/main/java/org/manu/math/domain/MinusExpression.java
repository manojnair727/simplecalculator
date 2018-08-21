package org.manu.math.domain;

/**
 * Expression class that evaluates the subtraction of two expressions/numbers. Here
 * subtraction is performed on two operands. Operand could be another expression or
 * a simple number or an variable whose variable already assigned by let
 * expression.
 * 
 * @author Manoj
 *
 */
public class MinusExpression implements Expression {

	private Expression operandExpression1;
	
	private Expression operandExpression2;
	
	public MinusExpression(Expression a, Expression b) {
		this.operandExpression1 = a;
		this.operandExpression2 = b;
	}
	
	@Override
	public double evaluate() {
		return this.operandExpression1.evaluate()-this.operandExpression2.evaluate();
	}

}
