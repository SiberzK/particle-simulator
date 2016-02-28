/** 
 * @author Michael Kirkland
 * @version "28/02/2016"
 */

package integrator;
import util.Vector;

/**
 * Allows for multiple algorithmic implementations
 */
public interface Algorithm {

	public void updateSystem(Vector[] forces);
	
}
