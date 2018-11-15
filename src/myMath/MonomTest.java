package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MonomTest {
	Monom m = new Monom (2.5,2);
	Monom copy = new Monom(m);


	/**
	 * Test method for {@link myMath.Monom#Monom(myMath.Monom)}.
	 * test deep copy monom.
	 * test deep by make sure if i change expected, the monoms don't equal
	 */
	@Test
	void testMonomMonom() {
		Monom expected = new Monom(m);
		assertTrue(expected.equals(m));

		expected.add(new Monom(1,2));
		assertFalse(expected.equals(m));
	}
	/**
	 * Test method for {@link myMath.Monom#f(double)}.
	 * f(x)=2.5x^2
	 * f(3)=22.5
	 */
	@Test
	void testF() {
		double expected = 22.5;
		double actual = m.f(3);
		assertEquals(expected,actual);
	}
	/**
	 * Test method for {@link myMath.Monom#multiply(myMath.Monom)}.
	 * 2.5x^2*2.5^2=6.25x^4
	 */
	@Test
	void testMultiply() {
		Monom expected = new Monom(6.25,4);
		Monom actual = new Monom(m);
		actual.multiply(m);
		assertTrue(expected.equals(actual));
	}
	/**
	 * Test method for {@link myMath.Monom#multiply(myMath.Monom)}.
	 * 2.5x^2*0=0
	 */
	@Test
	void testMultiplyZero() {
		Monom expected = new Monom(0,0);
		Monom actual = new Monom(m);
		
		actual.multiply(new Monom(0,0));
		
		assertTrue(expected.equals(actual));
	}
	/**
	 * Test method for {@link myMath.Monom#derivative()}.
	 * (2.5x^2)'=5x^1
	 */
	@Test
	void testDerivative() {
		Monom expected = new Monom(5,1);
		Monom actual = new Monom(m);
		actual.derivative();
		assertTrue(expected.equals(actual));
	}
	/**
	 * Test method for {@link myMath.Monom#add(myMath.Monom)}.
	 * 2.5x^2+2.5x^2=5x^2
	 */
	@Test
	void testAdd() {
		Monom expected = new Monom(5,2);
		Monom actual = new Monom(m);
		actual.add(m);
		assertTrue(expected.equals(actual));

	}

	/**
	 * Test method for {@link myMath.Monom#get_coefficient()}.
	 */
	@Test
	void testEqualsMonom() {
		boolean expected = true;
		boolean actual = m.equals(copy);
		assertEquals(expected,actual);

		copy.add(new Monom(-1,2));
		actual = m.equals(copy);
		assertNotEquals(expected,actual);

		copy.add(new Monom(1,2));
		actual = m.equals(copy);
		assertEquals(expected,actual);
	}

}
