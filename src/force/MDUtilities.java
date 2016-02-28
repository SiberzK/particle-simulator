/**
 * A class of utility functions for LJ simulations.
 * 
 * @author A. Hermann
 * @author A. R. Turner
 * @version "08/2015"
 */

// Import the IO class

package force;
import util.Particle;
import util.Vector;

public class MDUtilities {

	public static Vector setInitialPositions(double density, Particle[] particles) {
		
		// Set the number of atoms
		int numAtoms = particles.length;
		// Set the box dimensions
		double boxSize = Math.pow(numAtoms / density, 1.0 / 3.0);
		// Set number of particles in each dimension
		int nDim = (int) Math.pow(numAtoms, 1.0 / 3.0) + 1;
		// Separation between particles
		double delta = boxSize / nDim;

		// Loop over dimensions setting particle positions
		int iAtom = 0;
		for (int ix = 0; ix < nDim; ix++) {
			for (int iy = 0; iy < nDim; iy++) {
				for (int iz = 0; iz < nDim; iz++) {
					if (iAtom < numAtoms) {
						Vector pos = new Vector(ix * delta, iy * delta, iz * delta);
						particles[iAtom].setPosition(pos);
						iAtom++;
					}
				}
			}
		}

		// Print results
		// System.out.printf(" %d atoms placed on a cubic lattice\n", iAtom);
		// System.out.printf("Box dimensions = (%1.5f, %1.5f, %1.5f)\n", boxSize, boxSize, boxSize);

		// Return the box size
		return new Vector(boxSize, boxSize, boxSize);

	}

	/**
	 * Set the initial velocities on a Boltzman distribution.
	 */
	public static void setInitialVelocities(double temperature, Particle[] particles) {

		// Set the number of particles
		int nAtom = particles.length;

		// Zero the accumulators
		double xv0 = 0.0;
		double yv0 = 0.0;
		double zv0 = 0.0;
		double vsq = 0.0;

		// Loop over particles, setting random velocities
		for (int i = 0; i < nAtom; i++) {
			// Random initial velocity
			double xvt = Math.random() - 0.5;
			double yvt = Math.random() - 0.5;
			double zvt = Math.random() - 0.5;
			// Set particle velocity
			particles[i].setVelocity(new Vector(xvt, yvt, zvt));
			// Add to total velocity
			xv0 = xv0 + xvt;
			yv0 = yv0 + yvt;
			zv0 = zv0 + zvt;
			vsq = vsq + xvt * xvt + yvt * yvt + zvt * zvt;
		}

		// Zero the movement of the centre of mass
		xv0 = xv0 / nAtom;
		yv0 = yv0 / nAtom;
		zv0 = zv0 / nAtom;

		// Boltzman factor
		double k = Math.sqrt(3 * nAtom * temperature / vsq);

		// Zero the accumulators
		double xv0Tot = 0.0;
		double yv0Tot = 0.0;
		double zv0Tot = 0.0;
		double v0sq = 0.0;

		// Loop over particles, scaling initial velocities
		for (int i = 0; i < nAtom; i++) {
			// Make sure velocities are scaled correctly
			Vector vtemp = particles[i].getVelocity();
			double xvt = k * (vtemp.getX() - xv0);
			double yvt = k * (vtemp.getY() - yv0);
			double zvt = k * (vtemp.getZ() - zv0);
			// Set particle velocity
			particles[i].setVelocity(new Vector(xvt, yvt, zvt));
			// Add to total velocity
			xv0Tot = xv0Tot + xvt;
			yv0Tot = yv0Tot + yvt;
			zv0Tot = zv0Tot + zvt;
			v0sq = v0sq + xvt * xvt + yvt * yvt + zvt * zvt;
		}

		// Output the details
		// System.out.printf("Temperature = %1.5f\n", v0sq / (3 * nAtom));
		// System.out.printf("COM Velocity = (%1.5f, %1.5f, %1.5f)\n", xv0Tot / nAtom, yv0Tot / nAtom, zv0Tot / nAtom);
	}
	
}
