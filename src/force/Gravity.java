/** 
 * @author Michael Kirkland
 * @version "28/02/2016"
 */

package force;
import java.util.Scanner;
import util.Particle;
import util.Vector;

/**
 * Provides simulation with gravitational force calculations
 */
public class Gravity implements Force {

	private double g;
	
	// Force from 'b' acting on 'a' 
    public Vector computeForce(Particle a, Particle b) {
    	return Particle.separation(a, b).scalarMult(g*a.getMass()*b.getMass()).scalarDiv(Math.pow(Particle.separation(a, b).mag(),3));
    }
    
    public double potentialEnergy(Particle a, Particle b) { 
    	return -g*a.getMass()*b.getMass()/Particle.separation(a, b).mag();
 
    }
    
    public void systemParameters(Scanner parameters) {
    	this.g = parameters.nextDouble();
    }
    
    public boolean boundaryConditions(Particle particle) {
    	// No boundary conditions
    	return false;
    }
    
}
