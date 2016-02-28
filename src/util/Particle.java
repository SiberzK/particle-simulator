/** 
 * @author Michael Kirkland
 * @version "28/02/2016"
 */

package util;
import java.util.Scanner;

/**
 * Defines a particle in 3D space
 */
public class Particle {
	
	private String label;
	private double mass;
	private Vector position;
	private Vector velocity;
	
	// Default constructor
	public Particle() {
		this.setLabel("Default");
		this.setMass(1.0);
		this.setPosition(new Vector());
		this.setVelocity(new Vector());	
	}
	
	// Explicit constructor
	public Particle(String label, double mass, Vector position, Vector velocity) {
		this.setLabel(label);
		this.setMass(mass);
		this.setPosition(position);
		this.setVelocity(velocity);
	}
	
	// Scans particles from file
    public void scanParticle(Scanner scan) {
    	this.setLabel(scan.next());
    	this.setMass(scan.nextDouble());
    	position.setX(scan.nextDouble());
    	position.setY(scan.nextDouble());
    	position.setZ(scan.nextDouble());
    	velocity.setX(scan.nextDouble());
    	velocity.setY(scan.nextDouble());
    	velocity.setZ(scan.nextDouble());
    }
    
    @Override
    public String toString() {
        return this.getLabel() + " " + this.getPosition().getX() + " " + this.getPosition().getY() + " " + this.getPosition().getZ();
    }
	
    public double kineticEnergy() { 
    	return 0.5*this.getMass()*velocity.magSquared(); }
    
    public static double totalEnergy(Particle a, Particle b) {
    	return a.kineticEnergy() + b.kineticEnergy() + Singleton.getForce().force.potentialEnergy(a,b);
    }
    
    // Returns position vector pointing from a to b
    public static Vector separation(Particle a, Particle b) {
    	return Vector.subVector(a.getPosition(), b.getPosition());
    }
    
    public String getLabel() { return label; }
    
    public double getMass() { return mass; }
    
    public Vector getPosition() { return position; }
    
    public Vector getVelocity() { return velocity; }

    public void setLabel(String label) { this.label = label; }

    public void setMass(double mass) { this.mass = mass; }

    public void setPosition(Vector position) { this.position = position; }

    public void setVelocity(Vector velocity) { this.velocity = velocity; }
    
}
