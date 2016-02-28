/** 
 * @author Ellen Sirks
 * @version "21/02/2016"
 */

package util;

/**
 * Defines a 3D vector and all relevant vector operations
 */
public class Vector {

	private double x;
	private double y;
	private double z;

	// Default constructor
	public Vector() {
		this.setVector(0, 0, 0);
	}

	// Explicit constructor
	public Vector(double x, double y, double z) {
		this.setVector(x, y, z);
	}

	// Copy constructor
	public Vector(Vector original) {
		this.setVector(original.getX(), original.getY(), original.getZ());
	}

	@Override
	public String toString() {
		return "(" + this.getX() + ", " + this.getY() + ", " + this.getZ() + ")";
	}

	public double magSquared() {
		return Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2) + Math.pow(this.getZ(), 2);
	}

	public double mag() {
		return Math.sqrt(this.magSquared());
	}

	public Vector scalarMult(double a) {
		return new Vector(this.getX() * a, this.getY() * a, this.getZ() * a);
	}

	public Vector scalarDiv(double a) {
		return new Vector(this.getX() / a, this.getY() / a, this.getZ() / a);
	}

	public static Vector sumVector(Vector a, Vector b) {
		return new Vector(a.getX() + b.getX(), a.getY() + b.getY(), a.getZ() + b.getZ());
	}

	// Vector sum of a and -b
	public static Vector subVector(Vector a, Vector b) {
		return new Vector(a.getX() - b.getX(), a.getY() - b.getY(), a.getZ() - b.getZ());
	}

	public static Vector crossVector(Vector a, Vector b) {
		return new Vector(a.getY() * b.getZ() - a.getZ() * b.getY(), a.getZ() * b.getX() - a.getX() * b.getZ(),
				a.getX() * b.getY() - a.getY() * b.getX());
	}

	public static double dotVector(Vector a, Vector b) {
		return a.getX() * b.getX() + a.getY() * b.getY() + a.getZ() * b.getZ();
	}

	public void setVector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public double getZ() {
		return this.z;
	}

}
