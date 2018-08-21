package org.manu.math.domain;

/**
 * This is a dummy expression that represents opening parenthesis of the
 * mathematical expression.
 * 
 * @author Manoj
 *
 */
public class ParenthesisExpression implements Expression {

	@Override
	public double evaluate() {
		throw new UnsupportedOperationException("The expression could not be evaluated");
	}

}
