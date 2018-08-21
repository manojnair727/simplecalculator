package org.manu.math.domain;

import java.util.Map;

/**
 * This class denotes simple double value or it can be a variable whose value is
 * evaluated by let expression.
 * 
 * @author Manoj
 *
 */
public class Operand implements Expression {

	private String operand;
	private Map<String, Double> variable;

	public Operand(String operand, Map<String, Double> variable) {
		this.operand = operand;
		this.variable = variable;
	}

	/**
	 * Gets the value of the operand.
	 * @return operand string representation.
	 */
	public String get() {
		return this.operand;
	}

	@Override
	public double evaluate() {
		try {
			return Double.parseDouble(this.operand);
		} catch (NumberFormatException nfe) {
			Double value = variable.get(this.operand);
			if (value == null) {
				throw new IllegalArgumentException("The variable "+this.operand+" could not be evaluated or cannot be recognized");
			}
			return value.doubleValue();
		}
	}

}
