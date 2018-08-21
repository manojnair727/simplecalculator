package org.manu.math.domain;

/**
 * This class denotes the operation such as LET, MULT, DIV,ADD AND SUB.
 * 
 * @author Manoj
 *
 */
public class Operation implements Expression {

	private String op;

	public Operation(String op) {
		this.op = op;
	}

	/**
	 * Gets the operation name.
	 * 
	 * @return ADD, MULT, SUB, DIV or LET.
	 */
	public String get() {
		return this.op;
	}

	/**
	 * Do not invoke as this is not supported for operation.
	 */
	@Override
	public double evaluate() {
		throw new UnsupportedOperationException("The operation could not be evaluated");
	}

}
