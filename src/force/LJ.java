/** 
 * @author Michael Kirkland
 * @version "28/02/2016"
 */

package force;
import java.util.Scanner;
import util.Particle;
import util.Singleton;
import util.Vector;

/**
 * Provides simulation with Lennard-Jones force calculations
 */
public class LJ implements Force {

	private double temperature;
	private double density;
	private double cutoff;
	private Particle[] particles = Singleton.getParticles().particles;
	
	// Force from 'b' acting on 'a'. Imposes periodic boundary conditions 
	public Vector computeForce(Particle a, Particle b) {
		double factor = 48/(Math.pow(Particle.separation(a, b).mag(),13) - 48/(Math.pow(Particle.separation(a, b).mag(),13)));
		return Particle.separation(a, b).scalarMult(factor);
	}
	
	public double potentialEnergy(Particle a, Particle b) {
		return 4/(Math.pow(Particle.separation(a, b).mag(),12) - 4/(Math.pow(Particle.separation(a, b).mag(),6)));
	}
	
	public void systemParameters(Scanner parameters) {
    	this.temperature = parameters.nextDouble();
    	this.density = parameters.nextDouble();
    	this.cutoff = parameters.nextDouble();
    	// Randomly sets initial particle positions and velocities
    	MDUtilities.setInitialPositions(density, particles);
    	MDUtilities.setInitialVelocities(temperature, particles);
    }
	
	// Makes boundary conditions periodic
	public boolean boundaryConditions(Particle particle) {
		
		Vector position = particle.getPosition();
		double x = position.getX();
		double y = position.getY();
		double z = position.getZ();

		x %= cutoff;
		y %= cutoff;
		z %= cutoff;

		if (x < 0) {
			x += cutoff;
		}

		if (y < 0) {
			y += cutoff;
		}

		if (z < 0) {
			z += cutoff;
		}

		position = new Vector(x, y, z);
		particle.setPosition(position);
		
		return true;
	}
	
}
