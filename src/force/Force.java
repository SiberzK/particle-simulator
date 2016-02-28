/** 
 * @author Michael Kirkland
 * @version "28/02/2016"
 */

package force;
import java.util.Scanner;
import util.Particle;
import util.Vector;

/**
 * Allows for multiple force implementations
 */
public interface Force {

	public Vector computeForce(Particle a, Particle b);

	public double potentialEnergy(Particle a, Particle b);

	public void systemParameters(Scanner parameters);

	public boolean boundaryConditions(Particle particle);

}
