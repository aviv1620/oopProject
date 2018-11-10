package myMath;

import java.util.ArrayList;
import java.util.Iterator;

import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz before the description "add your code below".
 * @author Aviv Vexler 312129331 after the description "add your code below".
 *
 */
public class Polynom implements Polynom_able{

	// ********** add your code below ***********
	
	/** list holder all the monoms*/
	private ArrayList<Monom> monoms;

	/** make new polynom*/
	Polynom(){
		monoms = new ArrayList<Monom>();
	}

	/** make new polynom by string
	 * the format of string is  "a1x^b1+a2x^b2+...+anx^bn
	 * when a1,a2,...,an is the coefficient for every monoms.
	 * b1,b2,...,bn is the power for every monoms
	 * <b>the "+" sign is requirement between every single monom, even if is negative.
	 * possible add negative monom by use "+-" sign.</b>
	 * if monom power is 1 not necessary to write power sign("^").
	 * if monom power is 0 not necessary to write x sigr("x").
	 * to enter zero is need get exactly the string "is zero".
	 * 
	 * i ignore from space and charter case.
	 * 
	 * for example: 1+5x+<b>-2.1x^12</b>+3.8x^4
	 * */
	Polynom(String string){
		this();

		//If is zero make zero polynom
		if(string.equals("is zero"))
			return;

		//Remove unnecessary factor that can make error like space or charter case.
		string = string.replaceAll(" ", "");
		string = string.toLowerCase();

		//Split by '+' sign.
		//Negative number must write in this way +-ax^b
		String[] monomsStr = string.split("\\+");

		//Between every "+" have monom.
		for(String monomStr:monomsStr) {
			int power;
			double coefficient;

			//Negative number
			if(monomStr.charAt(0) == '-') {
				coefficient = -1;
				monomStr = monomStr.substring(1, monomStr.length());
			}
			else
				coefficient = 1;

			//find coefficient
			int indexOfx = monomStr.indexOf("x");
			if(indexOfx == 0) {//x... not have coefficient.this mean x is 1.
				coefficient *= 1; //multiple because it can be negative.
			}else if(indexOfx == -1) {//5+... not have x. this mean power is zero.
				coefficient *= Double.parseDouble(monomStr);//multiple because it can be negative.
				power = 0;
				add(new Monom(coefficient,power));
				continue;
			}else {//ax... normal case. add the coefficient.
				String tamp = monomStr.substring(0,indexOfx);
				coefficient *= Double.parseDouble(tamp);//multiple because it can be negative.
			}

			//find power
			int indexOfpower = monomStr.indexOf("^");
			if(indexOfpower == -1) {//...x... not have ^. this mean power is 1.
				power = 1;
			}else {//normal case. add power.
				String tamp = monomStr.substring(indexOfpower+1, monomStr.length());
				power = Integer.parseInt(tamp);
			}

			//add
			add(new Monom(coefficient,power));
		}

	}

	/** deep copy constructor */
	Polynom(Polynom p1){
		this();
		add(p1);
	}

	@Override
	/** compute the function and return it.*/
	public double f(double x) {
		double ans = 0;
		
		//count every single monom and add this to answer.
		for(Monom m:monoms) {
			ans += m.f(x);
		}

		return ans;
	}

	/** add polynom p1 to this polynom.*/
	@Override
	public void add(Polynom_able p1) {
		//get iterator of every single monom in p1.
		Iterator<Monom> iterator = p1.iteretor();

		//add to this.
		while(iterator.hasNext()) {
			add(iterator.next());
		}

	}

	/** add monom m1 to this polynom.
	 * not support negative power.
	 * add such that the polynom stay reduction.
	 * for example 1+2x^2+2x^2=1+4x^4.
	 * @throws negative power.*/
	@Override
	public void add(Monom m1) {
		//not support negative power. 
		if(m1.get_power() < 0) {
			throw new RuntimeException("not support negative power. you insert "+m1.get_power());
		}
		
		//unnecessary to add this if oefficient is zero
		if(m1.get_coefficient() == 0.0)
			return;

		/*add the coefficient to monom whit same power.
		* or make new one if this monom not exist.*/
		int power = m1.get_power();
		Monom mInsert = find(power);

		
		if(mInsert == null) {//make new monom.
			insert(new Monom(m1));		
		}else {//add coefficient to exist monom
			mInsert.add(m1);

			//if the coefficient is zero remove it.
			if(mInsert.get_coefficient() == 0.0)
				monoms.remove(mInsert);
		}

	}


	/** Subtract p1 from this Polynom */
	@Override
	public void substract(Polynom_able p1) {
		//get iterator of every single monom in p1.
		Iterator<Monom> iterator = p1.iteretor();

		//all monoms in p1.
		while(iterator.hasNext()) {
			Monom m1 = iterator.next();
			Monom negetiveMonom = new Monom(-1*m1.get_coefficient(),m1.get_power());
			add(negetiveMonom);
		}

	}

	/** multiply this Polynom by p1 */
	@Override
	public void multiply(Polynom_able p2) {
		/*p1 - polynom 1(from this).
		* p2 - polynom 2.
		* i write the result to monoms list in this class.*/

		//deep copy this monoms list to p1.
		Polynom_able p1 = new Polynom(this);
		Iterator<Monom> iteP1 = p1.iteretor();

		//empty array list
		monoms = new ArrayList<Monom>();

		//multiply
		while(iteP1.hasNext()) {
			Iterator<Monom> iteP2 = p2.iteretor();
			Monom m1 = iteP1.next();

			while(iteP2.hasNext()) {
				Monom m2 = iteP2.next();

				Monom mult = new Monom(m1);
				mult.multiply(m2);
				add(mult);												
			}
		}											
	}

	/** Indicates whether some other polynom is "equal to" this one. */
	@Override
	public boolean equals(Polynom_able p1) {
		return toString().equals(p1.toString());
	}

	/** Indicates whether this polynom is zero.
	 * this mean in my code that monoms list is empty.
	 * this mean in mathematics that every coefficient in the monoms is zero.*/
	@Override
	public boolean isZero() {
		return monoms.size() == 0;
	}

	/** find root.more details in root method in Polynom_able. 
	 *  {@link Polynom_able#root(double,double,double)}
	 * @return approximated of root.*/
	@Override
	public double root(double x0, double x1, double eps) {
		double xm = (x0+x1)/2;//xm - midle
		double xmOld;
		
		while(true) {
			double result = f(x0)*f(xm); // result = f(x0)*f(xm).
			
			if(result < 0) {
				x1 = xm;
			}else if(result > 0) {
				x0 = xm;
			}else//result = 0/
				return xm;
			
			xmOld = xm;
			xm = (x0 + x1)/2;
			
			double e = Math.abs( (xm-xmOld)/xm );
			if(e < eps)
				return xm;								
		}
			
	}

	/** reate a deep copy of this Polynum  
	 * {@link Polynom_able#copy()}
	 * @return new polynom with same value.
	 * but every value stored in new location on the memory.*/
	@Override
	public Polynom_able copy() {
		return new Polynom(this);
	}

	/**Compute a new Polynom which is the derivative of this Polynom.
	 *  {@link Polynom_able#derivative()} */
	@Override
	public Polynom_able derivative() {
		//make new Polynom
		Polynom dp = new Polynom();//dp = derivative polynom

		//copy every monom, derivative and add to new Polynom.
		for(Monom myMonom:monoms) {
			Monom dm = new Monom(myMonom);//dm = derivative monom.
			dm.derivative();
			dp.add(dm);
		}
		return dp;
	}

	/** Compute Riemann's Integral.
	 * more details in area method in Polynom_able.
	 * {@link cont_function#area(double, double, double)} */
	@Override
	public double area(double x0, double x1, double eps) {
		//number of step.
		int n = (int)((x1-x0)/eps);
		
		//sum step.
		double ans = 0;
		for(int i=1; i <= n;i++) {
			double xp = x0 + (i*eps) - (eps/2);//x point inside the step
			ans += eps*f(xp);
		}
		
		return ans;
	}

	/**get Iterator.
	 * {@link Polynom_able#iteretor()} 
	 * Iterator not iteretor.
	 * @return Iterator of every monoms in this polynom
	 * */
	@Override
	public Iterator<Monom> iteretor() {

		return monoms.iterator();
	}


	/**
	 * make string to print or to use in constructor.
	 * the format is the same format in constructor {@link #Polynom(String)}.
	 * such that it can be used for constructor(s) and equal(s) Polygon
	 */
	@Override
	public String toString() {
		String s = "";

		//if not have monom is zero.
		if(monoms.size() == 0)
			s += "is zero";
		
		//if have monoms add every monoms to string.
		else {
			Iterator<Monom> iterator = monoms.iterator();
			while(iterator.hasNext()) {
				s += iterator.next().toString();

				//not add '+' sign to lest monom.
				if(iterator.hasNext())
					s += " + ";
			}
		}

		return s;
	}



	/** find monom with the same power.
	 * @param power - power of the monom.
	 * @return -  monom with the same power or null if not have one*/
	private Monom find(int power) {
		//search monom
		for(Monom m:monoms) {
			if(m.get_power() == power)
				return m;
		}

		//if not finr return null.
		return null;
	}

	/** insert monom such that the list stay organize*/
	private void insert(Monom m1) {
		
		boolean succeed = false;
		
		//find monom with power greater than m1 power and insert before.
		for (int i = 0; i < monoms.size(); i++) {
			if(monoms.get(i).get_power() > m1.get_power()) {
				monoms.add(i, m1);
				succeed = true;
				break;
			}
		}

		//if not have power greater than m1 power insert m1 to end of the list.
		if(!succeed)
			monoms.add(m1);


	}




}
