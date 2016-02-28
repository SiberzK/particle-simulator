/** 
 * @author Michael Kirkland
 * @version "28/02/2016"
 */

package util;
import force.Force;
import integrator.Algorithm;

/**
 * Constructs "static" instances using the Singleton method
 */
public class Singleton {
	
	private static Singleton particlesInstance;
	private static Singleton forceInstance;
	private static Singleton algorithmInstance;
	private static Singleton timestepInstance;
	public Particle[] particles;
	public Force force;
	public Algorithm algorithm;
	public double timestep;
	
	private Singleton(Particle[] particles) {
		this.particles = particles;
	}
	
	private Singleton(Force force) {
		this.force = force;
	}
	
	private Singleton(Algorithm algorithm) {
		this.algorithm = algorithm;
	}
	
	private Singleton(double timestep) {
		this.timestep = timestep;
	}
	
	public static void setParticles(Particle[] particles) {
		particlesInstance = new Singleton(particles);
	}
	
	public static void setForce(Force force) {
		forceInstance = new Singleton(force);
	}
	
	public static void setAlgorithm(Algorithm algorithm) {
		algorithmInstance = new Singleton(algorithm);
	}
	
	public static void setTimestep(double timestep) {
		timestepInstance = new Singleton(timestep);
	}
	
	public static Singleton getParticles() {
		return particlesInstance;
	}
	
	public static Singleton getForce() {
		return forceInstance;
	}
	
	public static Singleton getAlgorithm() {
		return algorithmInstance;
	}
	
	public static Singleton getTimestep() {
		return timestepInstance;	
	}

}
