package myMath;

import java.util.Iterator;

public class Test {
	//sasas
	//test add from another computer.

	public static void main(String[] args) {
		System.out.println("######chengh######");
		System.out.println("######more chengh######");
		System.out.println("######Comperator######");
		testComperator();
		
		System.out.println("\n\n######test1######\n");
		test1a();
		System.out.println("\n\n######test2######\n");
		test2a();
		
		System.out.println("\n\ntestadd\n");
		test_Monom_add();
		System.out.println("\n\ntest_multiply\n");
		test_Monom_multiply();
		System.out.println("\n\nequals\n");
		test_Monom_equals();
		
		
		System.out.println("\n\ntestadd\n");
		test_add();
		System.out.println("\n\ntest_multiply\n");
		test_multiply();
		System.out.println("\n\nequals\n");
		test_equals();				
	}
	
	private static void testComperator() {
		Monom m1 = new Monom(5,1);
		Monom m2 = new Monom(5,2);
		
		//m1 < m2
		Monom_Comperator comperator = new Monom_Comperator();
		if(comperator.compare(m1, m2) < 0) {
			System.out.println("good");
		}else {
			System.out.println("bad");
		}

		//m1 = m2
		m1 = new Monom(5,2);
		if(comperator.compare(m1, m2) == 0) {
			System.out.println("good");
		}else {
			System.out.println("bad");
		}

	}

	private static void test_Monom_equals() {
		double a1 = 2.5;
		int b1 = 2;
		Monom m1 = new Monom(a1,b1);
		Monom m2 = new Monom(a1,b1+1);
		Monom m3 = new Monom(a1+0.001,b1);
		Monom m4 = new Monom(m1);
		System.out.println(m1+" != "+m2);
		System.out.println(m1+" != "+m3);
		System.out.println(m1+" == "+m4);
		if(m1.equals(m2)) {
			System.err.println(m1+" != "+m2);
			throw new RuntimeException("Error: Monom equals method is wrong!");		
		}
		if(m1.equals(m3)) {
			System.err.println(m1+" != "+m3);
			throw new RuntimeException("Error: Monom equals method is wrong!");		
		}
		if(!m1.equals(m4)) {
			System.err.println(m1+" == "+m4);
			throw new RuntimeException("Error: Monom equals method is wrong!");		
		}
	}
	private static void test_Monom_multiply() {
		double a1 = 2.5, a2 = 3;
		int b1 = 2, b2 = 4;
		Monom m1 = new Monom(a1,b1);
		Monom m2 = new Monom(a2,b2);
		Monom m3 = new Monom(m1);
		m3.multiply(m2);
		
		System.out.println(m1+" * "+m2+" = "+m3);
		Monom m4 = new Monom(a1*a2, b1+b2);
		if(!m3.equals(m4)) {
			System.err.println(m3+" != "+m4);
			throw new RuntimeException("Error: Monom multiply method is wrong!");		
		}
	}
	private static void test_Monom_add() {
		double a1 = 2.5;
		int b1 = 2;
		Monom m1 = new Monom(a1,b1);
		Monom m3 = new Monom(m1);
		m3.add(m1);
		
		System.out.println(m1+" + "+m1+" = "+m3);
		Monom m4 = new Monom(a1+a1, b1);
		if(!m3.equals(m4)) {
			System.err.println(m3+" != "+m4);
			throw new RuntimeException("Error: Monom add method is wrong!");		
		}
	}
	
	private static Polynom create_random_polynom(int deg) {
		Polynom ans = new Polynom();
		for(int i=0;i<deg;i++) {
			double a = (Math.random()-0.5)*1000;
			a = (int)a;
			a = a/50;
			Monom tmp = new Monom(a,i);
			ans.add(tmp);
		}
		return ans;
	}
	
	private static void test_equals() {
		Polynom p0 = create_random_polynom(4);
		Polynom p1 = create_random_polynom(5);
		Polynom p0_copy = new Polynom(p0);
		if(p0.equals(p1)) {
			System.err.println(p0+" != "+p1);
			throw new RuntimeException("Error: the Polynoms: "+p0+" and "+p1+"  should NOT be equal!!");		
		}
		if(p0.equals(p1)) {
			System.err.println(p0+" == "+p0_copy);
			throw new RuntimeException("Error: the Polynoms: "+p0+" and "+p0_copy+"  should be equal!!");		
		}
	}
	private static void test_add() {
		Polynom p0 = create_random_polynom(4);
		Polynom p1 = create_random_polynom(5);
		Polynom p2 = new Polynom(p0);
		p2.add(p1);
		System.out.println(p0+" + ");
		System.out.println(p1+" = ");
		System.out.println(p2);
	}
	private static void test_multiply() {
		Polynom p0 = create_random_polynom(2);
		Polynom p1 = create_random_polynom(3);
		Polynom p2 = new Polynom(p0);
		p2.multiply(p1);
		System.out.println(p0+" * ");
		System.out.println(p1+" = ");
		System.out.println(p2);
	}
	
	public static void test2a() {
		Polynom_able p1 = new Polynom("2x+-3x^2+x^3");
		Polynom_able p2 = new Polynom("-0.2+2.1x+-x^3");
		
		//add
		p1.add(p2);
		System.out.println("get \"-0.2+4.1x-3x^2\":");
		System.out.println(p1);
		
		//substract
		p1.substract(p2);
		System.out.println("get \"2x-3x^2+x^3\":");
		System.out.println(p1);
		
		//multiply
		Polynom_able mul = p1.copy();
		mul.multiply(p2);
		System.out.println("get \"-0.4x+4.8x^2-6.5x^3+0.1x^4+3x^5-x^6\":");
		System.out.println(mul);
		
		//derivative
		System.out.println("get \"2-6x+3x^2\":");
		System.out.println(p1.derivative());
		
		System.out.println("get \"2.1-3x^2\":");
		System.out.println(p2.derivative());
		
		//f(x)
		System.out.println("get 6:" + p1.f(3));
		System.out.println("get -20.9:" + p2.f(3));
		
		
		
		//root and area
		double eps = Double.MIN_VALUE;
		System.out.println("\nroot\n");
		
		//root p1
		System.out.println("get something close to 0:" + p1.root(-0.5, 0.5, eps));
		System.out.println("get something close to 1:" + p1.root(0.5, 1.5, eps));
		System.out.println("get something close to 2:" + p1.root(1.5, 2.5, eps));
		
		//root p2
		System.out.println("get something close to -1.49:" + p2.root(-2, -1, eps));
		System.out.println("get something close to 0.09:" + p2.root(0, 1, eps));
		System.out.println("get something close to 1.39:" + p2.root(1, 2, eps));
		
		eps = 0.00001;
		System.out.println("\narea\n");
		//area
		System.out.println("get something close to -0.25:" + p1.area(0, 1, eps));
		System.out.println("get something close to 0:" + p1.area(0.5, 1.5, eps));
		System.out.println("get something close to -0.140625:" + p1.area(0.5, 2, eps));
		
		System.out.println("get something close to 0.6:" + p2.area(0, 1, eps));
		
		
	}
	
	public static void test1a() {
		//zero test
		Polynom_able p1 = new Polynom();
		p1.add(new Monom(0.2,0));
		p1.add(new Monom(-0.1,0));
		p1.add(new Monom(-0.1,0));
		
		System.out.println("get zero:"+p1.toString());
		
		Polynom_able p2 = new Polynom(p1.toString());
		System.out.println("get zero:"+p2.toString());
		
		System.out.println("get true:"+p2.equals(p2));//zero = zero
		
		//polynom test
		p2.add(new Monom(-0.3,0));
		p2.add(new Monom(4,5));
		p2.add(new Monom(-8,1));
		p2.add(new Monom(7.3,3));
		p2.add(new Monom(7,7));
		p2.add(new Monom(-0.2,8));
		p2.add(new Monom(5.2,11));
		
		System.out.println("get this polynom \"-0.3x^0 + -8.0x^1 + 7.3x^3 + 4.0x^5 + 7.0x^7 + -0.2x^8 + 5.2x^11\":");
		System.out.println("polynom is: "+p2);
		
		//copy 1 test
		p1 = new Polynom(p2.toString());
		System.out.println("get same polynom:");
		System.out.println("polynom is: "+p1);
		
		//copy 2 test
		p1 = p2.copy();
		System.out.println("get same polynom:");
		System.out.println("polynom is: "+p1);
		
		//equals 1 test
		p2.add(new Monom(0.2,0));
		p2.add(new Monom(-0.1,0));
		p2.add(new Monom(-0.1,0));
		System.out.println("get true:"+p1.equals(p2));
		
		//equals 2 test
		System.out.println("get true:"+p1.toString().equals(p2.toString()));
		
		
		//Iterator test
		System.out.println("get \"0, 1, 3, 5, 7, 8, 11,\":");
		Iterator<Monom> iterator = p1.iteretor();
		while(iterator.hasNext()){
			System.out.print(iterator.next().get_power() + ", ");
		}
		
		System.out.println();
		
	}
	
	

}
