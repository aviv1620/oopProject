package myMath;
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz before the description "add your code below".
 * @author Aviv Vexler 312129331 after the description "add your code below".
 *
 */
public class Monom implements function{
	
	/**constructor
	 * @param a - coefficient.
	 * @param b - power */
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	/**copy constructor
	 * @param ot - other Monom.
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	
	
	// ***************** add your code below **********************
	
	
	
	/** give x and return the result of a multiple x power b.
	 * a is coefficient. see method get_coefficient() for more detail .
	 * b is power. see method get_power() for more detail.
	 * @param x - real numbers.
	 * @return result of a multiple x power b.
	 */
	@Override
	public double f(double x) {
		/* The class Math contains methods for performing basic numeric operations.
		 * pow method returns the value of the first argument raised to the power of the second argument
		 */
		return _coefficient*Math.pow(x, _power);
	} 
	
	/** multiply this monom by another monom.
	 * @param m1 - the monom to multiply.*/
	public void multiply(Monom m1) {
		_coefficient *= m1.get_coefficient();
		_power += m1._power;
	}
	
	/** Compute a monom which is the derivative("nigzeret") of this monom.
	 */
	public void derivative() {
		_coefficient *= _power;
		_power--;
		
		if(_power < 0) {
			_power = 0;
			_coefficient = 0;
		}
	}
	
	/** Add m1 to this monom. just for monom with the same power.
	 * @param m1 - monom with the same power.
	 * @throws get monom with different  power.
	 */
	public void add(Monom m1) {
		if(m1.get_power() != _power)
			throw new RuntimeException("add is just for monom with the same power. " + _power +" != " + m1.get_power());
			
		_coefficient += m1.get_coefficient();
		
	}
	
	/** get coefficient
	 * 	@return coefficient - A coefficient is a monomial in the first sense multiplied by constant.
	 */
	public double get_coefficient(){
		return(_coefficient);
	}
	/** get power
	 * 	@return power - product of powers of variables with nonnegative integer exponents.
	 */
	public int get_power() {
		return(_power);
	}
	
	/**return in ths format: "ax^b"
	 * when a is coefficient and b is power.*/
	@Override
	public String toString() {
		return  _coefficient + "x^" + _power;
	}
	
	/**Indicates whether some other monom is "equal to" this one.
	 * equal coefficient and power.
	 * @param another - another Monom
	 * @return true if another equal to to this.
	 */
	public boolean equals(Monom another) {
		return (_coefficient == 0 && another.get_coefficient() == 0 ||
				_coefficient == another.get_coefficient() && _power == another.get_power());
	}
	//****************** Private Methods and Data *****************
	

	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		this._power = p;
	}
	
	private double _coefficient; // 
	private int _power;
	
	
	
}
