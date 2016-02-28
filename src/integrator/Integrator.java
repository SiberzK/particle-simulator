/** 
 * @author Michael Kirkland
 * @version "28/02/2016"
 */

package integrator;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import util.Particle;
import util.Singleton;
import util.Vector;

/** 
 * Runs the simulation
 */
public class Integrator {
	
	private final Particle[] particles = Singleton.getParticles().particles;
	private final Algorithm algorithm = Singleton.getAlgorithm().algorithm;
	private final double timestep = Singleton.getTimestep().timestep;
	private int iterations;
	private double time;
	private final String trajectoryOut;
	private final String energyOut;
	
	public Integrator(int iterations, String trajectoryOut, String energyOut) {
		this.iterations = iterations;
		this.trajectoryOut = trajectoryOut;
		this.energyOut = energyOut;
	}
	
	// Creates an array of the total forces
	public static Vector[] initialiseForces(Particle[] particles) {
		
		Vector[] forces = new Vector[particles.length];
		Vector[][] forceMatrix = new Vector[particles.length][particles.length];
		for (int i=0; i< particles.length; i++) {
			forces[i] = new Vector();
		}
		
		for (int i=0; i< particles.length; i++) {
			for (int j=i+1; j< particles.length; j++) {
				forceMatrix[i][j] = Singleton.getForce().force.computeForce(particles[i], particles[j]);
				forces[i] = Vector.subVector(forces[i], forceMatrix[i][j]);
				forces[j] = Vector.sumVector(forces[j], forceMatrix[i][j]);
			}
		}
		
		return forces;
	}
	
	// Calculates the total energy of the system
	private double totalEnergy() {
		double totalEnergy = 0;
		for (int i=0; i < particles.length; i++) {
			for (int j=i+1; j< particles.length; j++) {
				totalEnergy += Particle.totalEnergy(particles[i], particles[j]);
			}
		}
		return totalEnergy;
	}
	
	// Prints the positions to file in VMD format
	private void printVMD(PrintWriter trajectoryOut, int iteration) throws IOException {
		trajectoryOut.println(particles.length + "\nPoint = " + iteration); 
		for (int i=0; i< particles.length; i++) {
			trajectoryOut.println(particles[i]);
		}
	}
	
	// Time integrator
	public void integrate() throws IOException {
		
		// Opens files to write to
		PrintWriter trajectoryOutFile = new PrintWriter(new FileWriter(trajectoryOut));
		PrintWriter energyOutFile = new PrintWriter(new FileWriter(energyOut));
		energyOutFile.println(time + " " + totalEnergy());
		
		System.out.println("Progress: ");
		
		// Main method loop - apply updates to each particle
		for (int k=0; k<iterations; k++) {	
			
			// Displays current progress in increments of 10%
			if (k % (iterations/10) == 0 && k != 0) {
				System.out.println(((double)k/iterations)*100 + "%");
			}
			
			// Calls specified algorithm
			algorithm.updateSystem(initialiseForces(particles));
			
			// Prints trajectory and energy data
			printVMD(trajectoryOutFile, k+1);
			time += timestep;
			energyOutFile.println(time + " " + totalEnergy());
		}
		
		trajectoryOutFile.close();
		energyOutFile.close();
	}

}
