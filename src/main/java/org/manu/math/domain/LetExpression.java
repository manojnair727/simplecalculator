package org.manu.math.domain;

import java.util.Map;

public class LetExpression implements Expression {

	private String a;
	
	private Expression assignExpression;
	
	private Expression c;
	
	private Map<String, Double> variable;
	
	public LetExpression(String a, Expression one, Expression two, Map<String, Double> variable) {
		this.a = a;
		this.assignExpression = one;
		this.c = two;
		this.variable = variable;
	}
	
	public String getVariableName() {
		return this.a;
	}
	
	public Expression getAssignExpression() {
		return this.assignExpression;
	}
	
	public Expression getExpression() {
		return this.c;
	}
	
	@Override
	public double evaluate() {
		String a = this.getVariableName();
		double variableValue = this.assignExpression.evaluate();
		variable.put(a, variableValue);
		return this.getExpression().evaluate();
	}

}
