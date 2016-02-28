/**
 * @author Michael Kirkland
 * @version "28/02/2016"
 */

package main;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import force.Force;
import integrator.Algorithm;
import integrator.Integrator;
import util.Particle;
import util.ParticleArray;
import util.Singleton;

public class ParticleSimulator {

	public static void main (String[] argv) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException  {

		// Verify input format and initialise parameters
		if (argv.length != 8) {
			System.out.println("System exiting. Incomplete parameters. \nRequired format: 'force algorithm parameters.dat input.dat output.xyz energy.dat iterations timestep'");
			System.exit(1);
		} 
		final String forceName = argv[0];
		final String algorithmName = argv[1];
		final String parametersIn = argv[2];
		final String dataIn = argv[3];
		final String trajectoryOut = argv[4];
		final String energyOut = argv[5];
		final int iterations = Integer.parseInt(argv[6]);
		final double timestep = Double.parseDouble(argv[7]);
		
		// Construct an array of particles from input file
		Particle[] particles = ParticleArray.arrayConstructor(dataIn);
		Singleton.setParticles(particles);
		
		// Instantiate specified force class
		Force forceClass = (Force) (Class.forName("force." + forceName).newInstance());
		Singleton.setForce(forceClass);
		File paramFile = new File(parametersIn);
		Scanner scanner = new Scanner(paramFile);
		Singleton.getForce().force.systemParameters(scanner);
		
		// Instantiate specified algorithm class
		Singleton.setTimestep(timestep);
		Algorithm algorithmClass = (Algorithm) (Class.forName("integrator." + algorithmName).newInstance());
		Singleton.setAlgorithm(algorithmClass);
		
		// Call integrator method
		new Integrator(iterations, trajectoryOut, energyOut).integrate();
	
		System.out.println("Simulation complete.");
	}

}
