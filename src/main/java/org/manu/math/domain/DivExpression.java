package org.manu.math.domain;

/**
 * Expression class that evaluates the division of two expressions/numbers. Here
 * division is performed on two operands. Operand could be another expression or
 * a simple number or an variable whose variable already assigned by let
 * expression.
 * 
 * @author Manoj
 *
 */
public class DivExpression implements Expression {

	private Expression operandExpression1;

	private Expression operandExpression2;

	public DivExpression(Expression a, Expression b) {
		this.operandExpression1 = a;
		this.operandExpression2 = b;
	}

	@Override
	public double evaluate() {
		double divisor = this.operandExpression2.evaluate();
		if (divisor == 0) {
			throw new IllegalArgumentException(
					"You are trying to divide a number where divisor is zero or the divisor evaluates to zero.");
		}
		return this.operandExpression1.evaluate() / divisor;
	}

}
