/**
 * 
 */
package myMath;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;


import org.junit.jupiter.api.Test;

/**
 * @author Alex and Aviv
 *
 */
class PolynomTest {
	private final double EPS = 0.001;//approximated for test functions like root and area.
	private final double DELTA = 0.01;//DELTA for test functions like root and area.
	
	Polynom p = new Polynom("x^2+-2x+4"); // A normal non-zero Polynom.
	Polynom copy = new Polynom(p); // A copy of Polynom p.
	Polynom zeroPolynom = new Polynom("is zero"); // 'Zero Polynom'.
	
	Polynom p2 = new Polynom("x^5+-4x^4+3x^3+2x^2+0.5x+-1"); // A normal Polynom with root points.
	
	


	/**
	 * Test method for {@link myMath.Polynom#Polynom(java.lang.String)}.
	 * test if {@link #p} build correct.
	 */
	@Test
	void testPolynomString1() {
		//build manually the polynom that i need.
		Polynom_able OtherP = new Polynom();
		OtherP.add(new Monom(1,2));
		OtherP.add(new Monom(-2,1));
		OtherP.add(new Monom(4,0));
		
		//test if i get this.
		boolean expected = true;
		boolean actual = OtherP.equals(p);
		assertEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link myMath.Polynom#Polynom(java.lang.String)}.
	 * test if {@link #p2} build correct.
	 */
	@Test
	void testPolynomString2() {
		//build manually the polynom that i need.
		Polynom_able OtherP = new Polynom();
		OtherP.add(new Monom(1,5));
		OtherP.add(new Monom(-4,4));
		OtherP.add(new Monom(3,3));
		OtherP.add(new Monom(2,2));
		OtherP.add(new Monom(0.5,1));
		OtherP.add(new Monom(-1,0));

		
		//test if i get this.
		boolean expected = true;
		boolean actual = OtherP.equals(p2);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link myMath.Polynom#Polynom(myMath.Polynom)}.
	 * test if {@link #zeroPolynom} build correct.
	 */
	@Test
	void testPolynomPolynom() {
		//zero polynom.
		Polynom_able OtherP = new Polynom();
		
		//test if i get this.
		boolean expected = true;
		boolean actual = OtherP.equals(zeroPolynom);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link myMath.Polynom#f(double)}.
	 * test p2 in three points
	 * (0,-1),(1,1.5),(2,0)
	 */
	@Test
	void testF1() {
		double expected = -1;
		double actual = p2.f(0);
		assertEquals(expected, actual);
		
		expected = 1.5;
		actual = p2.f(1);
		assertEquals(expected, actual);
		
		expected = 0;
		actual = p2.f(2);
		assertEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link myMath.Polynom#f(double)}.
	 * test p in three points
	 * (2,4),(0,4),(1,3)
	 */
	@Test
	void testF2() {
		double expected = 4;
		double actual = p.f(2);
		assertEquals(expected, actual);
		
		expected = 4;
		actual = p.f(0);
		assertEquals(expected, actual);
		
		expected = 3;
		actual = p.f(1);
		assertEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link myMath.Polynom#f(double)}.
	 * test zero polynom in three points
	 * all point need return zero.
	 */
	@Test
	void testF3() {
		double expected = 0;
		double actual = zeroPolynom.f(2);
		assertEquals(expected, actual);
		
		actual = zeroPolynom.f(0);
		assertEquals(expected, actual);
		
		actual = zeroPolynom.f(1);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link myMath.Polynom#add(myMath.Polynom_able)}.
	 * Adding a Polynom p to a copy of itself.
	 */
	@Test
	void testAddPolynom_ableSame() {
		
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
	void testAddPolynom_ableZero() {
		
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
	void testAddMonomZero() {
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
	void testSubstractSame() {
		
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
	void testSubstractZero() {
		
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
	void testMultiplyZero() {
	
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
	void testMultiplyOne() {
		
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
	void testEqualsPolynom_ableSame() {
		
		boolean expected = true;
		boolean actual = p.equals(copy);
		assertEquals(expected,actual);
	}
	/**
	 * Test method for {@link myMath.Polynom#equals(myMath.Polynom_able)}.
	 * Tests if 'Zero Polynom' doesn't equals to Polynom p(not a 'Zero Polynom').
	 */
	@Test
	void testEqualsPolynom_ableZero() {
		
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
	 * find square 3 by this polynom "sqrt(3) - x = 0"
	 * the answer need to be 1.732.
	 * calculate the approximated 0.001 {@link #EPS} and test with delta 0.01 {@link #DELTA}.
	 */
	@Test
	void testRoot1() {
		Polynom_able p = new Polynom();
		p.add(new Monom(Math.sqrt(3),0));
		p.add(new Monom(-1,1));
		
		double expected = 1.73;
		double actual = p.root(1, 2, EPS);	
		assertEquals(expected, actual,DELTA);
	}
	
	/**
	 * Test method for {@link myMath.Polynom#root(double, double, double)}.
	 * test the root points in the polynom {@link #p2}.
	 * the root points is 0.53 , 2, 2.38
	 * calculate the approximated 0.001 {@link #EPS} and test with delta 0.01 {@link #DELTA}.
	 */
	@Test
	void testRoot2() {				
		double expected = 0.53;
		double actual = p2.root(0, 1, EPS);	
		assertEquals(expected, actual,DELTA);
		
		expected = 2;
		actual = p2.root(0.9, 2.1, EPS);	
		assertEquals(expected, actual,DELTA);
		
		expected = 2.38;
		actual = p2.root(2.1, 3, EPS);	
		assertEquals(expected, actual,DELTA);
	}
	
	

	/**
	 * Test method for {@link myMath.Polynom#copy()}.
	 * make copy for p and check that is equals.
	 * check the copy is deep copy, 
	 * by change one polynom and check that is not equals.
	 */
	@Test
	void testCopy1() {
		//make copy for p and check that is equals
		Polynom_able otherPolynom = p.copy();
		boolean actual = p.equals(otherPolynom);
		assertEquals(true, actual);//test that is equals

		//change copy for p and check that is not equals
		otherPolynom.add(new Monom(1,0));
		actual = otherPolynom.equals(p);
		assertNotEquals(true, actual);
	}
	
	/**
	 * Test method for {@link myMath.Polynom#copy()}.
	 * like testCopy1 with zero polynom.
	 */
	@Test
	void testCopy2() {
		//make copy for p and check that is equals
		Polynom_able otherZeroPolynom = zeroPolynom.copy();
		boolean actual = otherZeroPolynom.equals(zeroPolynom);
		assertEquals(true, actual);//test that is equals

		//change copy for p and check that is not equals
		otherZeroPolynom.add(new Monom(1,0));
		actual = otherZeroPolynom.equals(zeroPolynom);
		assertNotEquals(true, actual);
	}

	/**
	 * Test method for {@link myMath.Polynom#derivative()}.
	 * Derivative {@link #zeroPolynom}
	 */
	@Test
	void testDerivative1() {
		Polynom_able expectedP = new Polynom();
		Polynom_able actualP = zeroPolynom.derivative();
		boolean expected = true;
		boolean actual = expectedP.equals(actualP);
		assertEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link myMath.Polynom#derivative()}.
	 * Derivative {@link #p}
	 */
	@Test
	void testDerivative2() {
		Polynom_able expectedP = new Polynom("2x+-2");
		Polynom_able actualP = p.derivative();
		boolean expected = true;
		boolean actual = expectedP.equals(actualP);
		assertEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link myMath.Polynom#derivative()}.
	 * Derivative {@link #p2}
	 */
	@Test
	void testDerivative3() {
		Polynom_able expectedP = new Polynom("5x^4+-16x^3+9x^2+4x+0.5");
		Polynom_able actualP = p2.derivative();
		boolean expected = true;
		boolean actual = expectedP.equals(actualP);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link myMath.Polynom#area(double, double, double)}.
	 * test {@link #p2} in three different range.
	 * (1,2)   -- 41/30=    1.36666666
	 * (0,0.5) -- -211/640= -0.3296875
	 * (0,2)   -- 7/5=       1.4
	 */
	@Test
	void testArea1() {
		double expected = 1.36666666;
		double actual = p2.area(1, 2, EPS);
		assertEquals(expected, actual,DELTA);
		
		expected = -0.3296875;
		actual = p2.area(0, 0.5, EPS);
		assertEquals(expected, actual,DELTA);
		
		expected = 1.4;
		actual = p2.area(0, 2, EPS);
		assertEquals(expected, actual,DELTA);
	}
	
	/**
	 * Test method for {@link myMath.Polynom#area(double, double, double)}.
	 * test area for zero polynom.
	 */
	@Test
	void testArea2() {
		double expected = 0;
		double actual = zeroPolynom.area(1, 2, EPS);
		assertEquals(expected, actual,DELTA);
	}

	/**
	 * Test method for {@link myMath.Polynom#iteretor()}.
	 * p2 have 6 monoms.
	 * check iterator give all the six.
	 * after fill the polynom. if the iterator not forget one.
	 * polynom need to be equals to p2.
	 */
	@Test
	void testIteretor1() {
		Polynom_able polynom  = new Polynom();//polynom to fill		
		int count = 0;
		Iterator<Monom> iterator = p2.iteretor();
		
		//need count 6 monoms.
		//fill the polynom.
		while(iterator.hasNext()) {
			polynom.add( iterator.next() );
			count++;
		}
		
		//we get all the 6 monoms.
		int expected = 6;
		assertEquals(expected, count);
		
		//i not forget any monom because i fill it.
		boolean equals = polynom.equals(p2);
		assertEquals(true, equals);
	}
	
	/**
	 * Test method for {@link myMath.Polynom#iteretor()}.
	 * zero polynom not give any monom.
	 */
	@Test
	void testIteretor2() {
		Iterator<Monom> iterator = zeroPolynom.iteretor();
		boolean expected = false;
		boolean actual = iterator.hasNext();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link myMath.Polynom#toString()}.
	 * test {@link #p2}
	 * test if possible build new polynom with string get from another polynom.
	 * test if possible compare polynom by string.
	 */
	@Test
	void testToString1() {
		//build new polynom by string
		Polynom_able copyPol = new Polynom(p2.toString());
		boolean expected = true;
		boolean actual = copyPol.equals(p2);
		assertEquals(expected, actual);

		//try to fail this
		copyPol.add(new Monom(1,0));
		copyPol.add(new Monom(-0.5,0));
		copyPol.add(new Monom(-0.5,0));

		//compare string 
		String p2s = p2.toString();
		String copyP2s = copyPol.toString();
		actual = p2s.equals(copyP2s);
		assertEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link myMath.Polynom#toString()}.
	 * like testToString1 just with {@link #p}.
	 */
	@Test
	void testToString2() {
		//build new polynom by string
		Polynom_able copyPol = new Polynom(p.toString());
		boolean expected = true;
		boolean actual = copyPol.equals(p);
		assertEquals(expected, actual);

		//try to fail this
		copyPol.add(new Monom(1,0));
		copyPol.add(new Monom(-0.5,0));
		copyPol.add(new Monom(-0.5,0));

		//compare string 
		String p2s = p.toString();
		String copyP2s = copyPol.toString();
		actual = p2s.equals(copyP2s);
		assertEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link myMath.Polynom#toString()}.
	 * like testToString1 just with {@link #zeroPolynom}.
	 */
	@Test
	void testToString3() {
		//build new polynom by string
		Polynom_able copyPol = new Polynom(zeroPolynom.toString());
		boolean expected = true;
		boolean actual = copyPol.equals(zeroPolynom);
		assertEquals(expected, actual);

		//try to fail this
		copyPol.add(new Monom(1,0));
		copyPol.add(new Monom(-0.5,0));
		copyPol.add(new Monom(-0.5,0));

		//compare string 
		String p2s = zeroPolynom.toString();
		String copyP2s = copyPol.toString();
		actual = p2s.equals(copyP2s);
		assertEquals(expected, actual);
	}

}
