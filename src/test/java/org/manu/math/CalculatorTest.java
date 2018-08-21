package org.manu.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorTest {
	
	@Test
	public void testSimpleAdd() {
		Calculator calculator = new Calculator();
		
		assertEquals(Double.valueOf(3), Double.valueOf(calculator.calculate("add(1, 2)")));
	}
	
	@Test
	public void testLetAdd() {
		Calculator calculator = new Calculator();
		
		assertEquals(Double.valueOf(10), Double.valueOf(calculator.calculate("let(a, 5, add(a, a))")));
	}
	
	@Test
	public void testLetExpressionAdd() {
		Calculator calculator = new Calculator();
		
		assertEquals(Double.valueOf(24), Double.valueOf(calculator.calculate("let(a, add(5, 7), add(a, a))")));
	}
	
	@Test
	public void testLetLetExpressionAdd() {
		Calculator calculator = new Calculator();
		
		assertEquals(Double.valueOf(25), Double.valueOf(calculator.calculate("let(a, let(b, 7, add(5, b)), add(a, 13))")));
	}
	
	@Test
	public void testAddMult() {
		Calculator calculator = new Calculator();
		
		assertEquals(Double.valueOf(7), Double.valueOf(calculator.calculate("add(1, mult(2, 3))")));
	}
	
	@Test
	public void testMultAddDiv() {
		Calculator calculator = new Calculator();
		
		assertEquals(Double.valueOf(12), Double.valueOf(calculator.calculate("mult(add(2, 2), div(9, 3))")));
	}
	
	@Test
	public void testLetMultAdd() {
		Calculator calculator = new Calculator();
		
		assertEquals(Double.valueOf(55), Double.valueOf(calculator.calculate("let(a, 5, let(b, mult(a, 10), add(b, a)))")));
	}
	
	@Test
	public void testLetLetAddAdd() {
		Calculator calculator = new Calculator();
		
		assertEquals(Double.valueOf(40), Double.valueOf(calculator.calculate("let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b)))")));
	}
	
	@Test
	public void testLetAddFloat() {
		Calculator calculator = new Calculator();
		
		assertEquals(Double.valueOf(56.1), Double.valueOf(calculator.calculate("let(a, 5.1, let(b, mult(a, 10), add(b, a)))")));
	}
	
	@Test
	public void testManyOp() {
		Calculator calculator = new Calculator();
		
		assertEquals(Double.valueOf(18.2), Double.valueOf(calculator.calculate("mult(div(add(5, 8), add(3, 2)), div(add(6, 8), sub(4, 2)))")));
	}
	
	@Test
	public void testNegativeResult() {
		Calculator calculator = new Calculator();
		
		assertEquals(Double.valueOf(-18.2), Double.valueOf(calculator.calculate("mult(div(add(5, 8), add(3, 2)), div(add(6, 8), sub(2, 4)))")));
	}
	
	@Test
	public void testBigOp() {
		Calculator calculator = new Calculator();
		
		assertEquals(Double.valueOf(5.80), Double.valueOf(Math.round(calculator.calculate("mult(div(add(50, 7), add(35, 2)), div(add(72, 7), sub(75, 54)))"))), 2);
	}
	
	@Test
	public void testBigValue() {
		Calculator calculator = new Calculator();
		
		assertEquals(Double.valueOf(2147483647d*2147483647d+2147483647), Double.valueOf(calculator.calculate("add(mult(2147483647, 2147483647), 2147483647)")));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMultAddZeroDiv() {
		Calculator calculator = new Calculator();
		
		calculator.calculate("mult(add(2, 2), div(9, 0))");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testLessOperand() {
		Calculator calculator = new Calculator();
		
		calculator.calculate("let(a, 3, div(9, ))");
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testWrongOperation() {
		Calculator calculator = new Calculator();
		
		calculator.calculate("let(a, 3, divide(9, a))");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNoVar() {
		Calculator calculator = new Calculator();
		
		calculator.calculate("let(a, 3, div(9, b))");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testLetInvalid() {
		Calculator calculator = new Calculator();
		
		calculator.calculate("let(a, 5)");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNoOPInvalid() {
		Calculator calculator = new Calculator();
		
		calculator.calculate("let (b, 7, (5, b))");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidParenthesis() {
		Calculator calculator = new Calculator();
		
		calculator.calculate("let(a, (let b, 7, add(5, b)), add(a, 13))");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMissingParenthesis() {
		Calculator calculator = new Calculator();
		
		calculator.calculate("add(10, let a, 5, add(10,a)))");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMissingRiParenthesis() {
		Calculator calculator = new Calculator();
		
		calculator.calculate("add(10, let(a, 5, add(10,a))");
	}
	
	@Test
	public void testLetSubs() {
		Calculator calculator = new Calculator();
		
		assertEquals(Double.valueOf(70), Double.valueOf(calculator.calculate("let(a, let(b, 7, sub(5, b)), add(72, a))")));
	}

}
