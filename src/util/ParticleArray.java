/**
 * @author Michael Kirkland
 * @version "28/02/2016"
 */

package util;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Constructs an array of particles from input file
 */
public class ParticleArray {

	public static Particle[] arrayConstructor(String inFile) throws IOException {

		// Counts number of particles from file
		File input = new File(inFile);
		Scanner particleScanner = new Scanner(input);
		int lineCount = 0;
		while (particleScanner.hasNextLine()) {
			lineCount++;
			particleScanner.nextLine();
		}
		particleScanner.close();

		// Constructs particles from file and assigns them to an array
		particleScanner = new Scanner(input);
		Particle[] particleList;

		if (lineCount == 1) {
			int numParticles = particleScanner.nextInt();
			particleList = new Particle[numParticles];
			particleScanner.close();
			for (int i = 0; i < numParticles; i++) {
				particleList[i] = new Particle();
			}
			return particleList;
		} else {
			particleList = new Particle[lineCount];
			for (int i = 0; i < lineCount; i++) {
				particleList[i] = new Particle();
				particleList[i].scanParticle(particleScanner);
			}
			return particleList;
		}
	}

}
