package fraction;

/**
 * Can calculate fraction with add, subtract, multiply, divide and power with
 * other fraction.
 * 
 * @author Dacharat Pankong
 *
 */
public class Fraction implements Comparable<Fraction> {

	private long numerator;
	private long denominator;
	public static final Fraction ZERO = new Fraction(0);

	/**
	 * Create a new fraction with value numerator/denominator. n and d can be zero.
	 * 
	 * @param numerator
	 *            is numerator of fraction
	 * @param denominator
	 *            is denominator of fraction
	 */
	public Fraction(long numerator, long denominator) {

		if (numerator != 0 && denominator != 0) {
			if (denominator < 0) {
				denominator *= -1;
				numerator *= -1;
			}
		}
		if (numerator == 0 && denominator > 0) {
			denominator = 1;
		}
		this.numerator = numerator;
		this.denominator = denominator;

	}

	/**
	 * Create a new fraction with interger value; this is same as
	 * Fraction(numerator,1)
	 * 
	 * @param numerator
	 *            is numerator of fraction
	 */
	public Fraction(long numerator) {
		this.numerator = numerator;
		this.denominator = 1;
	}

	/**
	 * Create a new function from a String value. The String must contain a long
	 * ("123"), long/long ("12/34") or decimal value ("12.34")
	 * 
	 * @param value
	 *            of fraction
	 */
	public Fraction(String value) {
		String sign = "";
		for (int i = 0; i < value.length(); i++) {
			if (value.charAt(i) == '/')
				sign = "/";
			else if (value.charAt(i) == '.')
				sign = "\\.";
		}
		String[] divide = value.split(sign);
		if (sign == "/") {
			this.numerator = Long.parseLong(divide[0]);
			this.denominator = Long.parseLong(divide[1]);
		} else if (sign == "\\.") {
			int decimal = (divide[1].length());
			this.numerator = Long.parseLong(divide[0] + divide[1]);
			this.denominator = (long) Math.pow(10, decimal);
		} else {
			this.numerator = Long.parseLong(value);
			this.denominator = 1;
		}
	}

	/**
	 * Add this fraction by another fraction and return the result.
	 * 
	 * @param f
	 *            the fraction to add by this fraction.
	 * @return a new Fraction that is the result of this fraction add by f.
	 */
	public Fraction add(Fraction f) {
		if ((f.toString().equals("Infinity") && this.toString().equals("-Infinity"))
				|| (this.toString().equals("Infinity") && f.toString().equals("-Infinity")))
			return new Fraction(0, 0);
		else if (f.toString().equals("NaN") || this.toString().equals("NaN"))
			return new Fraction(0, 0);
		else if (f.toString() == "Infinity" || this.toString() == "Infinity")
			return new Fraction(1, 0);
		else if (f.toString() == "-Infinity" || this.toString() == "-Infinity")
			return new Fraction(-1, 0);
		else if ((this.numerator == Long.MAX_VALUE && f.numerator > 0)
				|| (f.numerator == Long.MAX_VALUE && this.numerator > 0))
			return new Fraction(1, 0);
		else if (this.denominator == f.denominator)
			return new Fraction(this.numerator + f.numerator, this.denominator);
		else {
			long num = (this.numerator * f.denominator) + (f.numerator * this.denominator);
			long den = this.denominator * f.denominator;
			return new Fraction(num, den);
		}
	}

	/**
	 * Subtract this fraction by another fraction and return the result.
	 * 
	 * @param f
	 *            the fraction to subtract by this fraction.
	 * @return a new Fraction that is the result of this fraction subtract by f.
	 */
	public Fraction subtract(Fraction f) {
		if (f.toString() == "-Infinity" && this.toString() == "Infinity")
			return new Fraction(0, 0);
		else if (f.toString() == "Infinity" || this.toString() == "Infinity")
			return new Fraction(1, 0);
		else if (f.toString() == "-Infinity" || this.toString() == "-Infinity")
			return new Fraction(-1, 0);
		else if ((this.numerator == Long.MIN_VALUE && f.numerator > 0)
				|| (f.numerator == Long.MIN_VALUE && this.numerator > 0))
			return new Fraction(-1, 0);
		else if (this.denominator == f.denominator)
			return new Fraction(this.numerator - f.numerator, this.denominator);
		else {
			long num = (this.numerator * f.denominator) - (f.numerator * this.denominator);
			long den = this.denominator * f.denominator;
			return new Fraction(num, den);
		}
	}

	/**
	 * Multiply this fraction by another fraction and return the result.
	 * 
	 * @param f
	 *            the fraction to multiply by this fraction.
	 * @return a new Fraction that is the result of this fraction multiply by f.
	 */
	public Fraction multiply(Fraction f) {
		if ((this.toString().equals("Infinity") && f.toString().equals("0"))
				|| (f.toString() == "Infinity" && this.toString() == "0"))
			return new Fraction(0, 0);
		return new Fraction(this.numerator * f.numerator, this.denominator * f.denominator);
	}

	/**
	 * Divide this fraction by another fraction and return the result.
	 * 
	 * @param f
	 *            the fraction to divide by this fraction.
	 * @return a new Fraction that is the result of this fraction divide by f.
	 */
	public Fraction divide(Fraction f) {
		if (f.toString() == "Infinity") {
			return new Fraction(0);
		}
		return new Fraction(this.numerator * f.denominator, this.denominator * f.numerator);
	}

	/**
	 * Change this fraction to be negative.
	 * 
	 * @return a new fraction that is the negative of this Fraction.
	 */
	public Fraction negate() {
		return new Fraction(this.numerator * -1, this.denominator);
	}

	/**
	 * Power this Fraction n times.
	 * 
	 * @param n
	 *            is number that will power this fraction
	 * @return a new fraction that is this fraction raised to the power n.
	 */
	public Fraction pow(int n) {
		return new Fraction((long) Math.pow(this.numerator, n), (long) Math.pow(this.denominator, n));
	}

	/**
	 * Compare value of this fraction and other fraction
	 * 
	 */
	@Override
	public int compareTo(Fraction f) {
		if (this.isInfinite() && f.isInfinite()) {
			if (this.signum() > 0 && f.signum() < 0)
				return 1;
			else if (this.signum() < 0 && f.signum() > 0)
				return -1;
		}
		return (int) Math.signum(this.subtract(f).doubleValue());
	}

	/**
	 * Calculate value of this fraction.
	 * 
	 * @return value of this fraction as a double.
	 */
	public double doubleValue() {
		double num = this.numerator;
		double den = this.denominator;
		return num / den;
	}

	/**
	 * Check that this fraction is equal to other fraction.
	 * 
	 */
	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null) {
			return false;
		}
		if (arg0.getClass() != this.getClass()) {
			return false;
		}
		Fraction f = (Fraction) arg0;
		if (this.isInfinite() || f.isInfinite())
			return this.toString().equals(f.toString());
		else if (this.isNaN() || f.isNaN())
			return this.toString().equals(f.toString());
		else {
			simpleFraction();
			f.simpleFraction();
			return f.denominator == this.denominator && f.numerator == this.numerator;
		}
	}

	/**
	 * Check that this fraction is NaN or not(NaN = 0/0).
	 * 
	 * @return true if this fraction is Not a Number . false if this fraction is a
	 *         number.
	 */
	public boolean isNaN() {
		if (this.numerator == 0 && this.denominator == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Check that this fraction is infinity or not(Infinity is divide by 0)
	 * 
	 * @return true if this fraction is positive or negative infinity. false if this
	 *         fraction is not infinity.
	 */
	public boolean isInfinite() {
		if (this.numerator != 0 && this.denominator == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Check that this fraction is NaN or not(NaN = 0/0). Static version to make it
	 * easy to test value.
	 * 
	 * @param f
	 *            is the fraction that want to test.
	 * @return true if this fraction is Not a Number . false if this fraction is a
	 *         number.
	 */
	public static boolean isNaN(Fraction f) {
		return f.isNaN();
	}

	/**
	 * Check that this fraction is infinity or not(Infinity is divide by 0). Static
	 * version to make it easy to test value.
	 * 
	 * @param f
	 *            is the fraction that want to test.
	 * @return true if this fraction is positive or negative infinity. false if this
	 *         fraction is not infinity.
	 */
	public static boolean isInfinite(Fraction f) {
		return f.isInfinite();
	}

	/**
	 * Check this fraction that it is positive or negative.
	 * 
	 * @return +1 if this fraction is positive. 0 if this fraction is zero. -1 if
	 *         this fraction is negative.
	 */
	public int signum() {
		if (this.isInfinite()) {
			if (this.numerator < 0)
				return -1;
			else if (this.numerator > 0)
				return 1;
			return 0;
		} else if (this.numerator > 0 && this.denominator > 0) {
			return 1;
		} else if ((this.numerator > 0 && this.denominator < 0) || (this.numerator < 0 && this.denominator > 0)) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 * Find greatest common divisor of this fraction.
	 * 
	 * @param a
	 *            is numerator of fraction.
	 * @param b
	 *            is denominator of fraction.
	 * @return the greatest common divisor of a and b.
	 */
	public long gcd(long a, long b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	/**
	 * Change fraction to simple fraction. For example 5/10 will change to 1/2.
	 * 
	 */
	public void simpleFraction() {
		if (numerator != 0 && denominator != 0) {
			boolean isMinus = false;
			if (numerator < 0) {
				numerator = numerator * -1;
				isMinus = true;
			}
			long gcd = gcd(numerator, denominator);
			if (gcd != 1) {
				numerator /= gcd;
				denominator /= gcd;
			}
			if (isMinus)
				numerator *= -1;
		}
	}

	/**
	 * toString return a String represent of the fraction, with no spaces.
	 */
	@Override
	public String toString() {

		simpleFraction();
		if (this.isNaN())
			return "NaN";
		else if (this.isInfinite() && this.signum() == 1)
			return "Infinity";
		else if (this.isInfinite() && this.signum() == -1)
			return "-Infinity";
		else if (this.denominator == 1)
			return this.numerator + "";
		return this.numerator + "/" + this.denominator;
	}

}