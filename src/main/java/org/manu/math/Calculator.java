package org.manu.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.manu.math.domain.AddExpression;
import org.manu.math.domain.ParenthesisExpression;
import org.manu.math.domain.DivExpression;
import org.manu.math.domain.Expression;
import org.manu.math.domain.LetExpression;
import org.manu.math.domain.MinusExpression;
import org.manu.math.domain.MultExpression;
import org.manu.math.domain.Operand;
import org.manu.math.domain.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableSet;

/**
 * Calculates the value of given expression from the command line. Class gets
 * the expression from command line parses the expression and evaluates to a
 * double value. Calculator supports following operations, addition,
 * subtraction, multiplication and division. It also has provision of dynamic
 * variable assignment using let operation. User can also set the logger level
 * using command line option -Dlog-level={LEVEL}
 * 
 * @author Manoj
 *
 */
public class Calculator {

	private static Logger LOGGER = LoggerFactory.getLogger(Calculator.class);
	private static Set<String> OPERATIONS = ImmutableSet.<String>of("ADD", "LET", "MULT", "SUB", "DIV");
	private Map<String, Double> variable = new HashMap<>();
	
	public static void main(String[] args) {
		LOGGER.info("MAIN" + args);
		if (args.length == 0 || args[0] == null || "".equals(args[0].trim())) {
			System.out.println("Expression is empty");
			return;
		}
		LOGGER.info("MAIN" + args[0]);
		try {
			System.out.println(new Calculator().calculate(args[0]));
		} catch(Exception e) {
			LOGGER.error(e.getMessage(), e);
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Calculates the value of the given arithematic expression.
	 * 
	 * @param s
	 *            arithematic expression.
	 * @return value
	 */
	public double calculate(String s) {
		LOGGER.debug("calculate "+s);
		return evaluate(parse(s));
	}

	private Expression parse(String s) {
		char[] ch = s.toCharArray();
		Stack<Expression> stack = new Stack<>();
		String parsed = "";
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] == '(') {
				parsed = handleLeftBraces(stack, parsed);
			} else if (ch[i] == ')') {
				parsed = handleRightBraces(stack, parsed);
			} else if (ch[i] == ',') {
				if (!"".equals(parsed)) {
					stack.push(new Operand(parsed, this.variable));
					parsed = "";
				}
			} else if (ch[i] == ' ') {
				if (!"".equals(parsed)) {
					if (OPERATIONS.contains(parsed.toUpperCase())) {
						stack.push(new Operation(parsed.toUpperCase()));
					} else {
						stack.push(new Operand(parsed, this.variable));
					}
					parsed = "";
				}
			} else {
				parsed += ch[i];
			}
		}
		if (stack.size() > 1) {
			LOGGER.error("stack size is more than 1 as parsing completes"+stack.size());
			throw new IllegalArgumentException("Missing right parenthesis");
		}
		LOGGER.debug("parsing completed "+stack.size());
		return stack.pop();
	}

	private String handleRightBraces(Stack<Expression> stack, String parsed) {
		LOGGER.debug("handling right braces "+parsed);
		if (!"".equals(parsed)) {
			stack.push(new Operand(parsed, this.variable));
			parsed = "";
		}
		List<Expression> expression = new ArrayList<>();
		Expression expr = stack.pop();
		while (!(expr instanceof ParenthesisExpression)) {
			expression.add(0, expr);
			if (stack.isEmpty()) {
				throw new IllegalArgumentException("Missing left parenthesis");
			}
			expr = stack.pop();
		}
		if (stack.isEmpty()) {
			throw new IllegalArgumentException("Missing the operation");
		}
		Expression op = stack.pop();
		if (op instanceof Operation) {
			Operation operation = (Operation) op;
			stack.push(createExpression(operation, expression, this.variable));
		} else {
			throw new IllegalArgumentException("Missing operation or unrecognized operation");
		}
		return parsed;
	}

	private String handleLeftBraces(Stack<Expression> stack, String parsed) {
		if (!"".equals(parsed)) {
			if (OPERATIONS.contains(parsed.toUpperCase())) {
				stack.push(new Operation(parsed.toUpperCase()));
			} else {
				throw new UnsupportedOperationException("There is no operation supported as " + parsed);
			}
			parsed = "";
		}
		stack.push(new ParenthesisExpression());
		return parsed;
	}

	private Expression createExpression(Operation operation, List<Expression> expressions,
			Map<String, Double> variable) {
		LOGGER.info("createExpression operation"+operation.get()+" expression size "+expressions.size());
		
		if (expressions.size() < 2) {
			throw new IllegalArgumentException("Not enough operands for the operation"+operation.get());
		}
		String oper = operation.get();
		LOGGER.debug("createExpression operation"+oper);
		if ("LET".equalsIgnoreCase(oper)) {
			if (expressions.size() < 3 || expressions.get(2) == null) {
				throw new IllegalArgumentException("Incomplete Let Expression");
			}
			Operand operand = (Operand) expressions.get(0);
			return new LetExpression(operand.get(), expressions.get(1), expressions.get(2), variable);
		} else if ("MULT".equalsIgnoreCase(oper)) {
			return new MultExpression(expressions.get(0), expressions.get(1));
		} else if ("SUB".equalsIgnoreCase(oper)) {
			return new MinusExpression(expressions.get(0), expressions.get(1));
		} else if ("DIV".equalsIgnoreCase(oper)) {
			return new DivExpression(expressions.get(0), expressions.get(1));
		} else if ("ADD".equalsIgnoreCase(oper)) {
			return new AddExpression(expressions.get(0), expressions.get(1));
		}
		throw new IllegalArgumentException("Unknown Operation " + oper);
	}

	private double evaluate(Expression expression) {
		LOGGER.debug("evaluate");
		return expression.evaluate();
	}
}
