/**
 * 
 */
package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Alex and Aviv
 *
 */
class PolynomTest {
	Polynom p = new Polynom("x^2+-2x+4"); // A normal non-zero Polynom.
	Polynom copy = new Polynom(p); // A copy of Polynom p.
	Polynom zeroPolynom = new Polynom("is zero"); // 'Zero Polynom'.
	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link myMath.Polynom#Polynom(java.lang.String)}.
	 */
	@Test
	void testPolynomString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link myMath.Polynom#Polynom(myMath.Polynom)}.
	 */
	@Test
	void testPolynomPolynom() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link myMath.Polynom#f(double)}.
	 */
	@Test
	void testF() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link myMath.Polynom#add(myMath.Polynom_able)}.
	 * Adding a Polynom p to a copy of itself.
	 */
	@Test
	void testAddPolynom_able() {
		
		Polynom expected = new Polynom("2x^2+-4x+8");
		p.add(copy);
		Polynom actual = new Polynom(p);
		assertTrue(expected.equals(actual));
	}
	/**
	 * Test method for {@link myMath.Polynom#add(myMath.Polynom_able)}.
	 * Adding a 'Zero Polynom' to Polynom p.
	 */
	@Test
	void testAddPolynom_able2() {
		
		Polynom expected = new Polynom("x^2+-2x+4");
		p.add(zeroPolynom);
		Polynom actual = new Polynom(p);
		assertTrue(expected.equals(actual));
	}

	/**
	 * Test method for {@link myMath.Polynom#add(myMath.Monom)}.
	 * Adding Monom m to a Polynom.
	 */
	@Test
	void testAddMonom() {
		Monom m = new Monom(4,2);
		Polynom expected = new Polynom("5x^2+-2x+4");
		p.add(m);
		Polynom actual = new Polynom(p);
		assertTrue(expected.equals(actual));
	}
	
	/**
	 * Test method for {@link myMath.Polynom#add(myMath.Monom)}.
	 * Adding zero to a Polynom.
	 */
	@Test
	void testAddMonom2() {
		Monom m = new Monom(0,2);
		Polynom expected = new Polynom("x^2+-2x+4");
		p.add(m);
		Polynom actual = new Polynom(p);
		assertTrue(expected.equals(actual));
	}

	/**
	 * Test method for {@link myMath.Polynom#substract(myMath.Polynom_able)}.
	 * Substract Polynom p2 from Polynom p.
	 */ 
	@Test
	void testSubstract() {
		
		Polynom p2 = new Polynom("x^2+-x+2");
		Polynom expected = new Polynom("-x+2");
		p.substract(p2);;
		Polynom actual = new Polynom(p);
		assertTrue(expected.equals(actual));
	}
	/**
	 * Test method for {@link myMath.Polynom#substract(myMath.Polynom_able)}.
	 * Substract a copy of Polynom p from Polynom p.
	 * Should give 'Zero Polynom'.
	 */
	@Test
	void testSubstract2() {
		
		Polynom expected = new Polynom("is zero");
		p.substract(copy);
		Polynom actual = new Polynom(p);
		assertTrue(expected.equals(actual));
	}
	/**
	 * Test method for {@link myMath.Polynom#substract(myMath.Polynom_able)}.
	 * Substract 'Zero Polynom' from Polynom p.
	 */
	@Test
	void testSubstract3() {
		
		Polynom expected = new Polynom(p);
		p.substract(zeroPolynom);
		Polynom actual = new Polynom(p);
		assertTrue(expected.equals(actual));
	}
	/**
	 * Test method for {@link myMath.Polynom#multiply(myMath.Polynom_able)}.
	 * Multiply Polynom p by itself.
	 */
	@Test
	void testMultiply() {
		
		Polynom expected = new Polynom("16+-16x+12x^2+-4x^3+x^4");
		p.multiply(copy);
		Polynom actual = new Polynom(p);
		assertTrue(expected.equals(actual));
	}
	/**
	 * Test method for {@link myMath.Polynom#multiply(myMath.Polynom_able)}.
	 * Multiply Polynom p by 'Zero Polynom'.
	 */
	@Test
	void testMultiply2() {
	
		Polynom expected = new Polynom(zeroPolynom); //Multiplying a Polynom by zero should give zero
		p.multiply(zeroPolynom);
		Polynom actual = new Polynom(p);
		assertTrue(expected.equals(actual));
	}
	/**
	 * Test method for {@link myMath.Polynom#multiply(myMath.Polynom_able)}.
	 * Multiply Polynom p by 1.
	 */
	@Test
	void testMultiply3() {
		
		Polynom one = new Polynom("1");
		Polynom expected = new Polynom(p); //Multiplying a Polynom by 1 should give the same Polynom.
		p.multiply(one);
		Polynom actual = new Polynom(p);
		assertTrue(expected.equals(actual));
	}
	/**
	 * Test method for {@link myMath.Polynom#equals(myMath.Polynom_able)}.
	 * Tests if a copy of a Polynom equals to itself.
	 */
	@Test
	void testEqualsPolynom_able() {
		
		boolean expected = true;
		boolean actual = p.equals(copy);
		assertEquals(expected,actual);
	}
	/**
	 * Test method for {@link myMath.Polynom#equals(myMath.Polynom_able)}.
	 * Tests if 'Zero Polynom' doesn't equals to Polynom p(not a 'Zero Polynom').
	 */
	@Test
	void testEqualsPolynom_able2() {
		
		boolean expected = false;
		boolean actual = p.equals(zeroPolynom);
		assertEquals(expected,actual);
	}

	/**
	 * Test method for {@link myMath.Polynom#isZero()}.
	 * Tests if a 'Zero Polynom' is actually Zero.
	 */
	@Test
	void testIsZero() {
		
		boolean expected = true;
		boolean actual = zeroPolynom.isZero();
		assertEquals(expected,actual);
	}

	/**
	 * Test method for {@link myMath.Polynom#isZero()}.
	 * Tests if Polynom p(not a 'Zero Polynom') is Zero.
	 */
	@Test
	void testIsZero2() {
		
		boolean expected = false;
		boolean actual = p.isZero();
		assertEquals(expected,actual);
	}
	/**
	 * Test method for {@link myMath.Polynom#root(double, double, double)}.
	 */
	@Test
	void testRoot() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link myMath.Polynom#copy()}.
	 */
	@Test
	void testCopy() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link myMath.Polynom#derivative()}.
	 */
	@Test
	void testDerivative() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link myMath.Polynom#area(double, double, double)}.
	 */
	@Test
	void testArea() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link myMath.Polynom#iteretor()}.
	 */
	@Test
	void testIteretor() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link myMath.Polynom#toString()}.
	 */
	@Test
	void testToString() {
		fail("Not yet implemented");
	}

}
