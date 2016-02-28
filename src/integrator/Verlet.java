/** 
 * @author Michael Kirkland
 * @version "28/02/2016"
 */

package integrator;
import util.Particle;
import util.Singleton;
import util.Vector;

/**
 * Updates positions and velocities according to the Velocity Verlet algorithm
 */
public class Verlet implements Algorithm {

	private Particle[] particles = Singleton.getParticles().particles;
	private double timestep = Singleton.getTimestep().timestep;

	private Vector calculatePosition(Particle particle, Vector force) {
		return Vector.sumVector(particle.getPosition(), Vector.sumVector(particle.getVelocity().scalarMult(timestep),
				force.scalarMult(timestep * timestep / (2 * particle.getMass()))));
	}

	private Vector calculateVelocity(Particle particle, Vector force) {
		return Vector.sumVector(particle.getVelocity(), force.scalarMult(timestep / particle.getMass()));
	}

	private void updatePosition(Particle particle, Vector force) {
		particle.setPosition(calculatePosition(particle, force));
	}

	private void updateVelocity(Particle particle, Vector force) {
		particle.setVelocity(calculateVelocity(particle, force));
	}

	private void updatePositions(Vector[] forceList) {
		for (int i = 0; i < particles.length; i++) {
			updatePosition(particles[i], forceList[i]);
			
			// Checks if boundary conditions need to be applied
			if (Singleton.getForce().force.boundaryConditions(particles[i]) == true) {
				Singleton.getForce().force.boundaryConditions(particles[i]);
			}
		}
	}

	private void updateVelocities(Vector[] forceList, Vector[] forceList_new) {
		for (int i = 0; i < particles.length; i++) {
			updateVelocity(particles[i], (Vector.sumVector(forceList[i], forceList_new[i])).scalarMult(0.5));
		}
	}

	public void updateSystem(Vector[] forceList) {
		updatePositions(forceList);
		Vector[] forceList_new = Integrator.initialiseForces(particles);
		updateVelocities(forceList, forceList_new);
		forceList = forceList_new;
	}

}
